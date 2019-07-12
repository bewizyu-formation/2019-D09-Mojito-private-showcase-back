package fr.formation.reservation;

import fr.formation.reservation.Reservation;
import fr.formation.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    /**
     * list all artists
     * @return list of the common users
     */
    @GetMapping(value = "/",produces = "application/json")
    public List<Reservation> listReservations(){

        List<Reservation> listReservations =  reservationService.listReservations();

        return listReservations;
    }

    @PutMapping(value="/",consumes = "application/json")
    public boolean Create(@RequestBody Reservation reservation) {

        return reservationService.add(reservation);
    }

    @GetMapping(value = "/id/{id}",produces = "application/json")
    public Reservation getreservationById(@PathVariable long id){

        return reservationService.getReservationById(id);

    }

    /**
     *  modify the user with given id
     * @param id : the id of the user to modify
     * @param reservation : the new data
     * @return true if the user has been modified, false otherwise
     */
    @PostMapping(value = "/id/{id}", consumes = "application/json")
    public boolean modifyReservation(@PathVariable long id,@RequestBody Reservation reservation) {
        return reservationService.modifyReservation(id, reservation);
    }
}
