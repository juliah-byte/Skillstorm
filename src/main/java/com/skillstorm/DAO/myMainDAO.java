package com.skillstorm.DAO;

//import java.util.Set;

import com.skillstorm.beans.Flight;

public class myMainDAO {
	
	public static void main(String[] args) {
		
		FlightsDAO dao = new FlightsDAO();
		/**Flight updated = dao.create(new Flight("United", "FAT, Fresno-Yosemite-International, Fresno, CA", "PHX, Phoenix-Sky-Harbor, Phoenix, AZ", "522AM", "819AM","UA560"));
		
		System.out.println(updated.getId());
		
		Set<Flight> flight = dao.findAll();
		System.out.println(flight);*/
		
		Flight flight = dao.findbyId(7);
		System.out.println(flight);
		
	}

}
