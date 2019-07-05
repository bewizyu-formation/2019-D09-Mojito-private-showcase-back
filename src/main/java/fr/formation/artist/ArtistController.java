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
     * list all common users
     * @return list of the common users
     */
    @GetMapping(value = "/",produces = "application/json")
    public List<Artist> listArtist(){

        List<Artist> listArtist =  artistService.listArtist();

        return listArtist;
    }

    /**
     *  get common user with given id
     * @param id : the id of the user to look for
     * @return the user with id 'id'
     */
    @GetMapping(value = "/id/{id}",produces = "application/json")
    public Artist getArtistInfoById(@PathVariable long id){

        return artistService.artistById(id);
    }

    /**
     *  get common user with given username
     *
     * @param username : the username of the user to look for
     * @return the user named 'username"
     */
    @GetMapping(value="/name/{username}",produces = "application/json")
    public Artist getArtistInfoByUsername(@PathVariable String username){
        return artistService.artistByUsername(username);
    }

    /**
     * add a new user with given json class
     * @param newUser : the user given by request body
     * @return true if the user has been added, false otherwise
     */
    @PutMapping(value = "/", consumes = "application/json")
    public boolean registerArtist(@RequestBody Artist newUser,@RequestParam String password) {
        newUser.setPassword(password);
        return artistService.addArtist(newUser);
    }

    /**
     *  modify the user with given id
     * @param id : the id of the user to modify
     * @param newUser : the new data
     * @return true if the user has been modified, false otherwise
     */
    @PostMapping(value = "/id/{id}", consumes = "application/json")
    public boolean modifyArtist(@PathVariable long id,@RequestBody Artist newUser) {
        return artistService.modifyArtist(id,newUser);
    }

}
