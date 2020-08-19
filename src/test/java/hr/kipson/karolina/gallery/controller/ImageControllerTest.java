package hr.kipson.karolina.gallery.controller;

import hr.kipson.karolina.gallery.model.SiteUser;
import hr.kipson.karolina.gallery.repository.ImageRepository;
import hr.kipson.karolina.gallery.repository.SiteUserRepository;
import hr.kipson.karolina.gallery.service.SiteUserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ImageControllerTest {

    private final String userName = "user";
    private final List<String> userImages = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @Test
    void listUploadedFiles() throws Exception {
        userImages.add("http://localhost/files/2698a921-707d-4c97-82bb-6aee17edbef7.jpg");
        this.mockMvc
                .perform(
                        get("/search")
                                .param("name", userName)

                )
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", userName))
                .andExpect(model().attribute("files", userImages ))
                .andExpect(view().name("findphoto"));
    }

}
