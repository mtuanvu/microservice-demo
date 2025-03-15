package com.fai.semfour.userservice.mapper;

import com.fai.semfour.userservice.dto.request.AccountRequest;
import com.fai.semfour.userservice.dto.response.AccountResponse;
import com.fai.semfour.userservice.entities.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAccount(@MappingTarget Account account, AccountRequest request);

    @Mapping(source = "user", target = "user")
    AccountResponse toAccountResponse(Account account);
}
