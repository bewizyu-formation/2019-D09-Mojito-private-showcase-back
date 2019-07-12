package fr.formation.votes;


import fr.formation.artist.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping(value = "/",produces = "application/json")
    public List<Vote> listVote(){

        List<Vote> listVote =  voteService.listVotes();

        return listVote;
    }

    @GetMapping(value = "/id/{id}",produces = "application/json")
    public Vote voteById(@PathVariable long id) {
        return voteService.voteById(id);
    }

    @PutMapping(value = "/", consumes = "application/json")
    public boolean registerVote(@RequestBody Vote newVote,@RequestParam int note) {
        return voteService.addVote(newVote);
    }

    @PostMapping(value = "/note/{note}", consumes = "application/json")
    public boolean modifyNote(@PathVariable Long id,@RequestBody Vote newVote) {
        return voteService.modifyVote(newVote, id);
    }
}
