package com.mojito.domain;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserEmail(String userEmail);
	Set<User> findByUserName(String userName);
}
