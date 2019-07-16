package fr.formation.votes;


import fr.formation.artist.Artist;
import fr.formation.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface VoteRepository extends JpaRepository<Vote, Integer> {


    public List<Vote> findAll();

    public Vote findById(Long id);
}
