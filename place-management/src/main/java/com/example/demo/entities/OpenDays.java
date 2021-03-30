package com.example.demo.entities;

import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenDays {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private DayOfWeek dayOfWeek;  // Oblige users to save a real day
	private String openingHoursMorning;
	private String openingHoursEvening;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Place place;
}
