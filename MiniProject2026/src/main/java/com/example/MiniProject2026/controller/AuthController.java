package com.example.MiniProject2026.controller;

import com.example.MiniProject2026.model.User;
import com.example.MiniProject2026.repo.UserRepository;
import com.example.MiniProject2026.security.AuthRequest;
import com.example.MiniProject2026.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userRepository.findByName(user.getName())!=null){
            return ResponseEntity.badRequest().body("user already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole()==null || user.getRole().isBlank()){
            user.setRole("USER");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User Registered Successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        String token= jwtUtil.generateToken(authRequest.getUsername());;
        return ResponseEntity.ok(token);
    }
}
