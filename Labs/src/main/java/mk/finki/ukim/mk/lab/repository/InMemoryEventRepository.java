package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventRepository {

    public List<Event> findAll(){
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text){
        return DataHolder.events.stream()
                .filter(e -> e.getName().contains(text) || e.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public boolean decrementTickets(String name, int num){
        List<Event> events = DataHolder.events.stream()
                .filter(e -> e.getName().equals(name)).collect(Collectors.toList());

        Event event = events.get(0);
        if((event.getTickets() - num) >= 0){
            event.setTickets(event.getTickets() - num);
            return true;
        }

        return false;

    }

    public void deleteById(Long id){
        DataHolder.events.removeIf(event -> Objects.equals(event.getId(), id));
    }
    public Optional<Event> saveEvent(String name, String description, double popularityScore, Location location, Long id, int numTickets){
        Event newEvent = new Event(name, description, popularityScore, numTickets, location);
        DataHolder.events.removeIf(e -> Objects.equals(e.getId(), id));
        DataHolder.events.add(newEvent);
        return Optional.of(newEvent);
    }



    public Optional<Event> findById(Long id){
        return DataHolder.events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

}
