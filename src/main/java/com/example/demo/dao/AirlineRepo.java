package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Airline;

@RepositoryRestResource(collectionResourceRel="airline",path="airline")
public interface AirlineRepo extends JpaRepository<Airline,Integer>
{
	Airline findById(int id);
}