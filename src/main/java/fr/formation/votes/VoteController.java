package fr.formation.votes;


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
    public ResponseEntity<List<Vote>> listVote() {
        try {

            List<Vote> result = voteService.listVotes();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<Vote> voteById(@PathVariable long id) {
        try {
            Vote result = voteService.voteById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Vote> registerVote(@RequestBody Vote newVote, @RequestParam int note) {
        try {
        boolean result = voteService.addVote(newVote);

            return new ResponseEntity<>(newVote,result ==true ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/note/{note}", consumes = "application/json")
    public ResponseEntity<Vote> modifyNote(@PathVariable Long id, @RequestBody Vote newVote) {
        try {
        boolean result = voteService.modifyVote(newVote, id);

            return new ResponseEntity<>(newVote,result ==true ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
