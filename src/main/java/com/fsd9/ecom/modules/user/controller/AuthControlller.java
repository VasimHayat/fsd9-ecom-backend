package com.fsd9.ecom.modules.user.controller;

import com.fsd9.ecom.common.bean.JwtRequest;
import com.fsd9.ecom.common.bean.JwtResponse;
import com.fsd9.ecom.modules.user.dto.req.UserRegisterReqDto;
import com.fsd9.ecom.modules.user.model.EOUser;
import com.fsd9.ecom.modules.user.repositories.EOUserRepository;
import com.fsd9.ecom.modules.user.service.EOUserService;
import com.fsd9.ecom.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthControlller {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private EOUserService eoUserService;

    @Autowired
    private EOUserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        EOUser eoUser = this.eoUserService.authenticate(request);


        JwtResponse response = JwtResponse.builder()
                .jwtToken(jwtUtil.generateToken(eoUser.getUsername()))
                .email(eoUser.getEmail())
                .firstName(eoUser.getFirstName())
                .lastName(eoUser.getLastName())
                .id(eoUser.getId())
                .username(eoUser.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<EOUser> register(@RequestBody UserRegisterReqDto request) {
        EOUser eoUser = userRepository.getUserByEmail(request.getEmail());

        if (eoUser == null) {
            try {
                eoUser = this.eoUserService.createNewUser(request);
                return new ResponseEntity<>(eoUser, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(eoUser, HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>(eoUser, HttpStatus.OK);
        }


    }

    @GetMapping("/user")
    public String users() {
        return "THis istest use...";
    }
}
