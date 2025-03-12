package com.fai.semfour.userservice.services.impl;

import com.fai.semfour.userservice.dto.request.VerificationCodeRequest;
import com.fai.semfour.userservice.dto.response.VerificationCodeResponse;
import com.fai.semfour.userservice.entities.VerificationCode;
import com.fai.semfour.userservice.exception.AppException;
import com.fai.semfour.userservice.exception.ErrorCode;
import com.fai.semfour.userservice.mapper.VerificationCodeMapper;
import com.fai.semfour.userservice.repositories.VerificationCodeRepository;
import com.fai.semfour.userservice.services.VerificationService;
import com.fai.semfour.userservice.utils.paging.PageableData;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationServiceImpl implements VerificationService {
    VerificationCodeRepository verificationCodeRepository;
    VerificationCodeMapper verificationCodeMapper;

    @Override
    public VerificationCodeResponse createVerification(VerificationCodeRequest request) {
        VerificationCode verificationCode = verificationCodeMapper.toVerificationCode(request);
        return verificationCodeMapper.toVerificationCodeResponse(verificationCodeRepository.save(verificationCode));
    }

    @Override
    public VerificationCodeResponse updateVerification(String id, VerificationCodeRequest request) {
        VerificationCode verificationCode = verificationCodeRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.VERIFICATION_CODE_NOT_FOUND)
        );

        verificationCodeMapper.updateVerificationCode(verificationCode, request);

        return verificationCodeMapper.toVerificationCodeResponse(verificationCodeRepository.save(verificationCode));
    }

    @Override
    public VerificationCodeResponse getVerification(String id) {
        VerificationCode verificationCode = verificationCodeRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.VERIFICATION_CODE_NOT_FOUND)
        );

        return verificationCodeMapper.toVerificationCodeResponse(verificationCode);
    }

    @Override
    public PagingResponse<VerificationCodeResponse> getAllVerifications(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<VerificationCode> verificationCodes = verificationCodeRepository.findAll(pageable);

        List<VerificationCodeResponse> verificationCodeResponses = verificationCodes.getContent()
                .stream().map(verificationCodeMapper::toVerificationCodeResponse).toList();

        PageableData pageableData = new PageableData()
                .setPageNumber(verificationCodes.getNumber())
                .setPageSize(verificationCodes.getSize())
                .setTotalPages(verificationCodes.getTotalPages())
                .setTotalRecords(verificationCodes.getTotalElements());

        return new PagingResponse<VerificationCodeResponse>()
                .setContents(verificationCodeResponses)
                .setPaging(pageableData);
    }

    @Override
    public void deleteVerification(String id) {
        VerificationCode verificationCode = verificationCodeRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.VERIFICATION_CODE_NOT_FOUND)
        );

        verificationCodeRepository.delete(verificationCode);
    }
}
