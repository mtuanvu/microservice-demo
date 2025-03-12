package com.fai.semfour.userservice.utils.paging;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageableData {
    int pageNumber;
    int pageSize;
    int totalPages;
    long totalRecords;

    public PageableData setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber + 1;
        return this;
    }

    public PageableData setPageSize(final int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageableData setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PageableData setTotalRecords(final long totalRecords) {
        this.totalRecords = totalRecords;
        return this;
    }
}
