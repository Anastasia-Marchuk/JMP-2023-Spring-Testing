package jmp.amarchuk.dao;

import jmp.amarchuk.model.Event;
import jmp.amarchuk.model.Ticket;
import jmp.amarchuk.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Event DAO object for the domain model (Event).
 *
 * @author Anastasiya Marchuk
 *
 */
@Repository
public class EventDaoList implements EventDao {

    private List<Event> events;

    @Autowired
    private SessionFactory sessionFactory;

    public EventDaoList(List<Event> list) {
        this.events=list;
    }

    public EventDaoList() {

    }


    @Override
    public Event getEventById(long eventId) {

        Session currentSession=sessionFactory.getCurrentSession();
        Query <Event> theQuery=currentSession.createQuery("from Event where id=:eventId", Event.class);
        theQuery.setParameter("eventId", eventId);
        List<Event> eventsAll = new ArrayList<>();
        eventsAll=theQuery.getResultList();
        return eventsAll.get(0);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return events.stream().filter(o -> o.getTitle().equals(title)).collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return events.stream().filter(o -> o.getDate().equals(day)).collect(Collectors.toList());
    }

    @Override
    public Event createEvent(Event event) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query<Event> theQuery=currentSession.createQuery("from Event", Event.class);
        List<Event> usersAll = new ArrayList<>();
        usersAll=theQuery.getResultList();
        int id=usersAll.size();
        event.setId(id+1);
        currentSession.save(event);
        return event;
    }

    @Override
    public Event updateEvent(Event event) {
        Session currentSession=sessionFactory.getCurrentSession();
        currentSession.update(event);
        return event;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query theQuery=currentSession.createQuery("Delete from Event where id=:eventId");
        theQuery.setParameter("eventId", eventId);
        theQuery.executeUpdate();

        return true;
    }

    @Override
    public int size() {
        return events.size();
    }

    @Override
    public List<Event> getAllEvents() {
        Session currentSession=sessionFactory.getCurrentSession();
        Query <Event> theQuery=currentSession.createQuery("from Event", Event.class);
        List<Event> eventsAll = new ArrayList<>();
        eventsAll=theQuery.getResultList();
        return eventsAll;
    }

    public int sizeEvent() {
        return events.size();
    }

    public boolean deleteAllEvents() {
        for (int i = events.size(); i>=0 ; i--) {
            if(events.isEmpty()){
                return true;
            }
            events.remove(events.get(i-1));
        }
        return events.size() == 0;
    }
}
