package com.jornwer.codex.service;

import com.jornwer.codex.model.Tag;

import java.util.Set;

public interface TagService {
    Set<Tag> mapTags(Set<String> names);
}
