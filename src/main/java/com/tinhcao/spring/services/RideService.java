package com.tinhcao.spring.services;

import java.util.List;

import com.tinhcao.spring.entity.Ride;

public interface RideService {
	Ride createRide(Ride ride);

	Ride getRide(int id);

	List<Ride> getAllRides();

	Ride updateRide(Ride ride);

	boolean deleteRide(int id);
}
