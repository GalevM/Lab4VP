package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();
    List<Event> searchEvents(String text);
    void deleteById(Long id);
    Event saveEvent(String name, String description, double popularityScore, Long locationId, Long id, int numTickets);
    Optional<Event> findById(Long id);

    List<Event> findAllByLocationId(Long locationId);
}
