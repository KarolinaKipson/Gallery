package hr.kipson.karolina.gallery.controller;

import hr.kipson.karolina.gallery.aop.TrackExecutionTime;
import hr.kipson.karolina.gallery.form.SiteUserForm;
import hr.kipson.karolina.gallery.model.SiteUser;
import hr.kipson.karolina.gallery.repository.SiteUserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final SiteUserRepository siteUserRepository;

    public UserController(SiteUserRepository siteUserRepository) {
        this.siteUserRepository = siteUserRepository;
    }

    @TrackExecutionTime
    @GetMapping("/register")
    public String register(Model model, SiteUserForm siteUserForm) {
        return "register";
    }

    @TrackExecutionTime
    @PostMapping("/register")
    public String register(Model model, SiteUser siteUser, SiteUserForm siteUserForm, BindingResult result) {

        if (siteUserForm.getName().matches("^[a-zA-Z0-9]{3,}$")){
            siteUser = new SiteUser(siteUserForm.getName().trim(),
                    new BCryptPasswordEncoder().encode(siteUserForm.getPassword()), true);
        }
        else {
            result.rejectValue("name", "username");
            return "register";
        }

        try {
            this.siteUserRepository.save(siteUser);
        } catch(DataIntegrityViolationException ex){
            ex.printStackTrace();
            result.rejectValue("name", "name");
            return "register";
        }

        return "redirect:/login";
    }

    @TrackExecutionTime
    @GetMapping("/users")
    public String listUsers(Model model){
        model.addAttribute("users", siteUserRepository.findAll());
        return "users";
    }

    @TrackExecutionTime
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @TrackExecutionTime
    @GetMapping("/login?logout")
    public String logout() {
        return "login";
    }
}
