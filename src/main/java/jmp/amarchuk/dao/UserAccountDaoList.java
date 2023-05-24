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

@Repository
public class UserAccountDaoList implements  UserAccountDao {

    private List<UserAccount> accounts;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserAccount getUserAccountDaoById(int id) {
        return accounts.stream().filter(o -> o.getId()==id).findAny().get();
    }

    public UserAccountDao getUserAccountByUser(User user) {
        return null;
    }

    @Override
    public List<UserAccount> getaAllUserAccountDao() {

        Session currentSession=sessionFactory.getCurrentSession();
        Query<UserAccount> theQuery=currentSession.createQuery("from UserAccount", UserAccount.class);
        List<UserAccount> accounts = new ArrayList<>();
        accounts=theQuery.getResultList();
        return accounts;
    }

    @Override
    public UserAccount getById(int id) {
        return accounts.stream().filter(o -> o.getId()==id).findAny().get();
    }

    @Override
    public double getMoney(User user) {
        int userId= (int) user.getId();
        UserAccount us=accounts.stream().filter(o -> o.getUserId()==userId).findAny().get();
        return us.getMoney();
    }

    @Override
    public UserAccount putMoney(int accountId, double money) {
        for (UserAccount u: accounts) {
            if(u.getId()==accountId){
                u.setMoney(u.getMoney()+money);
                return u;
            }
        }
        return null;
    }

    @Override
    public void pay(UserAccount userAccount, double price) {

        int accountId=userAccount.getId();
        for (UserAccount u: accounts) {
            if(u.getId()==accountId){
                u.setMoney(u.getMoney()-price);
            }
        }
    }

    @Override
    public UserAccount createAccount(UserAccount userAccount) {
        Session currentSession=sessionFactory.getCurrentSession();
        Query<UserAccount> theQuery=currentSession.createQuery("from UserAccount", UserAccount.class);
        List<UserAccount> accounts = new ArrayList<>();
        accounts=theQuery.getResultList();
        int id=accounts.size();
        userAccount.setId(id+1);
        currentSession.save(userAccount);
        return userAccount;
    }

    @Override
    public double updateMoney(User user,double price) {
        Session currentSession0=sessionFactory.getCurrentSession();
        long userId= user.getId();
        Query<UserAccount> theQuery=currentSession0.createQuery("from UserAccount where userId=:userId", UserAccount.class);
        theQuery.setParameter("userId",userId);

        List<UserAccount> uacc = new ArrayList<>();
        uacc=theQuery.getResultList();

        UserAccount ua=uacc.get(0);
        ua.setMoney(price);
        Session currentSession=sessionFactory.getCurrentSession();
        currentSession.update(ua);
        return price;
    }

    @Override
    public void updateWallet(long userId, double price) throws HandlerException {
        Session currentSession0=sessionFactory.getCurrentSession();
        Query<UserAccount> theQuery=currentSession0.createQuery("from UserAccount where userId=:userId", UserAccount.class);
        theQuery.setParameter("userId",userId);
        List<UserAccount> account = new ArrayList<>();
        account=theQuery.getResultList();

        UserAccount accountToUpdate=account.get(0);
        double oldWallet=accountToUpdate.getMoney();
        if (price>oldWallet){
            System.out.println("Sorry. User does not have enough money to buy the ticket!");
            throw new HandlerException();
        }
        accountToUpdate.setMoney(oldWallet-price);



    }
}
