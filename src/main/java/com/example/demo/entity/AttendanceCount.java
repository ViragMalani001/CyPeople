package com.example.demo.entity;


import java.time.LocalDate;

import java.time.LocalTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="attendance_count")
public class AttendanceCount {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String name;		
		private LocalDate date;
		private LocalTime punchInTime;
		private LocalTime punchOutTime;
	
		public AttendanceCount() {
			super();
		}

	public AttendanceCount(int id, String name, LocalDate date,
				LocalTime punchInTime, LocalTime punchOutTime) {
			super();
			this.id = id;
			this.name = name;
			this.date = date;
			this.punchInTime = punchInTime;
			this.punchOutTime = punchOutTime;
		}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getPunchInTime() {
		return punchInTime;
	}
	
	public void setPunchInTime(LocalTime punchInTime) {
		this.punchInTime = punchInTime;
	}
	
	public LocalTime getPunchOutTime() {
		return punchOutTime;
	}
	
	public void setPunchOutTime(LocalTime punchOutTime) {
		this.punchOutTime = punchOutTime;
	}

	@Override
	public String toString() {
		return "AttendanceCount [id=" + id + ", name=" + name + ", date=" + date + ", punchInTime=" + punchInTime + ", punchOutTime=" + punchOutTime + "]";
	}

	
}
