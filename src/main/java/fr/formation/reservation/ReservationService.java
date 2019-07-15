package fr.formation.reservation;

import fr.formation.event.Event;
import fr.formation.event.EventDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private EventDaoRepository EventDaoRepository;
    private ReservationRepository reservationRepository;
    /**
     * Instantiate a new Event Service
     *
     * @param EventDaoRepository the event repository
     * @param reservationRepository the reservation repository
     */

    @Autowired
    public ReservationService(
            ReservationRepository reservationRepository,
            EventDaoRepository EventDaoRepository

    ) {
        this.reservationRepository = reservationRepository;
        this.EventDaoRepository = EventDaoRepository;
    }


    public List<Reservation> listReservations() {

        return reservationRepository.findAll();
    }

    public boolean add(Reservation reservation) throws PersistenceException {

        try {
            reservationRepository.save(reservation);
        }
        catch
        (PersistenceException ex) {
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public Reservation getReservationById(Long id) {
        Optional<Reservation> optReservation = this.reservationRepository.findById(id);

        try{
            optReservation.isPresent();
            return optReservation.get();
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * modify a given event with new data
     * @param id : the id of the artist to change
     * @param reservation : the new event for the user
     * @return true if the artist has been modified, false otherwise
     */
    public boolean modifyReservation(long id, Reservation reservation){
        Reservation reservationToUpdate = this.getReservationById(id);
        if(reservationToUpdate == null){
            return false;
        }else{

            reservationToUpdate.setDate(reservation.getDate());
            reservationToUpdate.setNbPlace(reservation.getNbPlace());

            reservationRepository.save(reservationToUpdate);

            return true;
        }
    }




}
