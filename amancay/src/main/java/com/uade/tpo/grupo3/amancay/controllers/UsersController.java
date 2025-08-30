package com.uade.tpo.grupo3.amancay.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.grupo3.amancay.entity.User;
import com.uade.tpo.grupo3.amancay.entity.dto.users.UserResponse;
import com.uade.tpo.grupo3.amancay.service.users.UserService;
import com.uade.tpo.grupo3.amancay.entity.dto.users.UserRequest;

import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping // GET /users
    public ResponseEntity<Page<User>> getUsers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(userService.getUsers(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(userService.getUsers(PageRequest.of(page, size)));
    }

    @GetMapping("/{userId}") // GET /users/{userId}
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> result = userService.getUserById(userId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping // POST /users
    public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest)
            throws InvalidParameterException {
        User result = userService.createUser(
                userRequest.getNombre(),
                userRequest.getApellido(),
                userRequest.getEmail(),
                userRequest.getPassword());
        return ResponseEntity.created(URI.create("/users/" + result.getId())).body(result);
    }

    @PutMapping // PUT /users
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest)
            throws InvalidParameterException {
        UserResponse result = userService.updateUser(userRequest);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping // DELETE /users
    public void deleteUser(@RequestBody UserRequest userRequest)
            throws InvalidParameterException {
        userService.deleteUser(userRequest.getId());
    }
}
