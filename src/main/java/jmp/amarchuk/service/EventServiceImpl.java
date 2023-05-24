package jmp.amarchuk.service;


import jmp.amarchuk.dao.EventDao;
import jmp.amarchuk.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * EventService implementation - which contains event and booking-related functionality accordingly.
 *
 * @author Anastasiya Marchuk
 *
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventDao eventDao;

    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    @Transactional
    public Event getEventById(long eventId) {
        return eventDao.getEventById(eventId);
    }

    @Override
    @Transactional
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventDao.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    @Transactional
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventDao.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    @Transactional
    public Event createEvent(Event event) {
        return eventDao.createEvent(event);
    }

    @Override
    @Transactional
    public Event updateEvent(Event event) {
        return eventDao.updateEvent(event);
    }

    @Override
    @Transactional
    public boolean deleteEvent(long eventId) {
        return eventDao.deleteEvent(eventId);
    }

    @Transactional
    public List<Event> getAllEvents() {
       return eventDao.getAllEvents();
    }
}
