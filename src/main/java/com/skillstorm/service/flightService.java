package com.skillstorm.service;

import java.sql.SQLException;
import java.util.Set;

import com.skillstorm.DAO.FlightsDAO;
import com.skillstorm.beans.Flight;


public class flightService {
	
	
	FlightsDAO dao = new FlightsDAO();
	
	/**public void createFlight(Flight flight) {
		dao.create(flight); }*/
		
	public void updateFlight() {
		
	}
	
	public void deleteFlight(int id) {
		dao.deleteByFlightNumber(id);
	}
	
	public Flight retrieveFlight(int id){
		return dao.findbyId(id);
	}
	
	public Set<Flight> retrieveAllFlights() {
		return dao.findAll();
	}

}
