package com.fai.semfour.userservice.dto.request;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionRequest {
    String name;

    public PermissionRequest(String name) {
        this.name = name;
    }

    public PermissionRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
