package com.example.demo.securityjwt.service;

import com.example.demo.securityjwt.controller.dto.AuthenticationRequest;
import com.example.demo.securityjwt.controller.dto.AuthenticationResponse;
import com.example.demo.securityjwt.controller.dto.RegisterRequest;
import com.example.demo.securityjwt.repo.UserRepository;
import com.example.demo.securityjwt.user.Role;
import com.example.demo.securityjwt.user.User;
import com.example.demo.securityjwt.utils.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public record AuthenticationService(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager) {
	
	 
	
    public AuthenticationResponse register(RegisterRequest request) {
        final var user = new User();
        user.setEmail(request.email());
        user.setFirstname(request.firstname());
        user.setLastname(request.lastname());
        user.setPasswd(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        
        userRepository.save(user);
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        final var user = userRepository.findByEmail(request.email()).orElseThrow();
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);

    }
}
