package com.Ganesh.SpringBoot_Tutorial.repository;

import com.Ganesh.SpringBoot_Tutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
