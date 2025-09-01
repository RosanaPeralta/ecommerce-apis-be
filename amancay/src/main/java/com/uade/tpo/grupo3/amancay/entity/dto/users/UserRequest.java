package com.uade.tpo.grupo3.amancay.entity.dto.users;

import lombok.Data;

@Data
public class UserRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserRequest() {
    }

    public UserRequest(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
