package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Flight;

@RepositoryRestResource(collectionResourceRel="flight",path="flight")
public interface FlightRepo extends JpaRepository<Flight,Integer>
{
	Flight findById(int id);
}