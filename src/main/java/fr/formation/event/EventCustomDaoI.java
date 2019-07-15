package fr.formation.event;

import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public interface EventCustomDaoI {

    //public Set<Event> getPassedEvents();

    //public Set<Event> getNextEvents();

    //public

    @Query("SELECT e FROM Event e WHERE date < :passed")
    public List<Event> getAllPassedEvents(LocalDateTime passed);

    @Query("SELECT e FROM Event e WHERE date >= :passed")
    public List<Event> getAllIncommingEvents(LocalDateTime passed);

    @Query("SELECT e FROM Event e WHERE e.owner.id = :id")
    public List<Event> getOwnEvents(long id);

}

