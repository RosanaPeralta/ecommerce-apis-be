package com.uade.tpo.grupo3.amancay.entity.dto.users;

public class UserResponse {

    private Long id;

    public UserResponse() {
    }

    public UserResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
