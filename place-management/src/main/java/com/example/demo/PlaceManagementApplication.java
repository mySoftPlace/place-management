package com.example.demo;

import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.dao.OpenDaysRepository;
import com.example.demo.dao.PlaceRepository;
import com.example.demo.entities.OpenDays;
import com.example.demo.entities.Place;

@SpringBootApplication
public class PlaceManagementApplication implements CommandLineRunner {
	@Autowired
	PlaceRepository placesRepository;
	
	@Autowired
	OpenDaysRepository openHoursRepository;
	
	@Autowired
	RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(PlaceManagementApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		Place place1 = placesRepository.save(new Place(null, "Le Café du Marché","Rue de Conthey 17, 1950 Sion", null));
		Place place2 = placesRepository.save(new Place(null, "Great stadium","Blauacker 81, 8051 Zurich", null));
		Place place3 = placesRepository.save(new Place(null, "Hotel Palace","Bahnhofstrasse 84, 8010 Zurich", null));
		
		openHoursRepository.save(new OpenDays(null, DayOfWeek.TUESDAY, "10:30 - 14:00", "17:30 - 22:00", place1));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.WEDNESDAY, "11:30 - 15:00", "18:30 - 23:00", place1));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.THURSDAY, "09:30 - 15:00", "16:30 - 00:00", place1));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.FRIDAY, "09:00 - 13:00", "18:30 - 00:00", place1));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.SATURDAY, "08:00 - 13:00", "14:30 - 19:00", place1));
		
		openHoursRepository.save(new OpenDays(null, DayOfWeek.WEDNESDAY, "20:30 - 23:00", null, place2));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.THURSDAY, "10:30 - 17:00", null, place2));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.FRIDAY, "18:30 - 00:00", null, place2));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.SATURDAY, "07:00 - 14:00", null, place2));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.SUNDAY, "09:00 - 17:30", null, place2));
		
		openHoursRepository.save(new OpenDays(null, DayOfWeek.MONDAY, "10:00 - 13:00", "14:30 - 19:00", place3));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.WEDNESDAY, "10:30 - 17:00", null, place3));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.FRIDAY, "18:30 - 00:00", null, place3));
		openHoursRepository.save(new OpenDays(null, DayOfWeek.SUNDAY, "11:30 - 15:00", null, place3));
	}
}
