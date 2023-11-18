package com.fsd9.ecom.modules.user.service;

import com.fsd9.ecom.common.bean.JwtRequest;
import com.fsd9.ecom.modules.user.dto.req.UserRegisterReqDto;
import com.fsd9.ecom.modules.user.model.EOUser;
import com.fsd9.ecom.modules.user.model.EOUserRole;
import com.fsd9.ecom.modules.user.repositories.EORoleRepository;
import com.fsd9.ecom.modules.user.repositories.EOUserRepository;
import com.fsd9.ecom.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EOUserService {

    @Autowired
    private EOUserRepository userRepository;

    @Autowired
    private EORoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EOUser createNewUser(UserRegisterReqDto reqDto) {
        EOUser eoUser = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(reqDto.getDob(), formatter);
            eoUser = EOUser.builder()
                    .firstName(reqDto.getFirstName())
                    .lastName(reqDto.getLastName())
                    .email(reqDto.getEmail())
                    .password(passwordEncoder.encode(reqDto.getPassword()))
                    // .role(reqDto.getRole())
                    .dob(date).build();
            EOUserRole eoUserRole = new EOUserRole();
            eoUserRole.setEoUser(eoUser);
            String roleName = reqDto.getRole();
            if (roleName == null) {
                roleName = "ROLE_USER";
            }
            eoUserRole.setEoRole(roleRepository.findByName(roleName));
            eoUser.setEoUserRoleArray(Collections.singleton(eoUserRole));
            userRepository.save(eoUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eoUser;
    }

    public List<EOUser> getAllUsers() {
        return userRepository.findAll();
    }

    public EOUser authenticate(JwtRequest request) {
        EOUser eoUser = this.userRepository.getUserByEmail(request.getUsername());
        if (eoUser == null) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        boolean isAuthenticated = passwordEncoder.matches(request.getPassword(), eoUser.getPassword());
        if (!isAuthenticated) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        if (!eoUser.getEoUserRoleArray().stream().findFirst().get().getEoRole().getName()
                .equalsIgnoreCase(request.getRole())) {
            throw new UsernameNotFoundException("Invalid Role");
        }
        return eoUser;
    }

    public Optional<EOUser> findById(Long id) {
        return this.userRepository.findById(id);
    }
}
