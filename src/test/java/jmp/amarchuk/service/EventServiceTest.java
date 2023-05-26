package jmp.amarchuk.service;

import jmp.amarchuk.dao.EventDao;
import jmp.amarchuk.model.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks
    EventServiceImpl eventService;

    @Mock
    EventDao eventDao;

    Event event1 = new Event((long) 1, "TestEvent", new Date("05/05/2023"));
    Event event2 = new Event((long) 1, "TestEvent", new Date("06/06/2023"));

    @Test
    void getEventById() {

        when(eventDao.getEventById(any(Long.class))).thenReturn(event1);
        Event eventById = eventService.getEventById(0);
        assertEquals(eventById, event1);
    }

    @Test
    void getEventsByTitle() {
        List<Event> list = Arrays.asList(event1, event2);

        when(eventDao.getEventsByTitle(any(), any(Integer.class), any(Integer.class))).thenReturn(list);

        List<Event> mock = eventService.getEventsByTitle("TestEvent", 1, 1);
        assertEquals(list.size(), mock.size());
    }

    @Test
    void getEventsForDay() {
        List<Event> list = Arrays.asList(event1, event2);
        when(eventDao.getEventsForDay(any(), any(Integer.class), any(Integer.class))).thenReturn(list);
        List<Event> mock = eventService.getEventsForDay(new Date("06/06/2023"), 1, 1);
        assertEquals(list.size(), mock.size());
    }

    @Test
    void createEvent() {
        when(eventDao.createEvent(any())).thenReturn(event1);
        assertEquals(eventService.createEvent(event1), event1);

    }

    @Test
    void updateEvent() {
        when(eventDao.updateEvent(any())).thenReturn(event1);
        assertEquals(eventService.updateEvent(event1), event1);
    }

    @Test
    void deleteEvent() {
        when(eventDao.deleteEvent(any(Long.class))).thenReturn(true);
        assertTrue(eventService.deleteEvent(1));
    }
}