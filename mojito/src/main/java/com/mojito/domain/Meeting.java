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
	
	public Date date;
	
	public Time time;

	public Time bomb_time;
	
	public String location;
	
	public int capacity;
	
	@Lob
	public String contents;
	
	private LocalDateTime createDate;
	
	public Meeting(){}
	
	public Meeting(User writer, Date date, Time time, Time bomb_time, String location, int capacity, String contents,
			LocalDateTime createDate) {
		this.writer = writer;
		this.date = date;
		this.time = time;
		this.bomb_time = bomb_time;
		this.location = location;
		this.capacity = capacity;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}

	public String getFormattedCreateDate() {
		if (createDate == null){
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Time getBomb_time() {
		return bomb_time;
	}

	public void setBomb_time(Time bomb_time) {
		this.bomb_time = bomb_time;
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
		return "Meeting [id=" + id + ", writer=" + writer + ", date=" + date + ", time=" + time + ", bomb_time="
				+ bomb_time + ", location=" + location + ", capacity=" + capacity + ", contents=" + contents
				+ ", createDate=" + createDate + "]";
	}

}
