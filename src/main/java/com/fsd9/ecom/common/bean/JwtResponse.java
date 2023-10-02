package com.fsd9.ecom.common.bean;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class JwtResponse {
    private String jwtToken;
    private String username;
}
