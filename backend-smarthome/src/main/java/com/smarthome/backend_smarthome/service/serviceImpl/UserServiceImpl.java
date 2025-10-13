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
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email é obrigatório e deve ter formato válido");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        if (user.getIdentification() == null) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }

        if (userRepository.findByIdentification(user.getIdentification()).isPresent()) {
            throw new IllegalArgumentException("CPF já está em uso");
        }

        if (user.getBirthDate() == null) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setName(user.getName().trim());
        user.setEmail(user.getEmail().trim());
        
        return userRepository.save(user);
    }

    @Override
    public User updateUser(long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
        if(user.getName() != null && !user.getName().isEmpty()) {
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
