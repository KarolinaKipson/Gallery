package hr.kipson.karolina.gallery.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    private final String userName = "pat";
    private final String userPass = "pat";
    private final String invalidUser = "invalid";

    @Test
    public void validUserLogin() throws Exception {
        this.mockMvc
                .perform(
                        get("/login")
                                .param("name", userName)
                                .param("password", userPass)
                                .param("enabled", "true")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())

                )
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }
    @Test
    public void invalidUserLogin() throws Exception {
        this.mockMvc
                .perform(
                        get("/login")
                                .param("name", invalidUser)
                                .param("password", invalidUser)
                                .param("enabled", "true")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())

                )
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }
    @Test
    public void findAllUsers() throws Exception {
        this.mockMvc
                .perform(
                        get("/users")
                )
                .andExpect(status().isOk());
    }

}
