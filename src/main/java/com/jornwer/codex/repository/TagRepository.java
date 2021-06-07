package com.jornwer.codex.repository;

import com.jornwer.codex.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);
    Tag findByName(String name);
}
