package com.example.workwave.services;


import com.example.workwave.entities.JwtRequest;
import com.example.workwave.entities.JwtResponse;
import com.example.workwave.entities.User;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    JwtUtil jwtUtil;
    UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        Optional<User> userOptional = userRepository.findById(userName);

        if (userOptional.get().getToken() != null) {
            // If the user already has a token, return it
            System.out.println("user not logged in");
            return ResponseEntity.status(403).body("Account not activated");
        } else {
            String userPassword = jwtRequest.getPassword();

            User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
            authenticate(userName, userPassword);

            System.out.println("user  logged in");

            UserDetails userDetails = loadUserByUsername(userName);
            String newGeneratedToken = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(user, newGeneratedToken));
        }
    }

    public ResponseEntity<?> createJwtTokenFace(String userName) throws Exception {
        Optional<User> userOptional = userRepository.findById(userName);

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("user  logged in");

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(user, newGeneratedToken));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
