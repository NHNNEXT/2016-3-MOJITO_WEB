package com.mojito.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


@Entity
public class Meeting {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;
	
	public String meeting_date;
	
	public String meeting_time;

	public String bomb_time;
	
	public String meeting_location;
	
	public int meeting_capacity;
	
	public int current_participants;
	
	@Lob
	public String meeting_detail;
	
	private LocalDateTime createDateTime;
	
	public Meeting(){}
	
	public Meeting(User writer, String meeting_date, String meeting_time, String bomb_time, 
			String meeting_location, int meeting_capacity, String meeting_detail) {
		this.writer = writer;
		this.meeting_date = meeting_date;
		this.meeting_time = meeting_time;
		this.bomb_time = bomb_time;
		this.meeting_location = meeting_location;
		this.meeting_capacity = meeting_capacity;
		this.meeting_detail = meeting_detail;
		this.createDateTime = LocalDateTime.now();
	}
	
	public void setWriter(User writer) {
		this.writer = writer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meeting other = (Meeting) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + ", writer=" + writer + ", meeting_date=" + meeting_date + ", meeting_time="
				+ meeting_time + ", bomb_time=" + bomb_time + ", meeting_location=" + meeting_location
				+ ", meeting_capacity=" + meeting_capacity + ", current_participants=" + current_participants
				+ ", meeting_detail=" + meeting_detail + ", createDateTime=" + createDateTime + "]";
	}

	

	
}
