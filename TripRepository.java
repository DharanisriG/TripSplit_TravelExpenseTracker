package com.example.TripSplit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.TripSplit.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
