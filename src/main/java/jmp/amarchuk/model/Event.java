package jmp.amarchuk.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table
public class Event {

    @Id
    @Column(name = "id")
    private long Id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private double price;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "event",cascade=CascadeType.ALL)
    private List<Ticket> tickets;

    public Event(Long id, String title, Date date, Double price) {
        this.Id = id;
        this.title = title;
        this.date = date;
        this.price=price;
    }

    public Event(Long id, String title, Date date) {
        this.Id = id;
        this.title = title;
        this.date = date;
    }

    public Event( String title, Date date) {
        this.title = title;
        this.date = date;
    }


    public Event() {
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return getId() == event.getId() &&
                Objects.equals(getTitle(), event.getTitle()) &&
                Objects.equals(getDate(), event.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDate());
    }

    @Override
    public String toString() {
        return "Event{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}
