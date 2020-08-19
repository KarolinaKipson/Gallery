package hr.kipson.karolina.gallery.service;

import hr.kipson.karolina.gallery.model.SiteUser;
import hr.kipson.karolina.gallery.repository.SiteUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteUserDetailsTest {

    @Autowired
    SiteUserDetails siteuserdetails;

    @Autowired
    SiteUserRepository siteUserRepository;

    private String name = "Tom";
    private String pass = "Tom123";
    private boolean isEnabled = true;
    private String nonUserName= "NonUser";

    @Test
    public void createUser() {
        SiteUser dummySiteUser = new SiteUser();
        dummySiteUser.setName(name);
        dummySiteUser.setPassword(pass);
        dummySiteUser.setEnabled(isEnabled);

        siteUserRepository.save(dummySiteUser);
        assert(siteUserRepository.findByName(name).isPresent());
    }
    @Test
    public void loadUserByUsername(){
        UserDetails userDetails = siteuserdetails.loadUserByUsername(name);
        SiteUser user = siteUserRepository.findByName(name).get();
        assertEquals(userDetails.getUsername(), user.getName());
    }
    @Test
    public void loadNonExistingUser(){
     assertFalse(helpAssertUser(siteUserRepository, nonUserName));
    }
    @Test
    public void deleteUser(){
        SiteUser user = siteUserRepository.findByName(name).get();
        assertTrue(helpAssertUser(siteUserRepository, name));
        siteUserRepository.delete(user);
        assertFalse(helpAssertUser(siteUserRepository, name));
    }

    public boolean helpAssertUser(SiteUserRepository siteUserRepository, String name){
        // User exists.
        if(siteUserRepository.findByName(name).isPresent()){
            return true;
        } else { // Can not find user.
            return false;
        }
    }
}