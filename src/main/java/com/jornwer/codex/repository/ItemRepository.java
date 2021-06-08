package com.jornwer.codex.repository;

import com.jornwer.codex.model.Item;
import com.jornwer.codex.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);

    @Query("select i from Item i " +
            "where i.description like %:description% " +
            "and :tagsSize = (select count(t) from i.tags t where t in :tags) ")
    List<Item> findByDescriptionAndTags(@Param("description") String description,
                                        @Param("tags") Set<Tag> tags,
                                        @Param("tagsSize") long size);
}
