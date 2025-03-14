package com.fai.semfour.userservice.services.impl;

import com.fai.semfour.userservice.dto.request.AccountRequest;
import com.fai.semfour.userservice.dto.response.AccountResponse;
import com.fai.semfour.userservice.entities.Account;
import com.fai.semfour.userservice.entities.User;
import com.fai.semfour.userservice.exception.AppException;
import com.fai.semfour.userservice.exception.ErrorCode;
import com.fai.semfour.userservice.mapper.AccountMapper;
import com.fai.semfour.userservice.mapper.UserMapper;
import com.fai.semfour.userservice.repositories.AccountRepository;
import com.fai.semfour.userservice.repositories.RoleRepository;
import com.fai.semfour.userservice.repositories.UserRepository;
import com.fai.semfour.userservice.services.AccountService;
import com.fai.semfour.userservice.utils.paging.PageableData;
import com.fai.semfour.userservice.utils.paging.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    @Transactional
    public AccountResponse registerAccount(AccountRequest request) {
        if (accountRepository.existsByUsername(request.getUsername()) || userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USERNAME_EMAIL_ALREADY_EXISTS);
        }

        Account account = accountMapper.toAccount(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        User user = userMapper.toUser(request, new User());
        user.setAccount(account);

        account.setUser(user);
        accountRepository.save(account);

        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse updateAccount(String id, AccountRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        accountMapper.updateAccount(account, request);

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @Override
    public AccountResponse getAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        return accountMapper.toAccountResponse(account);
    }

    @Override
    public PagingResponse<AccountResponse> getAllAccounts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Account> accounts = accountRepository.findAll(pageable);


        List<AccountResponse> accountResponses = accounts.getContent()
                .stream().map(accountMapper::toAccountResponse).toList();

        PageableData pageableData = new PageableData()
                .setPageNumber(accounts.getNumber())
                .setTotalPages(accounts.getTotalPages())
                .setPageSize(accounts.getNumber())
                .setTotalRecords(accounts.getTotalElements());

        return new PagingResponse<AccountResponse>()
                .setContents(accountResponses)
                .setPaging(pageableData);
    }

    @Override
    public void deleteAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND)
        );

        accountRepository.delete(account);
    }
}
