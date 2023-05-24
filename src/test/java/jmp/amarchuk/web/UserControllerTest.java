//package jmp.amarchuk.web;
//
//import jmp.amarchuk.model.User;
//import jmp.amarchuk.web.handler.HandlerException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = {"classpath:config.xml"})
//@WebAppConfiguration
//class UserControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @Autowired
//    UserController userController;
//
//    private static Logger logger= LoggerFactory.getLogger(UserControllerTest.class);
//
//    @BeforeEach
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .build();
//    }
//
//    @Test
//    void startPage() throws Exception {
//        logger.info("Start testing landing page... ");
//        mvc.perform(MockMvcRequestBuilders.get("/"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("welcome"));
//    }
//
//    @Test
//    void allUsers() throws Exception {
//        logger.info("Start testing list of all users... ");
//        mvc.perform(MockMvcRequestBuilders.get("/allUsers"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("list_users"));
//    }
//
//    @Test
//    void createUser() throws Exception, HandlerException {
//        logger.info("Start testing creating user... ");
//        User user = new User("testName", "test@test.com");
//        mvc.perform(MockMvcRequestBuilders.get("/create")
//                .param("name", user.getName())
//                .param("email", user.getEmail()))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/"));
//
//    }
//
//    @Test
//    void newUser() throws Exception {
//        logger.info("Start testing page for creating user... ");
//        mvc.perform(MockMvcRequestBuilders.get("/new"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("new_user"));
//    }
//
//    @Test
//    void updateUser() throws Exception, HandlerException {
//
//        logger.info("Start testing updating user... ");
//        User user = new User(1,"testName", "test@test.com");
//        mvc.perform(MockMvcRequestBuilders.get("/update/1")
//                .param("name", user.getName())
//                .param("email", user.getEmail()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("update_user"));
//
//
//    }
//
//    @Test
//    void deleteUser() throws Exception {
//        logger.info("Start testing deleting user... ");
//        mvc.perform(get("/delete/{id}", 1)
//        ).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("facade"));
//    }
//
//    @Test
//    void getUser() throws Exception {
//        mvc.perform(get("/user/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(view().name("facade"));
//    }
//
//
//    @Test
//    void getUserByName() throws Exception {
//        logger.info("Start testing getting user by Name... ");
//        mvc.perform(get("/user/name")
//                .param("name", "Stacy"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("facade"));
//    }
//
//    @Test
//    void getUserByEmail() throws Exception {
//        logger.info("Start testing getting User by Email... ");
//        mvc.perform(get("/user/email")
//                .param("email", "test@test.com"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("list_users"));
//    }
//}