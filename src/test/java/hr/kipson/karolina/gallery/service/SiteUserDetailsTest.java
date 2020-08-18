package hr.kipson.karolina.gallery.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

class SiteUserDetailsTest {

    @MockBean
    SiteUserDetails siteuserdetails;

    @Autowired
    MockMvc mockMvc;

    @Test
    void loadUserByUsername() {


    }
}