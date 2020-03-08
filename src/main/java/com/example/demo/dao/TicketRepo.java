package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Ticket;

@RepositoryRestResource(collectionResourceRel="ticket",path="ticket")
public interface TicketRepo extends JpaRepository<Ticket,Integer>
{
	Ticket findById(int id);
}