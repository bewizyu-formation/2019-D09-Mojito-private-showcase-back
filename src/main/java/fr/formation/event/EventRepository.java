package fr.formation.event;

import fr.formation.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Find all common users.
     **
     * @return the list of users
     */
    public List<Event> findAll();



    /**
     * find the common user of given id
     * @param id : the id of the common user to search
     * @return an optional conataining the common user of id 'id' if it exists
     */
    public Optional<Event> findById(Long id);
}
