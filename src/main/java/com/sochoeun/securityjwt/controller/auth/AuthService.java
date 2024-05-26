package com.sochoeun.securityjwt.controller.auth;

import com.sochoeun.securityjwt.model.Role;
import com.sochoeun.securityjwt.model.User;
import com.sochoeun.securityjwt.repository.RoleRepository;
import com.sochoeun.securityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request){
        // get roles from request
        List<String> strRole =request.getRoles();
        List<Role> roles = new ArrayList<>();
        for (String role:strRole){
            Role getRole = roleRepository.findByName(role).orElseThrow(
                    () -> new RuntimeException("Role:%s not found".formatted(role)));
            roles.add(getRole);
        }
        // save user to db
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        User save = userRepository.save(user);

        // response register -> success
        /*AuthResponse response = new AuthResponse();
        response.setFirstName(save.getFirstname());
        response.setLastName(save.getLastname());
        response.setEmail(save.getEmail());
        response.setProfile(save.getProfile());
        response.setRoles(save.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));

        return response;*/
        return AuthResponse.builder()
                .firstName(save.getFirstname())
                .lastName(save.getLastname())
                .email(save.getEmail())
                .profile(save.getProfile())
                .roles(save.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .build();
    }

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var response = userRepository.findUserByEmail(request.getEmail());

        /*authResponse.setFirstName(response.getFirstname());
        authResponse.setLastName(response.getLastname());
        authResponse.setEmail(response.getEmail());
        authResponse.setProfile(response.getProfile());
        authResponse.setRoles(response.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
*/
        String generateToken = response.getPassword();
        return AuthResponse.builder()
                .firstName(response.getFirstname())
                .lastName(response.getLastname())
                .email(response.getEmail())
                .profile(response.getProfile())
                .roles(response.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .token(generateToken)
                .build();
    }
}
