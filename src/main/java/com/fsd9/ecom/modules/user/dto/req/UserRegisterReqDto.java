package com.fsd9.ecom.modules.user.dto.req;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterReqDto {
    private String firstName;
    private String lastName;
    private String dob;
    private String password;
    private String email;
    private String role = "ROLE_USER";
}
