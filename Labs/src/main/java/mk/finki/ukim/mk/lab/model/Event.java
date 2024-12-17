package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double popularityScore; //0.0 <-> 10.0
    private int tickets; // (0-10)

    @ManyToOne
    private Location location;

    public Event(String name, String description, double popularityScore, int tickets, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.tickets = tickets;
        this.location = location;
    }

    public Event(String name, String description, double popularityScore, int tickets, Location location, long id) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.tickets = tickets;
        this.location = location;
        this.id = id;
    }


    public Event(String name, String description, double popularityScore, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }

}
