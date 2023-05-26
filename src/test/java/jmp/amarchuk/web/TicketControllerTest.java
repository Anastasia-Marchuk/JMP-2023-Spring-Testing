package jmp.amarchuk.web;

import jmp.amarchuk.model.Category;
import jmp.amarchuk.test.config.SpringConfigTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfigTest.class)
@WebAppConfiguration
class TicketControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    UserController userController;

    private static Logger logger= LoggerFactory.getLogger(TicketControllerTest.class);

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void startPage() throws Exception {
        logger.info("Start testing landing page... ");
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }

    @Test
    void allTickets() throws Exception {
        logger.info("Start testing list of all tickets... ");
        mvc.perform(MockMvcRequestBuilders.get("/allTickets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("list_tickets"));
    }

    @Test
    void createTicket() throws Exception {
        logger.info("Start testing creating ticket... ");
        mvc.perform(MockMvcRequestBuilders.get("/createTicket")
                .param("eventId", String.valueOf(2))
                .param("userId", String.valueOf(2))
                .param("category", String.valueOf(Category.PREMIUM))
                .param("place", String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}