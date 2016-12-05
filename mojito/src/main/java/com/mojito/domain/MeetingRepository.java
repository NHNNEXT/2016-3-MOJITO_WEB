package com.mojito.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long>{
	List<Meeting> findByWriter(User writer);
}
