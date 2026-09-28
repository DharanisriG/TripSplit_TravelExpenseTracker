package com.example.TripSplit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.TripSplit.entity.Trip;
import com.example.TripSplit.repository.TripRepository;
import com.example.TripSplit.exception.TripNotFoundException;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
    
    public Trip updateTrip(Long id, Trip updatedTrip) {

        Trip existingTrip = tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));

        existingTrip.setName(updatedTrip.getName());
        existingTrip.setDescription(updatedTrip.getDescription());

        return tripRepository.save(existingTrip);
    }
}

