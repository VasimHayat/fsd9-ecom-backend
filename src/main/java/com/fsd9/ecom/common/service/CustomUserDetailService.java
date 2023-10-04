package com.fsd9.ecom.common.service;


import com.fsd9.ecom.modules.user.model.EOUser;
import com.fsd9.ecom.modules.user.repositories.EOUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private EOUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // In a real application, fetch user details from a database or other source
//        List<UserDetails> users = new ArrayList<>();
//        users.add(User.withUsername("admin")
//                .password(this.passwordEncoder().encode("admin"))
//                .roles("USER")
//                .build());
//
//        for (UserDetails user : users) {
//            if (user.getUsername().equals(username)) {
//                return user;
//            }
//        }

       EOUser eoUser = userRepository.getUserByEmail(email);
       if(eoUser== null){
           throw new UsernameNotFoundException("User not found with username: " + email);
       }else{
           return eoUser;
       }

    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
