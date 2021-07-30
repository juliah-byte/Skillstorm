package com.skillstorm.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.skillstorm.beans.Flight;

public class FlightsDAO {

	public final static String url = "jdbc:mysql://localhost:3306/flight-api";
	public final static String username = "root";
	public final static String password = "root";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("Something bad happened while loading the driver");
			e.printStackTrace();
		}
	}

	public Flight create(Flight flight) {
		
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "insert into flights(AIRLINE, ORIGIN, DESTINATION, DEPARTURE_TIME, ARRIVAL_TIME, FLIGHT_NUMBER)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, flight.getAirline());
			stmt.setString(2, flight.getFromAirport());
			stmt.setString(3, flight.getToAirport());
			stmt.setString(4, flight.getDeparture());
			stmt.setString(5, flight.getArrival());
			stmt.setString(6, flight.getAirline());
			stmt.executeUpdate();
			
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);
			flight.setId(id);
			
		}catch(SQLException e) {
			e.printStackTrace();
			//rethrow
			
		}
		return flight;
	}
	
	public void update(Flight flight) {
		
	}
	
	public void delete(Flight flight) {
		
	}
	
	public Flight findbyId(int id) {
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "select * from flights where FLIGHT_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return new Flight(rs.getInt("FLIGHT_ID"), rs.getString("AIRLINE"), 
						rs.getString("ORIGIN"), rs.getString("DESTINATION"), rs.getString("DEPARTURE_TIME"),
						rs.getString("ARRIVAL_TIME"), rs.getString("FLIGHT_NUMBER"));
				
			}catch(Exception e) {
			
			e.printStackTrace();
			return null;
		}	

	}
	
	public Set<Flight> findAll(){
		Set<Flight> results = new HashSet<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "select * from flights";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String airline = rs.getString("AIRLINE");
				String origin = rs.getString("ORIGIN");
				String destination = rs.getString("DESTINATION");
				String departure_time = rs.getString("DEPARTURE_TIME");
				String arrival_time = rs.getString("ARRIVAL_TIME");
				String flight_number = rs.getString("FLIGHT_NUMBER");
				Flight flight = new Flight(airline, origin, destination, departure_time, arrival_time, flight_number);
				results.add(flight);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}	

}