package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventServiceImpl eventService;

    public EventBookingController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public String bookEvent(Model model, HttpServletRequest req){
        String eventName = req.getParameter("eventName");
        String numTickets = req.getParameter("numTickets");
        String attendeeName = req.getParameter("attendeeName");

        int tickets = 0;
        if(req.getParameter("numTickets") != null && !req.getParameter("numTickets").isEmpty()){
            tickets = Integer.parseInt(req.getParameter("numTickets"));
        }

        model.addAttribute("ipAddress", req.getRemoteAddr());
        model.addAttribute("eventName", eventName);
        model.addAttribute("numTickets", numTickets);
        model.addAttribute("attendeeName", attendeeName);
        return "bookingConfirmation";
    }
}
