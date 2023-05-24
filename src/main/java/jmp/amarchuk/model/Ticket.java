package jmp.amarchuk.model;

import javax.persistence.*;

@Entity
@Table
public class Ticket {

    @Id
    @Column(name = "id")
    private Long id;
    private Long eventId;
    private Long userId;

    @Column(name = "category")
    private Category category;

    public Long getEventId() {
        return eventId;
    }

    public Long getUserId() {
        return userId;
    }

    @Column(name = "place")
    private Integer place;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="eventId",referencedColumnName="id",insertable=false, updatable=false)
    private Event event;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="userId",referencedColumnName="id",insertable=false, updatable=false)
    private User user;

    public Ticket(long eventId, long userId, Category category, int place) {
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

    public Ticket() {
    }

    public Ticket(Long id, Category category, Integer place) {
        this.id=id;
        this.category = category;
        this.place = place;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public User getUser() {
        return user;
    }


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", userId=" + userId +
                ", category=" + category +
                ", place=" + place +
                '}'+'\n';
    }
}
