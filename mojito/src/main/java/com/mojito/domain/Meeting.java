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
	
	public LocalDateTime date;
	
	public Time time;

	public Time bomb_time;
	
	public String location;
	
	public int capacity;
	
	public int current_participants;
	
	@Lob
	public String contents;
	
	private LocalDateTime createDate;
	
	public Meeting(){}
	
	public Meeting(User writer, String location, int capacity, String contents) {
		this.writer = writer;
		this.date = date;
		this.time = time;
		this.bomb_time = bomb_time;
		this.location = location;
		this.capacity = capacity;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}

	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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
		return "Meeting [id=" + id + ", writer=" + writer + ", date=" + date + ", location=" + location + ", capacity="
				+ capacity + ", contents=" + contents + ", createDate=" + createDate + "]";
	}
}
