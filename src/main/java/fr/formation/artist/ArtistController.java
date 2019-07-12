package fr.formation.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    /**
     * list all artists
     *
     * @return list of the common users
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Artist>> listArtist() {
        try {
            List<Artist> result = artistService.listArtists();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * get artist with given id
     *
     * @param id : the id of the user to look for
     * @return the user with id 'id'
     */
    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<Artist> getArtistInfoById(@PathVariable long id) {
        try {
            Artist artist = artistService.artistById(id);
            if (artist == null) {
                return new ResponseEntity<>(artist, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(artist, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get artist with given username
     *
     * @param username : the username of the user to look for
     * @return the user named 'username"
     */
    @GetMapping(value = "/name/{username}", produces = "application/json")
    public ResponseEntity<Artist> getArtistInfoByUsername(@PathVariable String username) {
        try {
            Artist artist = artistService.artistByUsername(username);
            if (artist == null){
                return new ResponseEntity<>(artist, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(artist, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * add a new artist with given json class
     *
     * @param newArtist : the artist given by request body
     * @return true if the artist has been added, false otherwise
     */
    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Boolean> registerArtist(@RequestBody Artist newArtist, @RequestParam String password) {
        newArtist.setPassword(password);

        try {
            if (artistService.addArtist(newArtist)) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {

            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * modify the user with given id
     *
     * @param id        : the id of the user to modify
     * @param newArtist : the new data
     * @return the updated artist or null if no update happened
     */
    @PostMapping(value = "/id/{id}", consumes = "application/json")
    public ResponseEntity<Artist> modifyArtist(@PathVariable long id, @RequestBody Artist newArtist) {
        Artist artistUpdated;
            try {
                artistUpdated = artistService.modifyArtist(id, newArtist);
                if (artistUpdated == null) {
                    return new ResponseEntity<>(artistUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
    }
