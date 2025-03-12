package com.fai.semfour.userservice.services.impl;

import com.fai.semfour.userservice.dto.request.AccessKeyRequest;
import com.fai.semfour.userservice.dto.response.AccessKeyResponse;
import com.fai.semfour.userservice.entities.AccessKey;
import com.fai.semfour.userservice.exception.AppException;
import com.fai.semfour.userservice.exception.ErrorCode;
import com.fai.semfour.userservice.mapper.AccessKeyMapper;
import com.fai.semfour.userservice.repositories.AccessKeyRepository;
import com.fai.semfour.userservice.services.AccessKeyService;
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
public class AccessKeyServiceImpl implements AccessKeyService {
    AccessKeyRepository accessKeyRepository;
    AccessKeyMapper accessKeyMapper;

    @Override
    public AccessKeyResponse createAccessKey(AccessKeyRequest request) {
        AccessKey accessKey = accessKeyMapper.toAccessKey(request);
        return accessKeyMapper.toAccessKeyResponse(accessKeyRepository.save(accessKey));
    }

    @Override
    public AccessKeyResponse updateAccessKey(String id, AccessKeyRequest request) {
        AccessKey accessKey = accessKeyRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ACCESS_KEY_NOT_FOUND));

        accessKeyMapper.updateAccessKey(accessKey, request);

        return accessKeyMapper.toAccessKeyResponse(accessKeyRepository.save(accessKey));
    }

    @Override
    public AccessKeyResponse getAccessKey(String id) {
        AccessKey accessKey = accessKeyRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ACCESS_KEY_NOT_FOUND)
        );

        return accessKeyMapper.toAccessKeyResponse(accessKey);
    }

    @Override
    public PagingResponse<AccessKeyResponse> getAllAccessKeys(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<AccessKey> accessKeys = accessKeyRepository.findAll(pageable);

        List<AccessKeyResponse> accessKeyResponses = accessKeys.getContent()
                .stream().map(accessKeyMapper::toAccessKeyResponse).toList();

        PageableData pageableData = new PageableData()
                .setPageNumber(accessKeys.getNumber())
                .setPageSize(accessKeys.getSize())
                .setTotalPages(accessKeys.getTotalPages())
                .setTotalRecords(accessKeys.getTotalElements());

        return new PagingResponse<AccessKeyResponse>()
                .setContents(accessKeyResponses)
                .setPaging(pageableData);
    }

    @Override
    public void deleteAccessKey(String id) {
        AccessKey accessKey = accessKeyRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ACCESS_KEY_NOT_FOUND)
        );

        accessKeyRepository.delete(accessKey);
    }
}
