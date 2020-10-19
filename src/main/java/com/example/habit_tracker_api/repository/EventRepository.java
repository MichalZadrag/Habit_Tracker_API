package com.example.habit_tracker_api.repository;

import com.example.habit_tracker_api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

//    @Modifying
//    @Query("delete from Event e where e.user.id=:user_id")
//    void deleteEventsByUserId(@Param("user_id") Long user_id);
    void deleteEventsByUserId(Long id);

}
