package com.uade.tpo.grupo3.amancay.entity.dto.Authentication;

import com.uade.tpo.grupo3.amancay.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
