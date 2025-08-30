package com.uade.tpo.grupo3.amancay.service.users;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.grupo3.amancay.entity.User;
import com.uade.tpo.grupo3.amancay.entity.dto.users.UserResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.users.UserRequest;

public interface UserService {
    Page<User> getUsers(PageRequest pageRequest);

    Optional<User> getUserById(Long userId);

    User createUser(String nombre, String apellido, String email, String password);

    void deleteUser(Long userId);

    UserResponse updateUser(UserRequest entity) throws InvalidParameterException;
}
