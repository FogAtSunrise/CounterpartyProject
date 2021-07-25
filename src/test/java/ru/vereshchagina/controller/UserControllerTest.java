package ru.vereshchagina.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vereshchagina.Application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest(classes = Application.class)
@ContextConfiguration
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CounterpartyController userController;
//
//    @Test
//    public void contextLoads() {
//        assertThat(userController).isNotNull();
//    }
//
//    @Test
//    public void test_search() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .get("/counterparty"))
//                .andExpect(view().name("counterparty"))
//                .andExpect(status().isOk());
  //  }
}