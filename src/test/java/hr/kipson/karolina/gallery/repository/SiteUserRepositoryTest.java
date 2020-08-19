package hr.kipson.karolina.gallery.repository;

import hr.kipson.karolina.gallery.model.SiteUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class SiteUserRepositoryTest {

    @Autowired
    SiteUserRepository userRepository;

    @Test
    public void save() {
        SiteUser user = new SiteUser("Tom", "Tom123", true);
        userRepository.save(user);
        Assert.assertNotNull(userRepository.findByName("Tom").get());
    }

    @Test
    public void delete() {
        SiteUser user = userRepository.findByName("Tom").get();
        userRepository.delete(user);

        Assert.assertNull(findUser(userRepository));

        }
        public SiteUser findUser(SiteUserRepository userRepository){
            if (userRepository.findByName("Tom").isPresent()) {
                return userRepository.findByName("Tom").get();
            } else {
               return null;
            }
        }
    }



