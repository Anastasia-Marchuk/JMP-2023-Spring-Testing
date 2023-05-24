package jmp.amarchuk;

import jmp.amarchuk.model.Category;
import jmp.amarchuk.model.Event;
import jmp.amarchuk.model.Ticket;
import jmp.amarchuk.model.User;
import jmp.amarchuk.web.handler.HandlerException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DataAccessSpring {


    private static final Logger logger =
            LoggerFactory.getLogger(DataAccessSpring.class);

    @Autowired
    private static SessionFactory sessionFactory;



    public static void main(String[] args) throws HandlerException {

//        Session currentSession=sessionFactory.getCurrentSession();
//        Query<User> theQuery=currentSession.createQuery("from User ", User.class);
//        List<User> users = new ArrayList<>();
//        users=theQuery.getResultList();
//        System.out.println(users);
        System.out.println(new Date());




//
//
////        Configuration config = new Configuration();
//////        config.configure("hibernate.cfg.xml");
//////        config.configure("configSpring.xml");
////        config.configure("WEB-INF/spring-mvc-crud-demo-servlet.xml");
////        SessionFactory sessionFactory = config.buildSessionFactory();
////        Session session = sessionFactory.openSession();
//
//        try{
//            /**
//             * Delete data from tables
//             */
////            User deletedUser=session.get(User.class,(long)12);
////            session.delete(deletedUser);
////            session.getTransaction().commit();
//
//
//
//            /**
//             * Insertind data into tables
//             */
//
////            logger.info("Create new user object...");
////            User user=new User(17, "Anastasia", "marchuk@gmail.com");
////            session.beginTransaction();
////            session.save(user);
////            logger.info("Save user...");
////            session.getTransaction().commit();
////            logger.info("Commit...");
////            logger.info("DONE!!! USER ADDED TO DB!");
////
////            logger.info("Create new event object...");
////            Event event=new Event((long) 4, "Event 4", new Date(), 120.00);
////            session.beginTransaction();
////            session.save(event);
////            logger.info("Save event...");
////            session.getTransaction().commit();
////            logger.info("Commit...");
////            logger.info("DONE!!! EVENT ADDED TO DB!");
////
////            logger.info("Create new ticket object...");
////            Ticket ticket=new Ticket ((long)1,(long)13, Category.BAR,1);
////            session.beginTransaction();
////            session.save(ticket);
////            logger.info("Save ticket...");
////            session.getTransaction().commit();
////            logger.info("Commit...");
////            logger.info("DONE!!! Ticket ADDED TO DB!");
//
////            /**
////             * Read data from tables
////             */
////            User user1 =  (User) session.get(User.class, (long)16);
////            System.out.println("READ!!!!!!!!!!!!!!!! "+ user1);
////
//            List<User> usersList=
//                    session.createQuery("from User")
//                            .getResultList();
//
//
//            //display users
//            for (User u: usersList){
//                System.out.println(u);
//            }
//
////            /**
////             * Update data from tables
////             */
////            User userId14=session.get(User.class, (long)14);
////            userId14.setName("New NAME");
////            session.getTransaction().commit();
////
////            session.createQuery("Update User set name='NEW NAME' where id=16")
////                    .executeUpdate();
//
//
//
//
//        } finally{
//            sessionFactory.close();
//        }
    }
}