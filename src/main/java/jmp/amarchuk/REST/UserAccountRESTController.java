package jmp.amarchuk.REST;

import jmp.amarchuk.model.Event;
import jmp.amarchuk.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * EventRESTController implementation - annotation-based controller.
 *
 * @author Anastasiya Marchuk
 *
 */
@RestController
@RequestMapping("/api/event")
public class EventRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventRESTController.class);

    @Autowired
    EventService eventService;

    @GetMapping("/events")
    public List<Event> getAllTickets() {
        LOGGER.debug("Get all events");
        List<Event> allEvents = eventService.getAllEvents();
        List<Event> jsonList=new ArrayList<>();
        for (Event e: allEvents){
           Event event=new Event();
            event.setId(e.getId());
            event.setTitle(e.getTitle());
            event.setPrice(e.getPrice());
            event.setDate(e.getDate());
            jsonList.add(event);
        }
        return jsonList;
    }


    @PostMapping("/create")
    public Event name(@RequestBody Event event) {
        eventService.createEvent(event);
        LOGGER.info("Method start. UserController.");
        return event;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        eventService.deleteEvent(id);
        LOGGER.info("Method start. UserController.");
        return "Delete event with id - "+id;
    }

}
