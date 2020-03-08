package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AirlineRepo;
import com.example.demo.dao.RouteRepo;
import com.example.demo.model.Airline;
import com.example.demo.model.Route;
import com.example.demo.dao.FlightRepo;
import com.example.demo.model.Flight;

@RestController
public class FlightController
{
	@Autowired
	FlightRepo flightRepo;
	
	@Autowired
	RouteRepo routeRepo;
	
	@Autowired
	AirlineRepo airlineRepo;
	
	@PostMapping(path="/flight")
	public Flight addFlight(@RequestBody Map<String, Object> flightJson, HttpServletResponse response)
	{
		int route_id = (int) flightJson.get("route_id");
		Route route = routeRepo.findById(route_id);
		int airline_id = (int) flightJson.get("airline_id");
		Airline airline = airlineRepo.findById(airline_id);
		if (route == null || airline == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		int capacity = (int) flightJson.get("capacity");
		int basePrice = (int) flightJson.get("base_price");
		
		Flight flight = new Flight();
		flight.setRoute(route);
		flight.setAirline(airline);
		flight.setCapacity(capacity);
		flight.setFullSeats(0);
		flight.setBasePrice(basePrice);
		flightRepo.save(flight);
		return flight;
	}
	
	@GetMapping(path="/flight")
	public List<Flight> getAllFlights()
	{
		return flightRepo.findAll();
	}
	
	@GetMapping("/flight/{id}")
	public Flight getFlight(@PathVariable int id, HttpServletResponse response)
	{
		Flight flight = flightRepo.findById(id);
		if (flight == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return flight;
	}
	
}