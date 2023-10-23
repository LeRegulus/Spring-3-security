package com.regulus.sms.services.ServiceImpl;

import com.regulus.sms.models.Dtos.JwtAuthResponse;
import com.regulus.sms.models.Dtos.RefreshTokenRequest;
import com.regulus.sms.models.Dtos.SignInRequest;
import com.regulus.sms.models.Dtos.SignUpRequest;
import com.regulus.sms.models.entities.Role;
import com.regulus.sms.models.entities.User;
import com.regulus.sms.repositories.RoleRepository;
import com.regulus.sms.repositories.UserRepository;
import com.regulus.sms.services.AuthService;
import com.regulus.sms.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public User signUp(SignUpRequest signUpRequest){
        Role role = roleRepository.findByRoleName("USER");
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    public JwtAuthResponse signin(SignInRequest signInRequest){
        System.out.println("------------Alou-------------------------");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));
        System.out.println("------------Regulus-------------------------");
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refresToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setUser(user);
        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setRefreshToken(refresToken);
        return jwtAuthResponse;
    }

    public JwtAuthResponse refrestoken(RefreshTokenRequest refreshTokenRequest){
        String email = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(email).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setUser(user);
        jwtAuthResponse.setRefreshToken(refreshTokenRequest.getToken());
        return jwtAuthResponse;
        }
        return null;
    }


}
