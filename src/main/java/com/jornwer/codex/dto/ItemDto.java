package com.jornwer.codex.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ItemDto {
    private String name;
    private String description;
    public Set<String> tags;
}
