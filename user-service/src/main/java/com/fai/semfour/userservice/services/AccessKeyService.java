package com.fai.semfour.userservice.services;

import com.fai.semfour.userservice.dto.request.AccessKeyRequest;
import com.fai.semfour.userservice.dto.response.AccessKeyResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;

public interface AccessKeyService {
    AccessKeyResponse createAccessKey(AccessKeyRequest request);
    AccessKeyResponse updateAccessKey(String id, AccessKeyRequest request);
    AccessKeyResponse getAccessKey(String id);
    PagingResponse<AccessKeyResponse> getAllAccessKeys(int pageNumber, int pageSize);
    void deleteAccessKey(String id);
}
