package com.SecurityDemo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SecurityDemo.demo.model.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {

	public Facility findByName(String name);

}
