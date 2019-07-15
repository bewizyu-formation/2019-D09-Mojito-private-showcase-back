package fr.formation.event;

import fr.formation.reservation.Reservation;
import fr.formation.reservation.ReservationRepository;
import fr.formation.user.User;
import fr.formation.user.UserRepository;
import fr.formation.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventDaoRepository eventRepository;
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;


    /**
     * Instantiate a new Event Service
     *
     * @param eventRepository the event repository
     * @param reservationRepository the reservation repository
     */

    @Autowired
    public EventService(
            ReservationRepository reservationRepository,
            EventDaoRepository eventRepository,
            UserRepository userRepository


    ) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Autowired
    public UserService userService;


    public List<Event> listEvents() {

        return eventRepository.findAll();
    }


    public List<Event> ListPassedEvents() {
        LocalDateTime now = LocalDateTime.now();
        return eventRepository.getAllPassedEvents(now);
    }

    public List<Event> ListOwnerEvents(long id) {
        return eventRepository.getOwnEvents(id);
    }

    public List<Event> ListNextEvents() {
        LocalDateTime now = LocalDateTime.now();
        return eventRepository.getAllIncommingEvents(now);
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

            eventToUpdate.setDate(event.getDate());
            eventToUpdate.setNbPlace(event.getNbPlace());
            eventRepository.save(eventToUpdate);

            return true;
        }
    }


    public boolean addEvent(long id, Event event ) {

        User user = this.userService.userById(id);
        System.out.println("user =>");
        System.out.println(user.getId());

        try {

            Event evt = new Event();
            evt.setNbPlace(event.getNbPlace());
            evt.setDate(event.getDate());
            evt.setId(event.getId());
            evt.setOwner(user);
            evt.setArtist(event.getArtist());
            System.out.println(evt);


            eventRepository.save(evt);
            userRepository.save(user);
            //user.addEvent(evt);


            return true;

        }
        catch(Error err){
            System.out.println(err);

            return false;
        }

    }

}
