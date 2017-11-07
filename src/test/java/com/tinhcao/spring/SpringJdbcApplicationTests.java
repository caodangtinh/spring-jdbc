package com.tinhcao.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.tinhcao.spring.entity.Ride;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringJdbcApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int port;

	@Test
	public void testGetRides() {
		ResponseEntity<List<Ride>> responseEntity = restTemplate.exchange(
				"http://localhost:" + port + "/ride_tracker/rides", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = responseEntity.getBody();
		assertFalse(rides.isEmpty());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testGetRide() {
		ResponseEntity<Ride> responseEntity = restTemplate.exchange(
				"http://localhost:" + port + "/ride_tracker/ride/1", HttpMethod.GET, null,
				new ParameterizedTypeReference<Ride>() {
				});
		Ride rides = responseEntity.getBody();
		assertEquals(1, rides.getId());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testCreateRide() {
		Ride ride = new Ride();
		ride.setName("test create ride");
		ride.setDuration(30);
		HttpEntity<Ride> rideHttpEntity = new HttpEntity<Ride>(ride);
		ResponseEntity<Ride> responseEntity = restTemplate.exchange(
				"http://localhost:" + port + "/ride_tracker/add_ride", HttpMethod.POST, rideHttpEntity,
				new ParameterizedTypeReference<Ride>() {
				});
		Ride returnRide = responseEntity.getBody();
		assertNotNull(returnRide);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	public void testUpdateRide() {
		Ride ride = new Ride();
		ride.setId(10);
		ride.setName("test create ride");
		ride.setDuration(30);
		HttpEntity<Ride> rideHttpEntity = new HttpEntity<Ride>(ride);
		ResponseEntity<Ride> responseEntity = restTemplate.exchange("http://localhost:" + port + "/ride_tracker/ride",
				HttpMethod.PUT, rideHttpEntity, new ParameterizedTypeReference<Ride>() {
				});
		Ride returnRide = responseEntity.getBody();
		assertEquals(ride.getName(), returnRide.getName());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testDeleteRide() {
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
				"http://localhost:" + port + "/ride_tracker/ride/11", HttpMethod.DELETE, null,
				new ParameterizedTypeReference<Boolean>() {
				});
		Boolean success = responseEntity.getBody();
		if (success != null && success) {
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		} else {
			assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		}
	}

}
