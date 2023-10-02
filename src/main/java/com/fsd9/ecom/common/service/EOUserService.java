package com.fsd9.ecom.common.service;

import com.fsd9.ecom.common.dto.req.UserRegisterReqDto;
import com.fsd9.ecom.common.model.EOUser;
import com.fsd9.ecom.common.model.EOUserRole;
import com.fsd9.ecom.common.repositories.EORoleRepository;
import com.fsd9.ecom.common.repositories.EOUserRepository;
import com.fsd9.ecom.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EOUserService {


    @Autowired
    private EOUserRepository userRepository;

    @Autowired
    private EORoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EOUser createNewUser(UserRegisterReqDto reqDto){
        EOUser eoUser =null;
        try{
            eoUser = EOUser.builder()
                    .firstName(reqDto.getFirstName())
                    .lastName(reqDto.getLastName())
                    .email(reqDto.getEmail())
                    .password(passwordEncoder.encode(reqDto.getPassword()))
                    .dob(DateUtil.stringToLocalDate(reqDto.getDob())).build();
            System.out.println(eoUser);
            EOUserRole eoUserRole = new EOUserRole();
            eoUserRole.setEoUser(eoUser);
            eoUserRole.setEoRole(roleRepository.getUserRole());
            eoUser.setEoUserRoleArray(Collections.singleton(eoUserRole));

            System.out.println("##################### ");
            System.out.println(eoUser);
            System.out.println("##################### ");
            userRepository.save(eoUser);
        }catch (Exception e){
            e.printStackTrace();
        }


        return eoUser;
    }

    public List<EOUser> getAllUsers() {
        return userRepository.findAll();
    }
}
