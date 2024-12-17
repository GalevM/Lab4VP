package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.LocationService;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventServiceImpl eventService;
    private final LocationService locationService;

    public EventController(EventServiceImpl eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }


    @GetMapping
    public String getEventsPage(@RequestParam(required = false)String error, Model model, HttpServletRequest req){

        List<Event> events = new ArrayList<>();

        if(req.getParameter("popularityScore") != null && req.getParameter("searchText") != null){
            String searchText = req.getParameter("searchText");
            String popularityScoreStr = req.getParameter("popularityScore");
            if(!searchText.isEmpty() && !popularityScoreStr.isEmpty()){
                double popularityScore = Double.parseDouble(popularityScoreStr);
                events = eventService.findAll().stream()
                        .filter(e -> e.getPopularityScore() >= popularityScore && e.getName().contains(searchText))
                        .collect(Collectors.toList());
            } else{
                events = eventService.findAll();
            }
        }
        else{
            events = eventService.findAll();
        }

        String location = req.getParameter("locationId1");
        if (location != null) {
            if (!location.isEmpty()) {
                events = eventService.findAll().stream()
                        .filter(e -> Long.toString(e.getLocation().getId()).equals(req.getParameter("locationId1")))
                        .collect(Collectors.toList());
            }
        }

        List<Location> locations = locationService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("locations", locations);
        return "listEvents";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId,
                            @RequestParam Long id,
                            @RequestParam int numTickets){
        eventService.saveEvent(name, description, popularityScore, locationId, id, numTickets);
        return "redirect:/events";
    }

    @GetMapping("/add-form")
    public String addEventPage(Model model){
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "saveEvent";
    }


    @GetMapping("/edit/{eventId}")
    public String editEventEdit(@PathVariable Long eventId, Model model){
        if (this.eventService.findById(eventId).isPresent()){
            Event event = this.eventService.findById(eventId).get();
            List<Location> locations = locationService.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("event", event);
            return "saveEvent";
        }
        else{
            return "redirect:/events?error=EventNotFound";
        }
    }

    @GetMapping("/details/{eventId}")
    public String seeDetails(@PathVariable Long eventId, Model model){
        if (this.eventService.findById(eventId).isPresent()){
            Event event = this.eventService.findById(eventId).get();
            model.addAttribute("event", event);
            return "details";
        }
        else{
            return "redirect:/events?error=EventNotFound";
        }
    }

}
