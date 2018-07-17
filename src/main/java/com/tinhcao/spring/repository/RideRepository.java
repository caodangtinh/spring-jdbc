package com.tinhcao.spring.repository;

import com.tinhcao.spring.entity.Ride;

import java.util.List;

public interface RideRepository {
    /**
     * createRide
     *
     * @param ride ride
     * @return Ride
     */
    Ride createRide(Ride ride);

    /**
     * getRide
     *
     * @param id id
     * @return Ride
     */
    Ride getRide(int id);

    /**
     * getAllRides
     *
     * @return List<Ride>
     */
    List<Ride> getAllRides();

    /**
     * updateRide
     *
     * @param ride ride
     * @return Ride
     */
    Ride updateRide(Ride ride);

    /**
     * deleteRide
     *
     * @param id id
     * @return boolean
     */
    boolean deleteRide(int id);
}
