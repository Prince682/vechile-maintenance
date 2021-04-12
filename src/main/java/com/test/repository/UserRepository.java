package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByEmailId(String email);
	
	Optional<Users> findByUserId(Long userId);

}
