package jmp.amarchuk.service;


import jmp.amarchuk.dao.UserDao;
import jmp.amarchuk.model.Ticket;
import jmp.amarchuk.model.User;
import jmp.amarchuk.model.UserAccount;
import jmp.amarchuk.web.handler.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * UserService implementation - which contains user and booking-related functionality accordingly.
 *
 * @author Anastasiya Marchuk
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    @Transactional
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDao.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User userUpdated=userDao.updateUser(user);
        if(userUpdated==null){
            throw new NullPointerException("Error updating user "+user);
        }
        return userUpdated;
    }

    @Override
    @Transactional
    public boolean deleteUser(long userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() throws HandlerException {
        return userDao.getAllUsers();
    }

}
