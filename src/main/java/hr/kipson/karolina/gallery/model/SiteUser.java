package hr.kipson.karolina.gallery.model;

import hr.kipson.karolina.gallery.pricing.BillingStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Image> imageList;

    public SiteUser(){}

    public SiteUser(String name, String password, boolean enabled) {
        this.name = name;
        this.password = password;
        this.enabled = enabled;
        this.setStrategy(BillingStrategy.smallStrategy());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Image> getImageList() {
        return imageList;
    }

    public void setImageList(Set<Image> imageList) {
        this.imageList = imageList;
    }

    @Transient
    public BillingStrategy strategy;


    public void setStrategy(BillingStrategy strategy) {
        this.strategy = strategy;
    }
}
