package hr.kipson.karolina.gallery.repository;


import hr.kipson.karolina.gallery.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long>  {
    Optional<SiteUser> findByName(String name);
    SiteUser save(SiteUser siteUser);
}
