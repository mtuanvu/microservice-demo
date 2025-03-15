package com.fai.semfour.userservice.services.impl;

import com.fai.semfour.userservice.dto.response.AccessKeyResponse;
import com.fai.semfour.userservice.dto.response.IntrospectResponse;
import com.fai.semfour.userservice.dto.response.RefreshTokenResponse;
import com.fai.semfour.userservice.repositories.AccessKeyRepository;
import com.fai.semfour.userservice.repositories.AccountRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.fai.semfour.userservice.dto.request.*;
import com.fai.semfour.userservice.entities.Account;
import com.fai.semfour.userservice.entities.AccessKey;
import com.fai.semfour.userservice.exception.AppException;
import com.fai.semfour.userservice.exception.ErrorCode;
import com.fai.semfour.userservice.mapper.AccessKeyMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository accountRepository;
    private final AccessKeyRepository accessKeyRepository;
    private final AccessKeyMapper accessKeyMapper;
    private final PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.access-token-valid-duration}")
    protected long ACCESS_VALID_DURATION;

    @NonFinal
    @Value("${jwt.signerKey-refresh-token}")
    protected String SIGNER_KEY_REFRESH_TOKEN;

    @NonFinal
    @Value("${jwt.refresh-token-valid-duration}")
    protected long REFRESH_VALID_DURATION;

    @Transactional
    public AccessKeyResponse authenticate(AuthenticationRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String accessToken = generateToken(account, ACCESS_VALID_DURATION);
        String refreshToken = generateToken(account, REFRESH_VALID_DURATION);

        List<AccessKey> existingAccessKeys = accessKeyRepository.findByAccount(account);

        Optional<AccessKey> oldKeyOpt = existingAccessKeys.stream()
                .filter(key -> key.getDeviceType() == request.getDeviceType())
                .findFirst();

        AccessKey accessKey;
        if (oldKeyOpt.isPresent()) {
            AccessKey oldKey = oldKeyOpt.get();
            oldKey.setIsActive(false);
            accessKeyRepository.save(oldKey);
        }

        Optional<AccessKey> existingAccessKeyOpt = accessKeyRepository.findByDeviceIdAndIsActiveTrue(request.getDeviceId());

        if (existingAccessKeyOpt.isPresent()) {
            accessKey = existingAccessKeyOpt.get();
        } else {
            accessKey = accessKeyMapper.toAccessKey(request, accessToken, refreshToken);
            accessKey.setAccount(account);
        }

        return accessKeyMapper.toResponse(accessKeyRepository.saveAndFlush(accessKey));
    }


    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        Optional<AccessKey> accessKeyOpt = accessKeyRepository
                .findByRefreshTokenAndIsActiveTrue(request.getRefreshToken());

        if (accessKeyOpt.isEmpty()) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }


        AccessKey accessKey = accessKeyOpt.get();
        Account account = accessKey.getAccount();

        accessKeyRepository.delete(accessKey);

        String newAccessToken = generateToken(account, ACCESS_VALID_DURATION);
        String newRefreshToken = generateRefreshToken(account, REFRESH_VALID_DURATION);

        AccessKey newAccessKey = AccessKey.builder()
                .id(UUID.randomUUID().toString())
                .account(account)
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .deviceId(accessKey.getDeviceId())
                .deviceType(accessKey.getDeviceType())
                .lastUsed(LocalDateTime.now())
                .isActive(true)
                .build();
        accessKeyRepository.save(newAccessKey);

        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .build();
    }

    @Transactional
    public void logout(LogoutRequest request) {
        Optional<AccessKey> accessKeyOpt = accessKeyRepository
                .findByAccessTokenAndIsActiveTrue(request.getAccessToken());

        if (accessKeyOpt.isPresent()) {
            AccessKey accessKey = accessKeyOpt.get();
            accessKey.setIsActive(false);
            accessKeyRepository.save(accessKey);
        } else {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
    }

    private String generateToken(Account account, long duration) {
        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(account.getEmail())
                    .issuer("semfour.com")
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(duration, ChronoUnit.SECONDS)))
                    .jwtID(UUID.randomUUID().toString())
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
            signedJWT.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateRefreshToken(Account account, long duration) {
        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(account.getEmail())
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(duration, ChronoUnit.SECONDS)))
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
            signedJWT.sign(new MACSigner(SIGNER_KEY_REFRESH_TOKEN.getBytes()));

            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        if (!signedJWT.verify(verifier)) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (expiryTime.before(new Date())) {
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }

        Optional<AccessKey> accessKeyOpt = accessKeyRepository
                .findByAccessTokenAndIsActiveTrue(token);

        if (accessKeyOpt.isEmpty()) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }



    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();

        try {
            verifyToken(token);

            return IntrospectResponse.builder()
                    .valid(true)
                    .build();
        } catch (AppException e) {
            return IntrospectResponse.builder().valid(false).build();
        }
    }

}
