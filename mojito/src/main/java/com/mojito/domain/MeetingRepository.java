package com.mojito.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long>{
	Meeting findByWriter(User writer);
}
