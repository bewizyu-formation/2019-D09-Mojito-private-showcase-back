package fr.formation.event;

import fr.formation.artist.Artist;
import fr.formation.userCommun.UserCommun;
import fr.formation.userCommun.UserCommunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;


    /**
     * list all artists
     * @return list of the common users
     */
    @GetMapping(value = "/",produces = "application/json")
    public List<Event> listEvent(@RequestParam(required = false, value="past") boolean passed, @RequestParam(required = false, value="incoming") boolean incoming,
                                 @RequestParam(required = false, value="owner") String id ) {

        if (id == null) {
            id = "0";
        }
        Long long_id = Long.parseLong(id);

        if( long_id == 0 ) {

            if (passed && !incoming ) {

                return eventService.ListPassedEvents();

            }

            if (incoming && !passed) {

                return eventService.ListNextEvents();

            }
            if (incoming && passed) {
                return eventService.listEvents();
            }
        }

        if (long_id >= 1) {

            if (passed && !incoming) {

                return eventService.getPassedEventsOwned(long_id);
            }

            if (!passed && incoming) {

                return eventService.getIncomingEventsOwned(long_id);
            }

            if (passed && incoming) {

                return eventService.getEventOwned(long_id);
            }
        }

        if ((long_id == 0) && !incoming && !passed)  {
            return null;
        }

        return eventService.listEvents();


    }

    @GetMapping(value = "/id/{id}",produces = "application/json")
    public Event getEventById(@PathVariable long id){

        return eventService.getEventById(id);

    }

    /**
     *  modify the user with given id
     * @param id : the id of the user to modify
     * @param event : the new data
     * @return true if the user has been modified, false otherwise
     */
    @PutMapping(value = "/id/{id}", consumes = "application/json")
    public boolean modifyEvent(@PathVariable long id,@RequestBody Event event) {
        return eventService.modifyEvent(id,event);
    }

    @PostMapping(value="/{id}/add", consumes = "application/json")
    public boolean addEvent(@PathVariable long id, @RequestBody Event event) {

         return eventService.addEvent(id, event);
    }
}
