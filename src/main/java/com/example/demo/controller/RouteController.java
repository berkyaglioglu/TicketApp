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

import com.example.demo.dao.AirportRepo;
import com.example.demo.dao.RouteRepo;
import com.example.demo.model.Airport;
import com.example.demo.model.Route;

@RestController
public class RouteController
{
	@Autowired
	RouteRepo routeRepo;
	
	@Autowired
	AirportRepo airportRepo;
	
	@PostMapping(path="/route")
	public Route addRoute(@RequestBody Map<String, Object> routeJson, HttpServletResponse response)
	{
		int from_id = (int) routeJson.get("from_id");
		int to_id = (int) routeJson.get("to_id");
		Airport from = airportRepo.findById(from_id);
		Airport to = airportRepo.findById(to_id);
		if (from == null || to == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		Route route = new Route();
		route.setFrom(from);
		route.setTo(to);
		routeRepo.save(route);
		return route;
	}
	
	@GetMapping(path="/route")
	public List<Route> getAllRoutes()
	{
		return routeRepo.findAll();
	}
	
	@GetMapping("/route/{id}")
	public Route getRoute(@PathVariable int id, HttpServletResponse response)
	{
		Route route = routeRepo.findById(id);
		if (route == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return route;
	}
	
}