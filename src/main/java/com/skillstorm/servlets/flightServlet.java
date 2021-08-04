package com.skillstorm.servlets;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skillstorm.DAO.FlightsDAO;
import com.skillstorm.beans.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.service.flightService;



@WebServlet("/api/flight")
public class flightServlet extends HttpServlet{
	
	private flightService service;
	FlightsDAO dao = new FlightsDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("id") != null) {
			System.out.println("Testing..");
			String param = req.getParameter("id");
			System.out.println("Testing1");
			int id = Integer.parseInt(param);
			System.out.println("Testing3");
		 	//Flight flight = service.retrieveFlight(id);
			Flight flight = dao.findbyId(id);
		 	System.out.println("Testing2");
			String json = new ObjectMapper().writeValueAsString(flight);
			System.out.println("Testing3");
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.getWriter().print(json);
			resp.setStatus(200);
		}else {
			//Set<Flight> flights = service.retrieveAllFlights();
			Set<Flight> flights = dao.findAll();
			String json = new ObjectMapper().writeValueAsString(flights);
			resp.getWriter().print(json);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream requestBody = req.getInputStream();
		Flight flight = new ObjectMapper().readValue(requestBody, Flight.class);
		System.out.println(flight);
		//Flight updated = service.createFlight(flight);
		Flight updated = dao.create(flight);
		resp.getWriter().print(new ObjectMapper().writeValueAsString(updated));
		resp.setStatus(201); // "return"
		resp.setContentType("application/json");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param1 = req.getParameter("id");
		String param2 = req.getParameter("airline");
		String param3 = req.getParameter("fid");
		//InputStream requestBody = req.getInputStream();
		//Flight flight = new ObjectMapper().readValue(requestBody, Flight.class);
		int id = Integer.parseInt(param1);
		Flight updated = dao.updateFlightNumber(param3, param2, id );
		resp.getWriter().print(new ObjectMapper().writeValueAsString(updated));
		resp.setStatus(201); // "return"
		resp.setContentType("application/json");
	}
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("id") != null) {
			String param = req.getParameter("id");
			int id = Integer.parseInt(param);
			//service.deleteFlight(id);
			dao.deleteByFlightNumber(id);
			resp.setStatus(200);
		}
	}
	
}
