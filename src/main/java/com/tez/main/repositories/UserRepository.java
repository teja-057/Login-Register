package com.tez.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tez.main.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
