package com.example.TripSplit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.TripSplit.entity.Trip;
import com.example.TripSplit.service.TripService;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripService.createTrip(trip);
    }

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public Optional<Trip> getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return "Trip deleted successfully";
    }
    
    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable Long id,
                           @RequestBody Trip trip) {

        return tripService.updateTrip(id, trip);
    }
}