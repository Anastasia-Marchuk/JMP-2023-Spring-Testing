package jmp.amarchuk.service;


import jmp.amarchuk.dao.TicketDao;
import jmp.amarchuk.dao.TicketDaoList;
import jmp.amarchuk.model.Category;
import jmp.amarchuk.model.Event;
import jmp.amarchuk.model.Ticket;
import jmp.amarchuk.model.User;
import jmp.amarchuk.service.parser.Jackson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

/**
 * TicketService implementation - which contains ticket and booking-related functionality accordingly.
 *
 * @author Anastasiya Marchuk
 *
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    @Transactional
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        return ticketDao.bookTicket(userId, eventId, place, category);
    }

    @Override
    @Transactional
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDao.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    @Transactional
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDao.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    @Transactional
    public boolean cancelTicket(long ticketId) {
        return ticketDao.cancelTicket(ticketId);
    }

    @Override
    @Transactional
    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        return ticketDao.createTicket(ticket);
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return null;
    }

    public void preloadTickets(MultipartFile file) {

        TicketDaoList ticketDto = null;
        try {
            ticketDto = new Jackson(file).loaderXmlFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Ticket> tickets=ticketDto.getTickets();
        ticketDao.preloadTickets(tickets);
    }

}
