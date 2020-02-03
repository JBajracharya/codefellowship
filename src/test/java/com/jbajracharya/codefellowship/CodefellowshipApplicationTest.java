package com.jbajracharya.codefellowship;

import com.jbajracharya.codefellowship.controller.ApplicationUserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CodefellowshipApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationUserController applicationUserController;

    @Test
    void contextLoads() {
    }

    @Test
    public void functionalHomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>WELCOME TO CODEFELLOWSHIP CONNECT</h1>")))
                .andExpect(content().string(containsString("<a href=\"/sign-up\"><button>Sign up</button></a>\n")))
                .andExpect(content().string(containsString("<a href=\"/log-in\"><button>Sign in</button></a>")));
    }

    @Test
    public void profilePage() throws Exception {
        this.mockMvc.perform(get("/log-in"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Password: <input type=\"password\" name=\"password\">")))
                .andExpect(content().string(containsString("Username: <input type=\"text\" name=\"username\">")));
    }


}