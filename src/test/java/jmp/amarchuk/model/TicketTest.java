package jmp.amarchuk.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest {

    Ticket ticket=new Ticket((long)1,(long)1,Category.BAR,5);


    @Test
    public void testGetId(){
        assertEquals(1,ticket.getEventId());
    }
    @Test
    public void testGetIdUser(){
        assertEquals(1,ticket.getUserId());
    }
    @Test
    public void testGetCategory(){
        assertEquals(Category.BAR,ticket.getCategory());
    }

    @Test
    public void testGetPlace(){
        assertEquals(5,ticket.getPlace());
    }


}