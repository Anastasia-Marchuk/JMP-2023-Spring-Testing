package jmp.amarchuk.model;

import jmp.amarchuk.web.handler.HandlerException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Entity
@Table
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Ticket> tickets;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserAccount userAccount;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void add(Ticket tempTicket){

        if(tickets == null){
            tickets = new ArrayList<>();
        }
        tickets.add(tempTicket);
        tempTicket.setUser(this);
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public User() {
    }

    public User(long id, String name)  {
        this.id = id;
        this.name = name;
    }

    public User(long id, String name, String email) throws HandlerException {
        this.id = id;
        this.name = name;
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        matcher.matches();
        if (!matcher.matches()){
            throw new  HandlerException ();
        }
        this.email = email;
    }

    public User(String name, String email) throws HandlerException {
        this.name = name;
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        matcher.matches();
        if (!matcher.matches()){
            throw new  HandlerException ();
        }
        this.email = email;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws HandlerException {

     String regex = "^(.+)@(.+)$";
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(email);
     System.out.println(email +" : "+ matcher.matches());
        if (!matcher.matches()){
            throw new  HandlerException ();
        }
     this.email = email;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
