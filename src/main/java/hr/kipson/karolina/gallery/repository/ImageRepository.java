package hr.kipson.karolina.gallery.repository;

import hr.kipson.karolina.gallery.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByName(String text);

    Image findFirstByName(String text);
}
