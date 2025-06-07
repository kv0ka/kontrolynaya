package com.example.controsha.repository;

import com.example.controsha.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    @Query(value = "SELECT * FROM quests WHERE JSON_CONTAINS(personId, :userId, '$')", nativeQuery = true)
    List<Quest> findByPersonIdContains(@Param("userId") String userId);
} 