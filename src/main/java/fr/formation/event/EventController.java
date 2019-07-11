package fr.formation.event;

import fr.formation.artist.Artist;
import fr.formation.userCommun.UserCommun;
import fr.formation.userCommun.UserCommunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Event> listEvent(){

        List<Event> listEvents =  eventService.listEvents();

        return listEvents;
    }

    @PutMapping(value="/",consumes = "application/json")
    public boolean Create(@RequestBody Event event) {

        return eventService.add(event);
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
    @PostMapping(value = "/id/{id}", consumes = "application/json")
    public boolean modifyArtist(@PathVariable long id,@RequestBody Event event) {
        return eventService.modifyEvent(id,event);
    }
}
