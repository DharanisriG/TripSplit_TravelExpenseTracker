package com.example.TripSplit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TripSplit.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findByTripId(Long tripId);
}