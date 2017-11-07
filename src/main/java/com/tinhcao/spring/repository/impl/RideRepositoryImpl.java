package com.tinhcao.spring.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhcao.spring.entity.Ride;
import com.tinhcao.spring.repository.RideRepository;
import com.tinhcao.spring.util.RideRowMapper;

@Transactional
@Repository
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Ride createRide(Ride ride) {
		BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(ride);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update("insert into ride(name,duration) values(:name,:duration)", beanPropertySqlParameterSource, keyHolder);
		ride.setId(keyHolder.getKey().intValue());
		return ride;
	}

	@Override
	public Ride getRide(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject("select * from ride where id = :id", mapSqlParameterSource, new RideRowMapper());
	}

	@Override
	public List<Ride> getAllRides() {
		return namedParameterJdbcTemplate.query("select * from ride where id > 10", new RideRowMapper());
	}

	@Override
	public Ride updateRide(Ride ride) {
		BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(ride);
		namedParameterJdbcTemplate.update("update ride set name = :name, duration = :duration where id = :id", beanPropertySqlParameterSource);
		return ride;
	}

	@Override
	public boolean deleteRide(int id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return namedParameterJdbcTemplate.update("delete from ride where id = :id", mapSqlParameterSource) != 0;
	}

}
