package com.jornwer.codex.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {
    @ApiModelProperty("admin")
    private String username;

    @ApiModelProperty("admin")
    private String password;

    @ApiModelProperty("ipbbbeybkesuswvrkg@wqcefp.com")
    private String email;
}
