package fr.formation.reservation;

import javax.persistence.*;


@Entity
@Table(name = "reservation")


public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private int date;

    @Column(name = "nbPlace")
    private int nbPlace;

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
    public int getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
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
}
