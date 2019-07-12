package fr.formation.event;

import fr.formation.reservation.Reservation;
import fr.formation.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventRepository eventRepository;
    private ReservationRepository reservationRepository;
    /**
     * Instantiate a new Event Service
     *
     * @param eventRepository the event repository
     * @param reservationRepository the reservation repository
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

    public boolean add(Event event) throws PersistenceException {

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

    public Event getEventById(Long id) {
        Optional<Event> optEvent = this.eventRepository.findById(id);

        try{
            optEvent.isPresent();
            return optEvent.get();
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * modify a given event with new data
     * @param id : the id of the artist to change
     * @param event : the new event for the user
     * @return true if the artist has been modified, false otherwise
     */
    public boolean modifyEvent(long id, Event event){
        Event eventToUpdate = this.getEventById(id);
        if(eventToUpdate == null){
            return false;
        }else{

            eventToUpdate.setAdress(event.getAdress());
            eventToUpdate.setDate(event.getDate());
            eventToUpdate.setHour(event.getHour());
            eventToUpdate.setNbPlace(event.getNbPlace());
            eventRepository.save(eventToUpdate);

            return true;
        }
    }
}
