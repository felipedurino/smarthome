package com.smarthome.backend_smarthome.service.serviceImpl;

import com.smarthome.backend_smarthome.model.User;
import com.smarthome.backend_smarthome.repository.UserRepository;
import com.smarthome.backend_smarthome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        if (user.getName()== null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Nome é invalido");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if(user.getEmail()== null || user.getEmail().isEmpty() ||!user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email é invalido");
        }
        return userRepository.save(user);
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
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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


    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException ("Id nao existe");
        }
        userRepository.deleteById(id);
    }

}
