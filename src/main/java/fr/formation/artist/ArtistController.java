package fr.formation.artist;

import fr.formation.userCommun.UserCommun;
import fr.formation.userCommun.UserCommunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;


    /**
     * list all artists
     * @return list of the common users
     */
    @GetMapping(value = "/",produces = "application/json")
    public List<Artist> listArtist(){

        List<Artist> listArtist =  artistService.listArtists();

        return listArtist;
    }

    /**
     *  get artist with given id
     * @param id : the id of the user to look for
     * @return the user with id 'id'
     */
    @GetMapping(value = "/id/{id}",produces = "application/json")
    public Artist getArtistInfoById(@PathVariable long id){

        return artistService.artistById(id);
    }

    /**
     *  get artist with given username
     *
     * @param username : the username of the user to look for
     * @return the user named 'username"
     */
    @GetMapping(value="/name/{username}",produces = "application/json")
    public Artist getArtistInfoByUsername(@PathVariable String username){
        return artistService.artistByUsername(username);
    }

    /**
     * add a new artist with given json class
     * @param newArtist : the artist given by request body
     * @return true if the artist has been added, false otherwise
     */
    @PutMapping(value = "/", consumes = "application/json")
    public boolean registerArtist(@RequestBody Artist newArtist,@RequestParam String password) {
        newArtist.setPassword(password);
        return artistService.addArtist(newArtist);
    }

    /**
     *  modify the user with given id
     * @param id : the id of the user to modify
     * @param newArtist : the new data
     * @return true if the user has been modified, false otherwise
     */
    @PostMapping(value = "/id/{id}", consumes = "application/json")
    public boolean modifyArtist(@PathVariable long id,@RequestBody Artist newArtist) {
        return artistService.modifyArtist(id,newArtist);
    }

}
