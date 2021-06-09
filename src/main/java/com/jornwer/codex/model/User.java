package com.jornwer.codex.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @ApiModelProperty("1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty("admin")
    private String username;

    @ApiModelProperty("admin")
    private String password;

    @ApiModelProperty("ipbbbeybkesuswvrkg@wqcefp.com")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
