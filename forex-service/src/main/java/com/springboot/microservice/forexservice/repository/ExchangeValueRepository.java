package com.springboot.microservice.forexservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservice.entity.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{
	
	public ExchangeValue findByFromAndTo(String from, String to);

}
