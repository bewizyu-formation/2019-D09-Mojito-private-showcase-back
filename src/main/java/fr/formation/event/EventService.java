package fr.formation.event;

import fr.formation.event.Event;
import fr.formation.event.EventRepository;

import fr.formation.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

import static fr.formation.security.SecurityConstants.ROLE_USER_SUFFIX;

@Service
public class EventService {

    private EventRepository eventRepository;
    private ReservationRepository reservationRepository;
    /**
     * Instantiate a new Event Service
     *
     * @param eventRepository
     * @param reservationRepository
     */

    @Autowired
    public EventService(
            ReservationRepository reservationRepository,
            EventRepository eventRepository

    ) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
    }


    public List<Event> listEvents() {

        return eventRepository.findAll();
    }

    public boolean add(Event event) {

        try {
            eventRepository.save(event);
        }
        catch
        (PersistenceException ex) {
            System.out.println(ex);
            return false;
        }

        return true;
    }
}
