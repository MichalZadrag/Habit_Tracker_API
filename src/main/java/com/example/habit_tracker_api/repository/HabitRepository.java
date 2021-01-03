package com.example.habit_tracker_api.repository;

import com.example.habit_tracker_api.model.Habit;
import com.example.habit_tracker_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

        List<Habit> findAll();

        void deleteHabitsByUserId(Long id);
        Boolean existsByHabitText(String habit_text);
        Boolean existsByHabitTextAndUser(String habit_text, User user);

}
