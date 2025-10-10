package com.smarthome.backend_smarthome.service;

import com.smarthome.backend_smarthome.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(User usuario);

    Optional<User> findUserById(long id);

    User updateUser(long id, User usuario);

    void deleteUser(long id);


}
