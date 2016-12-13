package com.mojito.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserEmail(String userEmail);
	List<User> findByUserName(String userName);
}
