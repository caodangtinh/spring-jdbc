package com.tinhcao.spring.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
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
	private JdbcTemplate jdbcTemplate;

	@Override
	public Ride createRide(Ride ride) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preparedStatement = con
						.prepareStatement("insert into ride(name, duration) values (?,?)", new String[] { "id" });
				preparedStatement.setString(1, ride.getName());
				preparedStatement.setInt(2, ride.getDuration());
				return preparedStatement;
			}
		}, keyHolder);
		Number id = keyHolder.getKey();
		return this.getRide(id.intValue());
	}

	@Override
	public Ride getRide(int id) {
		return jdbcTemplate.queryForObject("select * from ride where id = ?", new RideRowMapper(), id);
	}

	@Override
	public List<Ride> getAllRides() {
		return jdbcTemplate.query("select * from ride where id > 10", new RideRowMapper());
	}

	@Override
	public Ride updateRide(Ride ride) {
		jdbcTemplate.update("update ride set name = ?, duration = ? where id = ?", ride.getName(), ride.getDuration(),
				ride.getId());
		return ride;
	}

	@Override
	public boolean deleteRide(int id) {
		return jdbcTemplate.update("delete from ride where id = ?", id) != 0;
	}

}
