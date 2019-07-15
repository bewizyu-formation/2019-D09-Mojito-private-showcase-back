package fr.formation.artist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.formation.event.Event;
import fr.formation.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "artist")

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class Artist extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "imageType")
    private String imageType;

    @Column(name = "adress")
    private String adress;



    /**
     * Get Adress
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * set the adress
     * @param adress : the adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Gets id.
     *
     * @return the id
     */

    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * Gets imageType.
     *
     * @return the imageType
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * Sets imageType.
     *
     * @param imageType the imageType
     */
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }




}
