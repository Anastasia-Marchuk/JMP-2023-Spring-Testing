package jmp.amarchuk.dao;

import jmp.amarchuk.model.User;
import jmp.amarchuk.model.UserAccount;
import jmp.amarchuk.web.handler.HandlerException;

import java.util.List;

public interface UserAccountDao {

    UserAccount getUserAccountDaoById(int id);

    List<UserAccount> getaAllUserAccountDao ();

    UserAccount getById(int id);

    double getMoney(User user);

    UserAccount putMoney(int accountId, double money);

    void pay(UserAccount userAccount, double price);

    UserAccount createAccount(UserAccount userAccount);

    double updateMoney(User user, double price);


    void updateWallet(long userId, double price) throws HandlerException;
}
