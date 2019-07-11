package fr.formation.reservation;

import fr.formation.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Event, Long> {

    /**
     * Find all common users.
     **
     * @return the list of users
     */
    public List<Event> findAll();

    /**
     * find the common user of given username
     * @param date : the username of the common user to search
     * @return the common user with 'username' as username, or null
     */
    public Event findByDate(int date);

    /**
     * find the common user of given id
     * @param id : the id of the common user to search
     * @return an optional conataining the common user of id 'id' if it exists
     */
    public Optional<Event> findById(Long id);
}
