package com.jornwer.codex.service.impl;

import com.jornwer.codex.model.Tag;
import com.jornwer.codex.repository.TagRepository;
import com.jornwer.codex.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public Set<Tag> mapTags(Set<String> names) {
        if (names == null) {
            return new HashSet<>();
        }
        return names.stream()
                .map(this::mapTag)
                .collect(Collectors.toSet());
    }

    private Tag mapTag(String name) {
        if (tagRepository.existsByName(name)) {
            return tagRepository.findByName(name);
        }
        Tag tag = Tag.builder()
                        .name(name)
                        .build();
        return tagRepository.save(tag);
    }
}
