package com.smarthome.backend_smarthome.service;

import com.smarthome.backend_smarthome.model.User;

import java.util.Optional;

public interface UserService {

    public User registerUser(User usuario);

    public Optional <User> findUserById(long id);

    public User updateUser(long id, User usuario);

    public void deleteUser(long id);




}
