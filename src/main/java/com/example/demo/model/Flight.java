package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Flight {
	
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(targetEntity = Route.class, optional=false)
	@JoinColumn(name="route_id", referencedColumnName="id")
	private Route route;
	@ManyToOne(targetEntity = Airline.class, optional=false)
	@JoinColumn(name="airline_id", referencedColumnName="id")
	private Airline airline;
	@Column(name="full_seats")
	private int fullSeats;
	private int capacity;
	@Column(name="base_price")
	private double basePrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getFullSeats() {
		return fullSeats;
	}
	public void setFullSeats(int fullSeats) {
		this.fullSeats = fullSeats;
	}
	@Override
	public String toString() {
		return "Flight [id=" + id + ", route=" + route + ", airline=" + airline + ", fullSeats=" + fullSeats
				+ ", capacity=" + capacity + "]";
	}
	
	
	
	
}
