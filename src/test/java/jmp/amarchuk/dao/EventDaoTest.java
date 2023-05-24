//package jmp.amarchuk.dao;
//
//
//import jmp.amarchuk.model.Event;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
//public class EventDaoTest {
//
//    private static Logger logger = LoggerFactory.getLogger(EventDaoTest.class);
//    private EventDaoList eventDao;
//    Event event;
//    Event event2;
//    Event event3;
//    List<Event> list;
//
//
//    @Before
//    public void init() {
//        logger.info("Start initialization");
//        logger.info("Creating events and adding to the list");
//        event = new Event((long) 1, "Event1", new Date("20/04/2024"));
//        event2 = new Event((long) 2, "Event2", new Date("20/04/2023"));
//        event3 = new Event((long) 3, "Event3", new Date("20/04/2023"));
//        list = new ArrayList<>();
//        list.add(event);
//        list.add(event2);
//        list.add(event3);
//        logger.info("Complete initialization");
//        eventDao=new EventDaoList(list);
//    }
//
//    @Test
//    public void createEvent() {
//
//        logger.info("Run createEvent test");
//        Event event3 = new Event((long) 3, "Event3", new Date("20/04/2025"));
//        Assertions.assertEquals(3,eventDao.sizeEvent());
//        logger.info("TEST create event "+event3.getTitle());
//        eventDao.createEvent(event);
//        Assertions.assertEquals(4,eventDao.sizeEvent());
//    }
//
//    @Test
//    public void getEventById() {
//
//        logger.info("Run getEventById test");
//        eventDao.getEventById(1);
//        Assertions.assertEquals(event,eventDao.getEventById((long)1));
//    }
//
//    @Test
//    public void getEventsByTitle() {
//
//        logger.info("Run getEventsByTitle test");
//        list=eventDao.getEventsByTitle("Event1", 1, 1);
//        Assertions.assertEquals(1, list.size());
//    }
//
//    @Test
//    public void updateEvent() {
//
//        logger.info("Run updateEvent test");
//        int sizeBeforeUpdate=list.size();
//        Event eventUpdate = new Event((long)1, "UpdatedEvent", new Date("20/04/2023"));
//        eventDao.updateEvent(eventUpdate);
//        int sizeAfterUpdate=list.size();
//        Assertions.assertEquals(sizeBeforeUpdate, sizeAfterUpdate);
//        Assertions.assertEquals(eventUpdate, eventDao.getEventById(1));
//    }
//
//    @Test
//    public void deleteEvent() {
//
//        logger.info("Run deleteEvent test");
//        int sizeBeforeDelete=list.size();
//        eventDao.deleteEvent(1);
//        int sizeAfetrDelete=list.size();
//        Assertions.assertEquals(sizeBeforeDelete, sizeAfetrDelete+1);
//    }
//
//
//    @Test
//    public void sizeEvent() {
//
//        logger.info("Run sizeEvent test. List size event ={}",eventDao.sizeEvent());
//        Assertions.assertEquals(list.size(),eventDao.sizeEvent());
//
//    }
//
//}
