package fr.formation.votes;

import fr.formation.artist.ArtistRepository;
import fr.formation.user.User;
import fr.formation.user.UserRepository;
import fr.formation.user.UserRoleRepository;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private VoteRepository voteRepository;
    private ArtistRepository artistRepository;


    @Autowired
    public VoteService(VoteRepository voteRepository, ArtistRepository artistRepository) {
        this.voteRepository = voteRepository;
        this.artistRepository = artistRepository;
    }

    public List<Vote> listVotes() {
        return voteRepository.findAll();
    }


    public Vote voteById(long id) {
        Optional<Vote> optUser = Optional.of(voteRepository.findById(id));
        if (optUser.isPresent()) {
            return optUser.get();
        } else {
            return null;
        }
    }


    final int noteMax = 10;
    final int noteMin = 0;

    public boolean modifyVote(Vote newVote, Long id) {


        Vote votemodif = this.voteById(id);
        if (votemodif.getNote() <= noteMax && votemodif.getNote() >= noteMin) {
            votemodif.setNote(newVote.getNote());
            voteRepository.save(votemodif);
            return true;
        } else {

            System.out.println("Vous ne pouvez pas modifier avec une note supérieure " + noteMax);
            return false;
        }
    }


    public boolean addVote(Vote newNote) {
        final int noteMax = 10;
        final int noteMin = 0;

        if (newNote.getNote() <= noteMax && newNote.getNote() >= noteMin) {

            voteRepository.save(newNote);
            return true;
        } else {
            System.out.println("Vous ne pouvez pas attribuer une note supérieure " + noteMax);
            return false;
        }
    }
}
