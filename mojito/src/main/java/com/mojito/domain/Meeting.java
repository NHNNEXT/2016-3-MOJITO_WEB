package com.mojito.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import com.mojito.utils.DateTimeUtils;
import com.mojito.web.LocalDateTimeConverter;

@Entity
public class Meeting {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;

	public LocalDateTime meetingDate;

	public LocalDateTime expireDate;

	public String location;
	
	public String locationCoordinates;

	public int capacity;

	@Lob
	public String contents;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 자세히 보기
	private Set<User> participants = new HashSet<User>();

	private LocalDateTime createDate;

	public Meeting() {
	}

	public Meeting(User writer, String location, String locationCoordinates, int capacity, String contents) {
		this.writer = writer;
		this.location = location;
		this.locationCoordinates = locationCoordinates;
		this.capacity = capacity;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}
	
	

	public Long getId() {
		return id;
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

	public String getLocationCoordinates() {
		return locationCoordinates;
	}

	public void setLocationCoordinates(String locationCoordinates) {
		this.locationCoordinates = locationCoordinates;
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
		this.participants.add(writer);
//		writer.joinMeeting(this);
	}

	public void setMeetingDate(String day, String time) {
		this.meetingDate = LocalDateTimeConverter.timeToStringConverter(day + " " + time);
	}

	public String getFormattedMeetingDate() {
		return DateTimeUtils.formatDate(meetingDate);
	}

	public void setExpireDate(String day, String bomb_time) {
		this.expireDate = LocalDateTimeConverter.timeToStringConverter(day + " " + bomb_time);
	}

	public String getFormattedExpireDate() {
		return DateTimeUtils.formatDate(expireDate);
	}

	public Set<User> getParticipants() {
		return participants;
	}
	
	public int getParticipantSize() {
		return participants.size();
	}

	public void join(User user) {
		if (capacity <= participants.size()) {
			throw new IllegalStateException("meeting capacity is full");
		}
		participants.add(user);
	}

	public void cancel(User user) {
		participants.remove(user);
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
		return "Meeting [id=" + id + ", writer=" + writer + ", meeting_date=" + meetingDate + ", expire_date="
				+ expireDate + ", location=" + location + ", capacity=" + capacity + ", contents=" + contents + ", createDate=" + createDate + "]";
	}
}
