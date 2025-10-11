package com.smarthome.backend_smarthome.service;

import com.smarthome.backend_smarthome.model.User;
import com.smarthome.backend_smarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        if (user.getName()== null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Nome é invalido");
        }
        if(user.getEmail()== null || user.getEmail().isEmpty() ||!user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email é invalido");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
        if(user.getName()== null || user.getName().isEmpty()) {
            existingUser.setName(user.getName().trim());
        }
        if(user.getEmail() !=null && !user.getEmail().trim().isEmpty()) {
            String newEmail = user.getEmail().trim();
            if(!newEmail.contains("@")) {
                throw new IllegalArgumentException("Email deve conter um formato valido");
            }
            userRepository.findByEmail(newEmail)
                    .ifPresent(userWithEmail -> {
                        if (!userWithEmail.getId().equals(id)) {
                            throw new IllegalArgumentException("Email ja está em uso");
                        }
                    });
            existingUser.setEmail(newEmail);
        }
        if(user.getPassword() !=null && !user.getPassword().trim().isEmpty()) {
            existingUser.setPassword(user.getPassword());
        }
        if(user.getIdentification()!=null){
            existingUser.setIdentification(user.getIdentification());
        }
        if (user.getBirthDate()!=null){
            existingUser.setBirthDate(user.getBirthDate());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException ("Id nao existe");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
