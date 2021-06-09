package com.jornwer.codex.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        indexes = @Index(columnList = "name", name = "item_name_index")
)
public class Item {
    @Id
    @ApiModelProperty("1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty("Table")
    @Column(nullable = false, unique = true)
    private String name;

    @ApiModelProperty("Table from IKEA")
    @Column(nullable = false)
    private String description;

    @ManyToMany
    private Set<Tag> tags;
}
