package fr.formation.artist;

import fr.formation.userCommun.UserCommun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository  extends JpaRepository<Artist, Long> {

    /**
     * Find all common users.
     **
     * @return the list of users
     */
    public List<Artist> findAll();

    /**
     * find the common user of given username
     * @param username : the username of the common user to search
     * @return the common user with 'username' as username, or null
     */
    public Artist findByUsername(String username);

    /**
     * find the common user of given id
     * @param id : the id of the common user to search
     * @return an optional conataining the common user of id 'id' if it exists
     */
    public Optional<Artist> findById(Long id);
}
