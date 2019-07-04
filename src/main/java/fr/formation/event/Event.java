package fr.formation.event;

import javax.persistence.*;


@Entity
@Table(name = "event")


public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private int date;

    @Column(name = "hour")
    private int hour;

    @Column(name = "adress")
    private String adress;

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
}
