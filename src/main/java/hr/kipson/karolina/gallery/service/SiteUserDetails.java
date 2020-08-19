package hr.kipson.karolina.gallery.service;



import hr.kipson.karolina.gallery.model.SiteUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import hr.kipson.karolina.gallery.repository.SiteUserRepository;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;
import java.util.Optional;

@Component
public class SiteUserDetails implements UserDetailsService {

    private SiteUserRepository siteUserRepository;

    public SiteUserDetails(SiteUserRepository siteUserRepository) {
        this.siteUserRepository = siteUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return siteUserRepository.findByName(name)
                .map(user -> new User(user.getName(), user.getPassword(), user.isEnabled(),
                        user.isEnabled(), user.isEnabled(), user.isEnabled(),
                        AuthorityUtils.createAuthorityList("USER")
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public SiteUser save(SiteUser siteUser){
        return siteUserRepository.save(siteUser);
    }

    public List<SiteUser> findAll(){
        return siteUserRepository.findAll();
    }

   public Optional<SiteUser> findByName(String name){
        return siteUserRepository.findByName(name);
   };
}
