package hr.kipson.karolina.gallery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Image(){}

    public Image(String name) {
        this.name = name;
    }

    public Image(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Image [id=" + id + ", name=" + name + "]";
    }

    public static class ImageBuilder{
        private Long id;
        private String name;

        public ImageBuilder(){}

        public ImageBuilder(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public ImageBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ImageBuilder setName(String name) {
            this.name = name;
            return this;
        }
        public Image build() {
            return new Image(id, name);
        }

    }
}
