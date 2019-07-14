package fr.formation.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.formation.artist.Artist;
import fr.formation.reservation.Reservation;
import fr.formation.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "event")

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date")
    private LocalDate date ;

    @Column(name = "hour")
    private int hour;

    @Column(name = "adress")
    private String adress;

    @Column(name = "nbPlace")
    private int nbPlace;

    /**
     * link events to user when creating events
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User owner;



    /**
     * attach an event to an artist when booking
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Artist artist;


    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets hour.
     *
     * @return the hour
     */
    public int getHour() {
        return hour;
    }
    /**
     * Sets hour.
     *
     * @param hour the hour
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Gets adress.
     *
     * @return the hour
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Sets adress.
     *
     * @param adress the adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Gets nbPlace.
     *
     * @return the nbPlace
     */
    public int getNbPlace() {
        return nbPlace;
    }

    /**
     * Sets nbPlace.
     *
     * @param nbPlace the nbPlace
     */
    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
