package fr.formation.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.formation.event.Event;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "reservation")


public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date")
    private LocalDate date ;

    @Column(name = "nbPlace")
    private int nbPlace;

    @ManyToOne()
    @JoinColumn (name="event_id",referencedColumnName="id",unique=true)
    private Event event;

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
     * Gets NbPlace.
     *
     * @return the NbPlace
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
