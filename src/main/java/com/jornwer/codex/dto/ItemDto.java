package com.jornwer.codex.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    @ApiModelProperty("Table")
    private String name;

    @ApiModelProperty("Table from IKEA")
    private String description;

    @ApiModelProperty("[\"table\", \"wood\", \"organic\"]")
    public Set<String> tags;
}
