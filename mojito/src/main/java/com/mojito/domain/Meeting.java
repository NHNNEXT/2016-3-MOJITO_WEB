package com.mojito.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	public int capacity;
	
	public int curParticipantNum = 0;
	
	@Lob
	public String contents;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 자세히 보기 페이지를 위해 participants 정보가 필요하긴 한데 jackson infinite recursive json serialize를 일으킬 것 같다..... 
	private Set<User> participants;
	
	private LocalDateTime createDate;
		
	public Meeting(){}
	
	public Meeting(User writer, String location, int capacity, String contents) {
		this.writer = writer;
		this.location = location;
		this.capacity = capacity;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
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

	public void setMeetingDate(String day, String time) {
		this.meetingDate = LocalDateTimeConverter.timeToStringConverter(day + " " + time);
	}
	
	public String getFormattedMeetingDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return meetingDate.format(formatter);
	}
	
	public void setExpireDate(String day, String bomb_time) {
		this.expireDate = LocalDateTimeConverter.timeToStringConverter(day + " " + bomb_time);
	}
	
	public Set<User> getParticipants() {
		return participants;
	}
	
	public void joinMeeting(User user) {
		if (capacity <= curParticipantNum) {
			throw new IllegalStateException("meeting capacity is full");
		}
		
		if (participants.contains(user)) {
			participants.remove(user);
			curParticipantNum--;
		} else {
			participants.add(user);
			curParticipantNum++;
		}
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
				+ expireDate + ", location=" + location + ", capacity=" + capacity + ", current_participants="
				+ curParticipantNum + ", contents=" + contents + ", createDate=" + createDate + "]";
	}
}
