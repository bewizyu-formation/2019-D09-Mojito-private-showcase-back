package fr.formation.votes;

import javax.persistence.*;


@Entity
@Table(name = "votes")

public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "note")
    private int note;

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
     * Gets note.
     *
     * @return the note
     */
    public int getNote() {
        return note;
    }
    /**
     * Sets note.
     *
     * @param note the note
     */
    public void setNote(int note) {
        this.note = note;
    }
}
