package com.example.school.controller;

import com.example.school.model.User;
import com.example.school.repository.UserRepository;
import com.example.school.security.dto.AccountCredentialDto;
import com.example.school.security.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "Método para autenticar usuário e recuperar token JWT.")
    @PostMapping("/signin")
    public ResponseEntity<Map<Object, Object>>insertPerson(@Valid @RequestBody AccountCredentialDto accountCredentialDto) {

        String username = accountCredentialDto.getUsername();
        String password = accountCredentialDto.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepository.findByUser(username);

        if (user == null)
            throw new UsernameNotFoundException("Username: " + username + " not found.");

        String token = jwtTokenProvider.createToken(username, user.getRoles());

        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        model.put("exp", jwtTokenProvider.getExpiration(token));

        return ResponseEntity.status(HttpStatus.OK).body(model);
    }
}
