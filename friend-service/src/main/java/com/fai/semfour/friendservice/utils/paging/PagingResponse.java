package com.fai.semfour.friendservice.utils.paging;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PagingResponse<T> {
    List<T> contents;
    PageableData paging;

    public PagingResponse<T> setContents(final List<T> contents) {
        this.contents = contents;
        return this;
    }

    public PagingResponse<T> setPaging(final PageableData paging) {
        this.paging = paging;
        return this;
    }
}
