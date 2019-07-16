package fr.formation.event;

import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public interface EventCustomDaoI {

    @Query("SELECT e FROM Event e WHERE date < :now")
    public List<Event> getAllPassedEvents(LocalDateTime now);

    @Query("SELECT e FROM Event e WHERE date >= :now")
    public List<Event> getAllIncommingEvents(LocalDateTime now);

    @Query("SELECT e FROM Event e WHERE e.owner.id = :id")
    public List<Event> getOwnEvents(long id);

    @Query("SELECT e FROM Event e WHERE date < :now AND e.owner.id = :id")
    public List<Event> getPassedOwnedEvents(long id, LocalDateTime now);

    @Query("SELECT e FROM Event e WHERE date >= :now AND e.owner.id = :id")
    public List<Event> getIncomingOwnedEvents(long id, LocalDateTime now);

}

