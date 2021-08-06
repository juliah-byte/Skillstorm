package com.skillstorm.data;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;




import com.skillstorm.DAO.FlightsDAO;
import com.skillstorm.beans.Flight;

public class JUnitTest {
	
	FlightsDAO dao = new FlightsDAO();
	static Flight flight;
	
	
	@BeforeClass
	public static void setupBeforeClass() {
		
		flight = new Flight("test", "test1", "test2", "test3", "test4", "test5");
	}
	
	@Test
	public void testFindbyId() {
		Flight flight = dao.findbyId(1);
		
		assertEquals("SOUTHWEST", flight.getAirline());
		assertEquals("JFK,  New-York, NY", flight.getFromAirport());
		assertEquals("DCA, Arlington, VA", flight.getToAirport());
		assertEquals("545PM", flight.getDeparture());
		assertEquals("115PM", flight.getArrival());
		assertEquals("WN380", flight.getFlightNumber());

	}
	
	
	@Test
	public void testFindAll() {
	 Boolean isPopulated = dao.findAll().size() > 0 ? true:false;
	 assertTrue(isPopulated);

	}
	
	
	@Test
	public void testCreate() throws SQLException {
		
		//Flight flight = new Flight("test", "test1", "test2", "test3", "test4", "test5");
		Flight flight2 = dao.create(flight);
		assertEquals(flight, flight2);
		
		
	}
	
	
	@Test
	public void testDeletebyId() {
		dao.deleteByFlightNumber(25);
		assertNull(dao.findbyId(25));
	}
	
	@After
	public void teardown() {
		
	}
	
	

}
