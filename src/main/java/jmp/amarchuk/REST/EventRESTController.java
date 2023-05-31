package jmp.amarchuk.web;

import jmp.amarchuk.facade.BookingFacade;
import jmp.amarchuk.model.Event;
import jmp.amarchuk.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * EventController implementation - annotation-based controller that will delegate to BookingFacade methods.
 *
 * @author Anastasiya Marchuk
 *
 */
@Controller
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Autowired
    EventService eventService;

    @GetMapping("/allEvents")
    public String getAllTickets(Model model) {
        LOGGER.debug("Get all tickets");
        List<Event> allEvents = eventService.getAllEvents();
        model.addAttribute("allEvents", allEvents);
        model.addAttribute("heading", "List of all events in DB");
        return "list_events";
    }

    @GetMapping("/newEvent")
    public String create() {
        LOGGER.debug("Create new event ");
        return "new_event";
    }

    @GetMapping("/createEvent")
    public String name(@RequestParam("title") String title, @RequestParam("price") double price) {
        LOGGER.debug("Create event with title ({})", title);
        Event event=new Event();
        event.setTitle(title);
        event.setPrice(price);
        event.setDate(new Date());
        eventService.createEvent(event);
        LOGGER.info("Method start. UserController.");
        return "redirect:/";
    }

    @GetMapping("/deleteEvent/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        LOGGER.debug("Delete event with id ({}) ", id);
        eventService.deleteEvent(id);
        List<Event> allEvents = eventService.getAllEvents();
        model.addAttribute("allEvents", allEvents);
        model.addAttribute("heading", "List of all events in DB");
        LOGGER.info("Method start. UserController.");
        return "list_events";
    }

}
