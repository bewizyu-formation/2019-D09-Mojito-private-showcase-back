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
    public List<Event> listEvent(@RequestParam(required = false, value="passed") boolean passed, @RequestParam(required = false, value="incomming") boolean incomming,
                                 @RequestParam(required = false, value="id") String id ) {
        if (passed) {

            return eventService.ListPassedEvents();

        }

        if (incomming) {

            return eventService.ListNextEvents();

        }

        if (id != null) {
            Long long_id = Long.parseLong(id);
            if (long_id >= 1) {

                return eventService.ListOwnerEvents(long_id);
            }
        }

        if ((id == null) && !incomming && !passed) {
            return null;
        }

        return eventService.listEvents();

    }

    public List<Event> listEvent(){

        List<Event> listEvents =  eventService.listEvents();

        return listEvents;
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
