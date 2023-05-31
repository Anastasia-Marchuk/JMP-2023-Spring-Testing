package jmp.amarchuk.REST;


import jmp.amarchuk.model.Ticket;
import jmp.amarchuk.model.User;
import jmp.amarchuk.service.EventService;
import jmp.amarchuk.service.TicketService;
import jmp.amarchuk.service.UserAccountService;
import jmp.amarchuk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TicketRESTController implementation - annotation-based controller.
 *
 * @author Anastasiya Marchuk
 *
 */
@RestController
@RequestMapping("/api/ticket")
public class TicketRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRESTController.class);

    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;
    @Autowired
    UserAccountService userAccountService;


    @GetMapping("/tickets")
    public  List<Ticket> getAllTickets(){
        LOGGER.debug("get all tickets" );
        List<Ticket> allTickets = ticketService.getAllTickets();
        LOGGER.info("Method start. TicketController.");
        List<Ticket> jsonList=new ArrayList<>();
        for (Ticket t: allTickets){
            Ticket ticket=new Ticket();
            ticket.setId(t.getId());
            ticket.setEventId(t.getEventId());
            ticket.setUserId(t.getUserId());
            jsonList.add(ticket);
        }
        return jsonList;
    }


    @GetMapping("/id/{id}")
    public  List<Ticket> getAllTicketsById(@PathVariable("id") int id) {
        LOGGER.debug("get all tickets" );
        User user=userService.getUserById(id);
        List<Ticket> allTickets = ticketService.getBookedTickets(user,1,1);
        List<Ticket> jsonList=new ArrayList<>();
        for (Ticket t: allTickets){
            Ticket ticket=new Ticket();
            ticket.setId(t.getId());
            ticket.setEventId(t.getEventId());
            ticket.setUserId(t.getUserId());
            jsonList.add(ticket);
        }
        return jsonList;
    }

    @PostMapping("/create")
    public String createTicket(@RequestBody Ticket ticket) {
        LOGGER.debug("Create ticket ");
        ticketService.createTicket(ticket);
        LOGGER.info("Method start. UserController.");
        return "Ticket is created with id - "+ticket.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTicket(@PathVariable("id") int id) {
        LOGGER.debug("Delete ticket with id => {}", id);
        ticketService.cancelTicket(id);
        LOGGER.info("Method start. UserController.");
        return "Ticket is deleted with id - "+id;
    }

}
