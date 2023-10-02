package com.fsd9.ecom.controller;

import com.fsd9.ecom.common.dto.req.UserRegisterReqDto;
import com.fsd9.ecom.common.model.EOUser;
import com.fsd9.ecom.common.repositories.EOUserRepository;
import com.fsd9.ecom.common.service.EOUserService;
import com.fsd9.ecom.security.JwtUtil;
import com.fsd9.ecom.common.bean.JwtRequest;
import com.fsd9.ecom.common.bean.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
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

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            JwtResponse response = JwtResponse.builder()
                    .jwtToken(jwtUtil.generateToken(userDetails.getUsername()))
                    .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<EOUser> register(@RequestBody UserRegisterReqDto request) {
        EOUser eoUser = userRepository.getUserByEmail(request.getEmail());

        if(eoUser == null){
            eoUser = this.eoUserService.createNewUser(request);
            return new ResponseEntity<>(eoUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(eoUser, HttpStatus.OK);
        }


    }

    @GetMapping("/user")
    public String users () {
      return "THis istest use...";
    }
}