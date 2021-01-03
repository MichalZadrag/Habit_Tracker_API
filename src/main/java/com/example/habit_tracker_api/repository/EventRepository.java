package com.example.habit_tracker_api.repository;

import com.example.habit_tracker_api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
