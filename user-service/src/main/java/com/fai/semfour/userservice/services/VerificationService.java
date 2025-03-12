package com.fai.semfour.userservice.services;

import com.fai.semfour.userservice.dto.request.VerificationCodeRequest;
import com.fai.semfour.userservice.dto.response.VerificationCodeResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;

public interface VerificationService {
    VerificationCodeResponse createVerification(VerificationCodeRequest request);
    VerificationCodeResponse updateVerification(String id, VerificationCodeRequest request);
    VerificationCodeResponse getVerification(String id);
    PagingResponse<VerificationCodeResponse> getAllVerifications(int pageNumber, int pageSize);
    void deleteVerification(String id);
}
