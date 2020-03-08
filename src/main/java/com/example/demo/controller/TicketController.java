package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.TicketRepo;
import com.example.demo.model.Ticket;

import com.example.demo.dao.FlightRepo;
import com.example.demo.model.Flight;

@RestController
public class TicketController
{
	@Autowired
	FlightRepo flightRepo;
	
	@Autowired
	TicketRepo ticketRepo;
	
	@PostMapping(path="/ticket")
	public Ticket addTicket(@RequestBody Map<String, Object> ticketJson, HttpServletResponse response)
	{
		int flight_id = (int) ticketJson.get("flight_id");
		Flight flight = flightRepo.findById(flight_id);
		String passenger = (String) ticketJson.get("passenger");
		if (flight == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		int fullPercentage = (flight.getFullSeats()*10) / flight.getCapacity();
		if (fullPercentage == 10) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		
		double price = flight.getBasePrice();
		for (int i=0; i < fullPercentage; i++) {
			price *= 1.1;
		}
		
		flight.setFullSeats(flight.getFullSeats()+1);
		
		Ticket ticket = new Ticket();
		ticket.setFlight(flight);
		ticket.setPassenger(passenger);
		ticket.setPrice(price);
		ticketRepo.save(ticket);
		return ticket;
	}
	
	@GetMapping(path="/ticket")
	public List<Ticket> getAllTickets()
	{
		return ticketRepo.findAll();
	}
	
	@GetMapping("/ticket/{id}")
	public Ticket getTicket(@PathVariable int id, HttpServletResponse response)
	{
		Ticket ticket = ticketRepo.findById(id);
		if (ticket == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return ticket;
	}
	
	@DeleteMapping("/ticket/{id}")
	public String deleteTicket(@PathVariable int id, HttpServletResponse response)
	{
		Ticket ticket = ticketRepo.findById(id);
		if (ticket == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "ticket do not exist";
		}
		Flight flight = ticket.getFlight();
		flight.setFullSeats(flight.getFullSeats()-1);
		ticketRepo.delete(ticket);
		return "deleted";
	}
	
}