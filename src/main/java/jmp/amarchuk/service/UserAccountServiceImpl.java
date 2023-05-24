package jmp.amarchuk.service;

import jmp.amarchuk.dao.UserAccountDao;
import jmp.amarchuk.model.User;
import jmp.amarchuk.model.UserAccount;
import jmp.amarchuk.web.handler.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountDao userAccountDao;

    public UserAccountServiceImpl(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    public UserAccount getUserAccountByUserId(long userId) {
        return null;
    }

    @Override
    public UserAccount getById(int id) {
        return userAccountDao.getById(id);
    }

    @Override
    public double getMoneyByAccountId(int id) {
        return 0;
    }

    public double getMoney(User user) {
        return userAccountDao.getMoney(user);
    }

    @Override
    @Transactional
    public double updateMoney(User user, double wallet) {
        return userAccountDao.updateMoney(user, wallet);
    }

    @Override
    @Transactional
    public List<UserAccount> getAllAccounts() {
        return userAccountDao.getaAllUserAccountDao();
    }

    @Override
    @Transactional
    public UserAccount createAccount(UserAccount userAccount) {
        return userAccountDao.createAccount(userAccount);
    }

    @Override
    @Transactional
    public void updateWallet(long userId, double price) {
        try {
            userAccountDao.updateWallet(userId,price);
        } catch (HandlerException e) {
            e.printStackTrace();
        }
    }

    public UserAccount putMoney(int accountId, double money) {
        return userAccountDao.putMoney(accountId, money);
    }

    public void pay(UserAccount ua, double price) {
        userAccountDao.pay(ua, price);
    }
}
