package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Airport;

@RepositoryRestResource(collectionResourceRel="airport",path="airport")
public interface AirportRepo extends JpaRepository<Airport,Integer>
{
    Airport findById(int id);
}