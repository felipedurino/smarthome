package com.smarthome.backend_smarthome.service;

import com.smarthome.backend_smarthome.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Override
    public User registerUser(User usuario) {
        return null;
    }

    @Override
    public Optional<User> findUserById(long id) {
        return Optional.empty();
    }

    @Override
    public User updateUser(long id, User usuario) {
        return null;
    }

    @Override
    public void deleteUser(long id) {

    }
}
