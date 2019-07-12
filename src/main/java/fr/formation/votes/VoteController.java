package fr.formation.votes;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.formation.artist.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Object> listVote() {

        List<Vote> result = voteService.listVotes();
        if (result == null) {
            return new ResponseEntity<>("Liste non disponible", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }


    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<String> voteById(@PathVariable long id) {
        ObjectMapper mapper = new ObjectMapper();

        Vote result = voteService.voteById(id);
        try {
            return new ResponseEntity<>(mapper.writeValueAsString(result), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("vote introuvable", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<String> registerVote(@RequestBody Vote newVote, @RequestParam int note) {
        boolean result = voteService.addVote(newVote);
        if (result == true) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("parametres invalides", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/note/{note}", consumes = "application/json")
    public ResponseEntity<String> modifyNote(@PathVariable Long id, @RequestBody Vote newVote) {
        boolean result = voteService.modifyVote(newVote, id);
        if (result == true) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("parametres invalides", HttpStatus.BAD_REQUEST);
        }
    }
}
