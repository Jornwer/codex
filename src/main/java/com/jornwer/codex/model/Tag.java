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
@Table(
        indexes = @Index(columnList = "name", name = "tag_name_index")
)
public class Tag {
    @Id
    @ApiModelProperty("1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty("wood")
    @Column(nullable = false, unique = true)
    private String name;
}
