package com.example.TripSplit.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.TripSplit.entity.Participant;
import com.example.TripSplit.service.ParticipantService;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public Participant createParticipant(@RequestBody Participant participant) {
        return participantService.createParticipant(participant);
    }

    @GetMapping("/trip/{tripId}")
    public List<Participant> getParticipantsByTrip(@PathVariable Long tripId) {
        return participantService.getParticipantsByTrip(tripId);
    }

    @DeleteMapping("/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return "Participant deleted successfully";
    }
    
    @PutMapping("/{id}")
    public Participant updateParticipant(
            @PathVariable Long id,
            @RequestBody Participant participant) {

        return participantService.updateParticipant(id, participant);
    }
}