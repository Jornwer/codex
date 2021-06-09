package com.jornwer.codex.dto;

import com.jornwer.codex.model.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    @ApiModelProperty("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMzE4NDQ4MH0.UHJf5H7crfANlsUtREi4VyyXY9iLIO74RnCly7GgQ-E")
    private String authenticationToken;

    @ApiModelProperty("admin")
    private String username;

    private Role role;
}
