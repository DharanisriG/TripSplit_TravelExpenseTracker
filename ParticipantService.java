package com.example.TripSplit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TripSplit.entity.Participant;
import com.example.TripSplit.repository.ParticipantRepository;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getParticipantsByTrip(Long tripId) {
        return participantRepository.findByTripId(tripId);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
    
    public Participant updateParticipant(Long id, Participant updatedParticipant) {

        Participant existingParticipant =
                participantRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Participant not found with id: " + id));

        existingParticipant.setName(updatedParticipant.getName());

        return participantRepository.save(existingParticipant);
    }
}