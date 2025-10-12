package com.smarthome.backend_smarthome.controller.api;


import com.smarthome.backend_smarthome.DTOS.request.LoginRequestDTO;
import com.smarthome.backend_smarthome.DTOS.response.LoginResponseDTO;
import com.smarthome.backend_smarthome.model.User;
import com.smarthome.backend_smarthome.repository.UserRepository;
import com.smarthome.backend_smarthome.security.JwtUtil;
import com.smarthome.backend_smarthome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Optional<User> userOptional= userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Credenciais Inválidas");
        }
        User user = userOptional.get();
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");

        }
        String token= jwtUtil.generateToken(email);

        LoginResponseDTO loginResponse= new LoginResponseDTO(token,email);
        return ResponseEntity.ok(loginResponse);
    }

}
