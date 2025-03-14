package com.fai.semfour.userservice.services;

import com.fai.semfour.userservice.dto.request.AccountRequest;
import com.fai.semfour.userservice.dto.response.AccountResponse;
import com.fai.semfour.userservice.utils.paging.PagingResponse;

public interface AccountService {
    AccountResponse registerAccount(AccountRequest request);
    AccountResponse updateAccount(String id, AccountRequest request);
    AccountResponse getAccount(String id);
    PagingResponse<AccountResponse> getAllAccounts(int pageNumber, int pageSize);
    void deleteAccount(String id);
}
