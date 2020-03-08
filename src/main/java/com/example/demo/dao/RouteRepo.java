package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Route;

@RepositoryRestResource(collectionResourceRel="route",path="route")
public interface RouteRepo extends JpaRepository<Route,Integer>
{
	Route findById(int id);
}