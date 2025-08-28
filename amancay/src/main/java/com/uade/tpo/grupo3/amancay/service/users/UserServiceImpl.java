package com.uade.tpo.grupo3.amancay.service.users;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.grupo3.amancay.entity.User;
import com.uade.tpo.grupo3.amancay.entity.dto.users.UserResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.users.UserRequest;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getUsers(PageRequest pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User createUser(String nombre, String apellido, String email, String password)
            throws InvalidParameterException {
        if (userRepository.existsByEmail(email)) {
            throw new InvalidParameterException("El email ya está registrado");
        }
        return userRepository.save(new User(nombre, apellido, email, password));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponse updateUser(UserRequest req) throws InvalidParameterException {
        User user = userRepository.findById(req.getId())
                .orElseThrow(() -> new NotFoundException("Fallo la actualizacion"));

        // Si cambia el email, validar duplicado
        if (req.getEmail() != null && !req.getEmail().equals(user.getEmail())
                && userRepository.existsByEmail(req.getEmail())) {
            throw new InvalidParameterException("El email ya está registrado");
        }

        if (req.getNombre() != null)
            user.setNombre(req.getNombre());
        if (req.getApellido() != null)
            user.setApellido(req.getApellido());
        if (req.getEmail() != null)
            user.setEmail(req.getEmail());
        if (req.getPassword() != null)
            user.setPassword(req.getPassword());

        User updated = userRepository.saveAndFlush(user);
        return new UserResponse(updated.getId());
    }
}
