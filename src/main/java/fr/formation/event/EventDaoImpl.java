package fr.formation.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EventDaoImpl implements EventCustomDaoI {


    public Set<Event> getPassedEvents(){
        return new HashSet<Event>();
    }

    public Set<Event> getNextEvents(){
        return new HashSet<Event>();

    }

    @Override
    public List<Event> getAllPassedEvents(LocalDateTime passed) {
        return null;
    }

    @Override
    public List<Event> getAllIncommingEvents(LocalDateTime passed) {
        return null;
    }

    @Override
    public List<Event> getOwnEvents(long id) {
        return null;
    }

    @Override
    public List<Event> getOwnPassedEvents(long id, LocalDateTime passed) {
        return null;
    }

}


