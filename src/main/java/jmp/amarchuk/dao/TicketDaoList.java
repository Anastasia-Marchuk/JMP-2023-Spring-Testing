package jmp.amarchuk.dao;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jmp.amarchuk.model.Category;
import jmp.amarchuk.model.Event;
import jmp.amarchuk.model.Ticket;
import jmp.amarchuk.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



/**
 * Ticket DAO object for the domain model (Ticket).
 *
 * @author Anastasiya Marchuk
 *
 */
@Repository
@JacksonXmlRootElement(localName = "TicketXMLlist")
public class TicketDaoList implements TicketDao {

    @JacksonXmlProperty(localName = "ticket")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Ticket> listTickets;

    private Map<String, Ticket> tickets;

    @Autowired
    private SessionFactory sessionFactory;

//    @Override
//    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
//        Ticket ticket = new Ticket(eventId, userId, category, place);
//        ticket.setId(4);
//        tickets.put("tickets: " + ticket.getId(), ticket);
//        System.out.println(tickets);
//        return ticket;
//    }
//

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        return null;

    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        List <Ticket> list=new LinkedList<>();
        Session currentSession=sessionFactory.getCurrentSession();
        Query<Ticket> theQuery=currentSession.createQuery("from Ticket where userId=id", Ticket.class);
        theQuery.setParameter("id", user.getId());
        list=theQuery.getResultList();

        return list;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public boolean cancelTicket(long ticketId) {

        Session currentSession=sessionFactory.getCurrentSession();
        Query theQuery=currentSession.createQuery("Delete from Ticket where id=:ticketId");
        theQuery.setParameter("ticketId", ticketId);
        theQuery.executeUpdate();
        return true;
    }

    public TicketDaoList() {
    }

    @Override
    public int size() {
        return tickets.size();
    }

    @Override
    public List<Ticket> getAllTickets() {
        List <Ticket> list=new LinkedList<>();
        Session currentSession=sessionFactory.getCurrentSession();
        Query<Ticket> theQuery=currentSession.createQuery("from Ticket", Ticket.class);
        list=theQuery.getResultList();

        return list;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {

        Session currentSession=sessionFactory.getCurrentSession();
        Query<Ticket> theQuery=currentSession.createQuery("from Ticket", Ticket.class);
        List<Ticket> ticketAll = new ArrayList<>();
        ticketAll=theQuery.getResultList();
        int id=ticketAll.size();
        ticket.setId(id+1);
        currentSession.save(ticket);
        return ticket;
    }


    public void preloadTickets(List<Ticket> list) {

        for (int i = 0; i <list.size() ; i++) {
            Ticket ticket=list.get(i);
            long newId=tickets.size()+1;
            Session currentSession=sessionFactory.getCurrentSession();
            Query<Ticket> theQuery=currentSession.createQuery("from Ticket", Ticket.class);
            List<Ticket> ticketAll = new ArrayList<>();
            ticketAll=theQuery.getResultList();
            int id=ticketAll.size();
            ticket.setId(id+1);
            currentSession.save(ticket);

        }
    }


    public List<Ticket> getTickets() {
        return listTickets;
    }
}
