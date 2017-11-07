package com.tinhcao.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tinhcao.spring.entity.Ride;
import com.tinhcao.spring.services.RideService;

@RestController(value = "rideController")
@RequestMapping(value = "ride_tracker")
public class RideController {
	@Autowired
	private RideService rideService;

	@PostMapping(value = "/add_ride")
	@ResponseBody
	public ResponseEntity<?> createRide(@RequestBody Ride ride) {
		try {
			Ride createdRide = rideService.createRide(ride);
			return new ResponseEntity<Ride>(createdRide, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error when creating Ride with id {} " + ride.getId(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/ride/{id}")
	@ResponseBody
	public ResponseEntity<?> getRide(@PathVariable(name = "id") Integer id) {
		try {
			Ride createdRide = rideService.getRide(id);
			return new ResponseEntity<Ride>(createdRide, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error when get Ride with id {} " + id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/rides")
	@ResponseBody
	public ResponseEntity<?> getAllRide() {
		try {
			List<Ride> rides = rideService.getAllRides();
			if (CollectionUtils.isEmpty(rides)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Ride>>(rides, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error when get all Ride ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/ride")
	@ResponseBody
	public ResponseEntity<?> updateRide(@RequestBody Ride ride) {
		try {
			Ride updatedRide = rideService.updateRide(ride);
			return new ResponseEntity<Ride>(updatedRide, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error when update Ride with id {} " + ride.getId(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/ride/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteRide(@PathVariable(name = "id") Integer id) {
		try {
			if (rideService.deleteRide(id)) {
				return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error when delete Ride with id {} " + id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
