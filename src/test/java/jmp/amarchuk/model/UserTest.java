package jmp.amarchuk.model;

import jmp.amarchuk.web.handler.HandlerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    User user=new User(1,"Stacy","stacy@gmail.com");

    public UserTest() throws HandlerException {
    }

    @Test
    public void testGetNameUser(){
        assertEquals("Stacy",user.getName());
    }
    @Test
    public void testGetIdUser(){
        assertEquals(1,user.getId());
    }
    @Test
    public void testGetEmailUser(){
        assertEquals("stacy@gmail.com",user.getEmail());
    }


}