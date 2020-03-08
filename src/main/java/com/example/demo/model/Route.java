package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Route {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(targetEntity = Airport.class, optional=false)
	@JoinColumn(name="from_id", referencedColumnName="id")
	private Airport from;
	@ManyToOne(targetEntity = Airport.class, optional=false)
	@JoinColumn(name="to_id", referencedColumnName="id")
	private Airport to;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Airport getFrom() {
		return from;
	}
	public void setFrom(Airport from) {
		this.from = from;
	}
	public Airport getTo() {
		return to;
	}
	public void setTo(Airport to) {
		this.to = to;
	}
	
}
