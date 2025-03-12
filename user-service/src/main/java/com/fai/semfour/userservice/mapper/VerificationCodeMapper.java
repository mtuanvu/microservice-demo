package com.fai.semfour.userservice.mapper;

import com.fai.semfour.userservice.dto.request.VerificationCodeRequest;
import com.fai.semfour.userservice.dto.response.VerificationCodeResponse;
import com.fai.semfour.userservice.entities.VerificationCode;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface VerificationCodeMapper {
    VerificationCode toVerificationCode(VerificationCodeRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVerificationCode(@MappingTarget VerificationCode verificationCode, VerificationCodeRequest request);

    VerificationCodeResponse toVerificationCodeResponse(VerificationCode verificationCode);
}
