package jmp.amarchuk.model;

import javax.persistence.*;

@Entity
@Table
public class UserAccount {

    @Id
    @Column(name = "id")
    int id;

    long userId;

    @Column(name = "money")
    double money;

    @OneToOne
    @JoinColumn(name = "userid",referencedColumnName="id",insertable=false, updatable=false)
    private User user;

    public UserAccount(User user) {
        this.userId=user.getId();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAccount(long userId, double money) {
        this.userId = userId;
        this.money = money;
    }

    public UserAccount() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void updateWallet(long userId, double price) {
    }
}
