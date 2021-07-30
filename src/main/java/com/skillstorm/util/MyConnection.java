package com.skillstorm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.skillstorm.beans.Flight;

public class MyConnection {
	
	static String url = "jdbc:mysql://localhost:3306/flight-api";
	static String username = "root";
	static String password = "root";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Flight flight = new Flight("JETBLUE", "JFK", "MSN", "821PM", "1155PM", "DL5841");
		createFlight(flight);
	
	}
	
	public static void createFlight(Flight flight) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,username, password);
		
		String sql = "insert into flights(AIRLINE, ORIGIN, DESTINATION, DEPARTURE_TIME, ARRIVAL_TIME, FLIGHT_NUMBER)"
				+ " values ('"+ flight.getAirline()+ "','" + flight.getFromAirport() + "', '" + flight.getToAirport() + "','" + flight.getDeparture() + "','" + flight.getArrival() + "','" + flight.getFlightNumber() + "')";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		
		conn.close();
	}
 
}
