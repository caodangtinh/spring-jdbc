package com.tinhcao.spring.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinhcao.spring.entity.Ride;
import com.tinhcao.spring.repository.RideRepository;
import com.tinhcao.spring.services.RideService;

@Service
public class RideServiceImpl implements RideService {

	@Autowired
	private RideRepository rideRepository;

	@Override
	public Ride createRide(Ride ride) {
		return rideRepository.createRide(ride);
	}

	@Override
	public Ride getRide(int id) {
		return rideRepository.getRide(id);
	}

	@Override
	public List<Ride> getAllRides() {
		return rideRepository.getAllRides();
	}

	@Override
	public Ride updateRide(Ride ride) {
		return rideRepository.updateRide(ride);
	}

	@Override
	public boolean deleteRide(int id) {
		return rideRepository.deleteRide(id);
	}

}
