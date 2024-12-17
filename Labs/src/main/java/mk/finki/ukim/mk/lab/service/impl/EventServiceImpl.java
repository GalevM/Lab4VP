package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.InMemoryEventRepository;
import mk.finki.ukim.mk.lab.repository.InMemoryLocationRepository;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text){
        return eventRepository.searchEvents(text);
    }


    @Override
    public void deleteById(Long id) {
      eventRepository.deleteById(id);
    }

    @Override
    public Event saveEvent(String name, String description, double popularityScore, Long locationId, Long id, int numTicekts) {
        Location location = locationRepository.findById(locationId).orElse(null);
        Event event = eventRepository.findById(id).orElse(null);
        if(event != null){
            return eventRepository.save(new Event(name, description, popularityScore, numTicekts, location, id));
        }
        return eventRepository.save(new Event(name, description, popularityScore, numTicekts, location));
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findAllByLocationId(Long locationId) {
        return eventRepository.findAllByLocation_id(locationId);
    }

}
