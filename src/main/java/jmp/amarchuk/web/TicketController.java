package jmp.amarchuk.web;

import jmp.amarchuk.dao.TicketDaoList;
import jmp.amarchuk.dao.UserDaoList;
import jmp.amarchuk.facade.BookingFacade;
import jmp.amarchuk.model.*;
import jmp.amarchuk.service.EventService;
import jmp.amarchuk.service.TicketService;
import jmp.amarchuk.service.UserAccountService;
import jmp.amarchuk.service.UserService;
import jmp.amarchuk.web.handler.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * TicketController implementation - annotation-based controller that will delegate to BookingFacade methods.
 *
 * @author Anastasiya Marchuk
 *
 */
@Controller
public class TicketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;
    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/ticketsByUser")
    public String name() {
        return "generate_pdf_tickets";
    }

    @GetMapping("/allTickets")
    public String getAllTickets(Model model) {
        LOGGER.debug("get all tickets" );
        List<Ticket> allTickets = ticketService.getAllTickets();
        model.addAttribute("allTickets", allTickets);
        model.addAttribute("heading", "List of all tickets in DB");
        LOGGER.info("Method start. UserController.");
        return "list_tickets";
    }

    @GetMapping("/allTicketsById")
    public String getAllTicketsBuId(@RequestParam("id") long id,Model model) {
        LOGGER.debug("get all tickets" );
        User user=ticketService.getUserById(id);
        List<Ticket> allTickets = ticketService.getBookedTickets(user,1,1);
        model.addAttribute("allTickets", allTickets);
        model.addAttribute("heading", "List of all tickets in DB");
        LOGGER.info("Method start. UserController.");
        return "list_tickets";
    }

    @GetMapping("/createTicket")
    public String createTicket(@RequestParam("eventId") long eventId, @RequestParam("userId") long userId,
                               @RequestParam("category") Category category, @RequestParam("place") int place) throws HandlerException {
        LOGGER.debug("Create ticket ");
        Ticket ticket=new Ticket(eventId,userId,category, place);
        ticketService.createTicket(ticket);
        Event ev=eventService.getEventById(eventId);
        userAccountService.updateWallet(userId, ev.getPrice());
        LOGGER.info("Method start. UserController.");
        return "redirect:/";
    }

    @GetMapping("/deleteTicket/{id}")
    public String deleteTicket(@PathVariable("id") int id,Model model) {
        LOGGER.debug("Delete ticket with id => {}", id);
        ticketService.cancelTicket(id);
        model.addAttribute("message","Ticket with id "+id);
        model.addAttribute("heading", "Delete: ");
        LOGGER.info("Method start. UserController.");
        return "facade";
    }


    @GetMapping("/newTicket")
    public String createTicket() {
        LOGGER.info("Method start. UserController.");
        return "new_ticket";
    }

}
