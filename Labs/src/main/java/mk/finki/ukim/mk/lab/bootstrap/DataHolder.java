package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();

    @PostConstruct
    public void init(){
        locations.add(new Location("FINKI", "Adresa 1", "425", "Opis 1"));
        locations.add(new Location("Silbo", "Adresa 2", "100", "Opis 2"));
        locations.add(new Location("SOU Jane Sandanski", "Adresa 3", "950", "Opis 3"));
        locations.add(new Location("City Mall", "Adresa 4", "2500", "Opis 4"));
        locations.add(new Location("Avtobuska", "Adresa 5", "1650", "Opis 5"));


        events.add(new Event("Bayern Munich vs Barcelona", "Football event", 9.8, 5, locations.get(4)));
        events.add(new Event("Red Star Belgrade vs Partizan", "Basketball event", 9.2, 6, locations.get(3)));
        events.add(new Event("Macedonia vs Turkey", "Volleyball event", 7.8, 2, locations.get(0)));
        events.add(new Event("Strumica Open Festival", "Music event", 6.4, 10, locations.get(2)));
        events.add(new Event("Ed Sheeran Concert", "Music event", 8.7, 7, locations.get(1)));
        events.add(new Event("Donald Trump Meeting", "Political event", 3.8, 1, locations.get(2)));
        events.add(new Event("Sidemen Meeting", "Youtube event", 6.9,8, locations.get(3)));
        events.add(new Event("Real Madrid vs Barcelona", "Football event", 7.3,3, locations.get(0)));
        events.add(new Event("Bayern Munich vs Borussia Dortmund", "Football event", 10.0, 4, locations.get(1)));
        events.add(new Event("Pastrmajlijada", "Food event", 4.4,5, locations.get(4)));

    }
}
