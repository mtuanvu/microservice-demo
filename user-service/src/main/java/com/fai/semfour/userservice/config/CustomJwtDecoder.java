package com.fai.semfour.userservice.config;

import java.text.ParseException;
import java.util.Objects;
import javax.crypto.spec.SecretKeySpec;

import com.nimbusds.jose.JOSEException;
import com.fai.semfour.userservice.dto.request.IntrospectRequest;
import com.fai.semfour.userservice.dto.response.IntrospectResponse;
import com.fai.semfour.userservice.services.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signerKey}")
    private String signerKey;

    private final AuthenticationService authenticationService;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    public CustomJwtDecoder(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            IntrospectResponse response = authenticationService.introspect(
                    IntrospectRequest.builder().token(token).build());
            if (!response.isValid()) {
                throw new JwtException("Invalid token");
            }
        } catch (ParseException | JOSEException e) {
            throw new JwtException(e.getMessage());
        }

        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HmacSHA512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}