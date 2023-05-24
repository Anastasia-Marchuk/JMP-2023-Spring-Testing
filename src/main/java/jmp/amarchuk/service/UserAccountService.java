package jmp.amarchuk.service;

import jmp.amarchuk.model.User;
import jmp.amarchuk.model.UserAccount;

import java.util.List;

public interface UserAccountService {

    UserAccount getUserAccountByUserId(long userId);

    UserAccount getById(int id);

    double getMoneyByAccountId(int id);

    double updateMoney(User user, double wallet);

    List<UserAccount> getAllAccounts();

    UserAccount createAccount(UserAccount userAccount) ;

    void updateWallet(long userId, double price);
}
