package com.example.habit_tracker_api.repository;

import com.example.habit_tracker_api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAll();
    void deleteById(Long id);
}
