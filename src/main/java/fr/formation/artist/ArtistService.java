package fr.formation.artist;

import fr.formation.user.User;
import fr.formation.user.UserRepository;
import fr.formation.user.UserRole;
import fr.formation.user.UserRoleRepository;
import fr.formation.util.Checks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static fr.formation.security.SecurityConstants.ROLE_USER_SUFFIX;

@Service
public class ArtistService {

    private UserRepository userRepository;
    private ArtistRepository artistRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Instantiates a new Artist service.
     *
     * @param userRepository     the artist repository
     * @param artistRepository the common artist repository
     * @param userRoleRepository the role repository
     */
    @Autowired
    public ArtistService(
            UserRepository userRepository,
            ArtistRepository artistRepository,
            UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * list all common artist in database
     *
     * @return the list of all common user
     */
    public List<Artist> listArtists(){
        return artistRepository.findAll();
    }

    /**
     * check if the username is already in the database
     * @param userName : username to check
     * @return true if the name exists, else false
     */
    public boolean usernameExists(String userName){
        return userRepository.findByUsername(userName) != null;
    }

    /**
     *  check if a user (any kind) exists by id
     * @param id : the id of the user to check
     * @return true if the user exists, false otherwise
     */
    public boolean userExistsById(long id){
        return userRepository.findById(id).isPresent();
    }

    /**
     * check if the user has an username that exists in the database
     * @param userToCheck : the user to check
     * @return true if the user name already exists, else false
     */
    public boolean userWithIdenticalNameExists(User userToCheck){
        return this.usernameExists(userToCheck.getUsername());
    }


    /**
     * get the artist by id
     * @param id : id of the artist to look for
     * @return artist with given 'id'
     */
    public Artist artistById(long id){
        Optional<Artist> optUser = artistRepository.findById(id);
        if(optUser.isPresent()){
            return optUser.get();
        }else{
            return null;
        }
    }

    /**
     * get the artist of given username
     * @param username : name of the artist to search
     * @return the artist with given name
     */
    public Artist artistByUsername(String username){
        Artist artist = artistRepository.findByUsername(username);
        return artist;
    }

    /**
     *  add a artist to the database
     *
     * @param artistToAdd : the artist to add
     * @return true if the artist has been added, else false
     */
    public int addArtist(Artist artistToAdd){

        int code;
        if(this.userWithIdenticalNameExists(artistToAdd)){
            code = 3;
        } else if ( !Checks.checkPassword(artistToAdd.getPassword())) {
            code = 2;
        } else if ( !Checks.checkEmail(artistToAdd.getEmail())) {
            code = 4;
        } else if (!Checks.checkWithPassword(artistToAdd)){
            code = 0;
        } else {
            try {
                artistToAdd.setPassword(passwordEncoder.encode(artistToAdd.getPassword()));
                artistRepository.save(artistToAdd);
                // Artist artist2 = artistRepository.findByUsername(artistToAdd.getUsername());

                UserRole userRole = new UserRole();
                userRole.setRole(ROLE_USER_SUFFIX);
                userRole.setUserId(artistToAdd.getId());
                userRoleRepository.save(userRole);

                code = 1;
            } catch (Exception e) {
                code = 0;
            }
        }
        return code;
    }

    /**
     * modify a given artist with new data
     * @param id : the id of the artist to change
     * @param newArtist : the new data for the user
     * @return true if the artist has been modified, false otherwise
     */
    public boolean modifyArtist(long id,Artist newArtist){
        Artist artistToUpdate = this.artistById(id);
        // Check if the id exists
        if(artistToUpdate == null){
            return false;
        }else{
            if(!Checks.check(newArtist)){
                return false;
            }

            // Save the new artist
            artistRepository.save(newArtist);

            return true;
        }
    }
}
