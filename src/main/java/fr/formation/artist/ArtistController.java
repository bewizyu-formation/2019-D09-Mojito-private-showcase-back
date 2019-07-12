package fr.formation.artist;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private ArtistService artistService;

    /**
     * list all artists
     *
     * @return list of the common users
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Object> listArtist() {

        List<Artist> result = artistService.listArtists();
        if (result == null) {
            return new ResponseEntity<>("Liste non disponible", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }


    /**
     * get artist with given id
     *
     * @param id : the id of the user to look for
     * @return the user with id 'id'
     */
    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<Object> getArtistInfoById(@PathVariable long id) {

        Artist result = artistService.artistById(id);
        if (result == null) {
            return new ResponseEntity<>("parametres invalides", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    /**
     * get artist with given username
     *
     * @param username : the username of the user to look for
     * @return the user named 'username"
     */
    @GetMapping(value = "/name/{username}", produces = "application/json")
    public ResponseEntity<String> getArtistInfoByUsername(@PathVariable String username) {
        Artist artist = artistService.artistByUsername(username);
        try {
            return new ResponseEntity<>(mapper.writeValueAsString(artist), HttpStatus.OK);
        } catch (Exception e) {}

        return new ResponseEntity<>("could not find artist", HttpStatus.BAD_REQUEST);
    }

    /**
     * add a new artist with given json class
     *
     * @param newArtist : the artist given by request body
     * @return true if the artist has been added, false otherwise
     */
    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<String> registerArtist(@RequestBody Artist newArtist, @RequestParam String password) {
        newArtist.setPassword(password);
        boolean result = artistService.addArtist(newArtist);
        try {
            return new ResponseEntity<String>(mapper.writeValueAsString(newArtist), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<String>("Artiste non ajouté", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * modify the user with given id
     *
     * @param id        : the id of the user to modify
     * @param newArtist : the new data
     * @return true if the user has been modified, false otherwise
     */
    @PostMapping(value = "/id/{id}", consumes = "application/json")
    public ResponseEntity<String> modifyArtist(@PathVariable long id, @RequestBody Artist newArtist) {
        Artist artistUpdated = artistService.modifyArtist(id, newArtist);
        if (artistUpdated != null) {
            try {
                return new ResponseEntity<>(mapper.writeValueAsString(artistUpdated), HttpStatus.OK);
            } catch (Exception e) {
            }
        }
        return new ResponseEntity<>("Modification non prise en compte", HttpStatus.BAD_REQUEST);
    }
}