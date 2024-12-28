package com.darfik.taskmanager.repository;

import com.darfik.taskmanager.entity.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TaskUser, Long> {

    Optional<TaskUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
