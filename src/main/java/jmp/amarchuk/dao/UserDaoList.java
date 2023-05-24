package jmp.amarchuk.dao;

import jmp.amarchuk.model.User;
import jmp.amarchuk.model.UserAccount;
import jmp.amarchuk.web.handler.HandlerException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * User DAO object for the domain model (User).
 *
 * @author Anastasiya Marchuk
 *
 */
@Repository
public class UserDaoList implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoList() {}

    @Override
    public User getUserById(long userId) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query<User> theQuery=currentSession.createQuery("from user where id=:userId", User.class);
        theQuery.setParameter("userId", userId);
        List<User> userbyId = new ArrayList<>();
        userbyId=theQuery.getResultList();
        return userbyId.get(0);
    }

    @Override
    public User getUserByEmail(String email) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query<User> theQuery=currentSession.createQuery("from User where email=:emailName", User.class);
        theQuery.setParameter("emailName", email);
        List<User> userbyEmail = new ArrayList<>();
        userbyEmail=theQuery.getResultList();
        return userbyEmail.get(0);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query<User> theQuery=currentSession.createQuery("from User where name=:name", User.class);
        theQuery.setParameter("name", name);
        List<User> userbyName = new ArrayList<>();
        userbyName=theQuery.getResultList();
        return userbyName;
    }

    @Override
    public User createUser(User user) {

        Session currentSession=sessionFactory.getCurrentSession();
        Query<User> theQuery=currentSession.createQuery("from User", User.class);
        List<User> usersAll = new ArrayList<>();
        usersAll=theQuery.getResultList();
        int id=usersAll.size();
        user.setId(id+1);
        currentSession.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        Session currentSession=sessionFactory.getCurrentSession();
        currentSession.update(user);
        return user;
    }

    @Override
    public boolean deleteUser(long userId) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query theQuery=currentSession.createQuery("Delete from User where id=:userId");
        theQuery.setParameter("userId", userId);
        theQuery.executeUpdate();

        return true;
    }


    @Override
    public List<User> getAllUsers() throws HandlerException {

        Session currentSession=sessionFactory.getCurrentSession();
        Query<User> theQuery=currentSession.createQuery("from User", User.class);
        List<User> usersAll = new ArrayList<>();
        usersAll=theQuery.getResultList();
        return usersAll;
    }

//    @Override
//    public void preloadUsers(List<User> list) {
//        users.addAll(list);
//    }

}
