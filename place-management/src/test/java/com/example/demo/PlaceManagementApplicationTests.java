package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.PlaceRepository;
import com.example.demo.entities.OpenDays;
import com.example.demo.entities.Place;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class PlaceManagementApplicationTests {
	
	public static String URL_1 = "http://localhost:8080/places/1";
	public static String URL_2 = "http://localhost:8080/places/2";
	public static String URL_3 = "http://localhost:8080/places/3";
	public static String WRONG_URL = "http://localhost:8080/places/p";

	@MockBean
	private PlaceRepository placesRepository;
	
	Optional<Place> expectedPlace1;
	Optional<Place> expectedPlace2;

	@BeforeEach
	public void setUp() {
		Place place1 = new Place(1L, "Le Café du Marché","Rue de Conthey 17, 1950 Sion", null);
		Place place2 = new Place(2L, "Zurich Stadium","Bahnhofstrasse 48, 8001 Zurich", null);
		
		OpenDays openHours1 = new OpenDays(1L, DayOfWeek.TUESDAY, "10:30 - 12:00", "13:00 - 19:00", place2);
		OpenDays openHours3 = new OpenDays(3L, DayOfWeek.WEDNESDAY, "07:30 - 14:00", "15:00 - 00:00", place1);
		OpenDays openHours5 = new OpenDays(5L, DayOfWeek.THURSDAY,"06:00 - 18:00", null, place2);
		OpenDays openHours6 = new OpenDays(6L, DayOfWeek.FRIDAY, "10:00 - 12:00", null, place1);
		
		Collection<OpenDays> col1 = new HashSet<OpenDays>();
		col1.add(openHours3);
		col1.add(openHours6);
		
		Collection<OpenDays> col2 = new HashSet<OpenDays>();
		col1.add(openHours1);
		col1.add(openHours5);
		
		expectedPlace1 = Optional.ofNullable(place1);
		expectedPlace1.get().setOpenDays(col1);
		
		expectedPlace2 = Optional.ofNullable(place2);
		expectedPlace2.get().setOpenDays(col2);
		
		Mockito.when(placesRepository.findById(1L)).thenReturn(expectedPlace1);
		Mockito.when(placesRepository.findById(2L)).thenReturn(expectedPlace2);
	}

	@Test
	public void testFindPlaceById() {
		Optional<Place> correspondingPlace1 = placesRepository.findById(1L);
		Optional<Place> correspondingPlace2 = placesRepository.findById(2L);

		assertNotNull(correspondingPlace1);

		assertEquals(expectedPlace1.get().getId(), correspondingPlace1.get().getId());
		assertEquals(expectedPlace1.get().getName(), correspondingPlace1.get().getName());
		assertEquals(expectedPlace1.get().getOpenDays().size(), correspondingPlace1.get().getOpenDays().size());
		
		assertNotNull(correspondingPlace2);
		
		assertEquals(expectedPlace2.get().getId(), correspondingPlace2.get().getId());
		assertEquals(expectedPlace2.get().getName(), correspondingPlace2.get().getName());
		assertEquals(expectedPlace2.get().getOpenDays().size(), correspondingPlace2.get().getOpenDays().size());
	}

	// API Test: Verify status code
	@Test
	public void testAPIStatusCode() throws ClientProtocolException, IOException {
		// Place with id=1 exists
	    HttpUriRequest requestOK = new HttpGet(URL_1);
	    HttpResponse httpResponseOK = HttpClientBuilder.create().build().execute(requestOK);
	    assertEquals(200, httpResponseOK.getStatusLine().getStatusCode());
	    
	    // Place with id=4 doesn't exist
	    HttpUriRequest requestNonOK = new HttpGet(WRONG_URL);
	    HttpResponse httpResponseNonOK = HttpClientBuilder.create().build().execute(requestNonOK);
	    assertEquals(500, httpResponseNonOK.getStatusLine().getStatusCode());
	}
	
	// API Test: Verify Media type
	@Test
	public void testMediaType() throws ClientProtocolException, IOException {

	   String expectedMediaType = "application/hal+json";
	   HttpUriRequest req = new HttpGet(URL_2);
	   HttpResponse response = HttpClientBuilder.create().build().execute(req);
	   String currentMediaType = ContentType.getOrDefault(response.getEntity()).getMimeType();
	   assertEquals(expectedMediaType, currentMediaType);
	}
	
	@Test
    void contextLoads() {		
    }
}
