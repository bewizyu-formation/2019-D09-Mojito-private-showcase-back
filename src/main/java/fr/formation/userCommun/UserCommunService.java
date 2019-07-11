package fr.formation.userCommun;

import fr.formation.user.User;
import fr.formation.user.UserRepository;
import fr.formation.user.UserRole;
import fr.formation.user.UserRoleRepository;
import fr.formation.util.Checks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static fr.formation.security.SecurityConstants.ROLE_USER;
import static fr.formation.security.SecurityConstants.ROLE_USER_SUFFIX;

@Service
public class UserCommunService {

    private UserRepository userRepository;
    private UserCommunRepository userCommunRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository     the user repository
     * @param userCommunRepository the common user repository
     * @param userRoleRepository the role repository
     */
    @Autowired
    public UserCommunService(
            UserRepository userRepository,
            UserCommunRepository userCommunRepository,
            UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userCommunRepository = userCommunRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * list all common user in database
     *
     * @return the list of all common user
     */
    public List<UserCommun> listUserCommun(){
        return userCommunRepository.findAll();
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
     * get the user by id
     * @param id : id of the user to look for
     * @return user with given 'id'
     */
    public UserCommun userCommunById(long id){
        Optional<UserCommun> optUser = userCommunRepository.findById(id);
        if(optUser.isPresent()){
            return optUser.get();
        }else{
            return null;
        }
    }

    /**
     * get the common user of given username
     * @param username : name of the user to search
     * @return the common user with given name
     */
    public UserCommun userCommunByUsername(String username){
        UserCommun user = userCommunRepository.findByUsername(username);
        return user;
    }

    /**
     *  add a user to the database
     *
     * @param userToAdd : the user to add
     * @return 1 if used added, 2 if password format incorrect, 3 if user already exist, 4 if format email incorrect, 0 if problem while insertion
     */
    public int addUserCommun(UserCommun userToAdd){
        int code;

        if (this.userWithIdenticalNameExists(userToAdd)) {
            code = 3;
        } else if ( !Checks.checkPassword(userToAdd.getPassword())) {
            code = 2;
        } else if ( !Checks.checkEmail(userToAdd.getEmail())) {
            code = 4;
        } else if (!Checks.checkWithPassword(userToAdd)){
            code = 0;
        } else {
            try {
                userToAdd.setPassword(passwordEncoder.encode(userToAdd.getPassword()));
                userCommunRepository.save(userToAdd);

                UserRole userRole = new UserRole();
                userRole.setRole(ROLE_USER_SUFFIX);
                userRole.setUserId(userToAdd.getId());
                userRoleRepository.save(userRole);

                code = 1;
            } catch( Exception e) {
                code = 0;
            }



        }
        return code;
    }

    /**
     * modify a given user with new data
     * @param id : the id of the user to change
     * @param newUser : the new data for the user
     * @return true if the user has been modified, false otherwise
     */
    public boolean modifyUserCommun(long id,UserCommun newUser){
        UserCommun userToUpdate = this.userCommunById(id);
        if(userToUpdate == null){
            return false;
        }else{
            if(!Checks.check(newUser)){
                return false;
            }
            userToUpdate.setAdress(newUser.getAdress());
            userToUpdate.setCity(newUser.getCity());
            userToUpdate.setEmail(newUser.getEmail());

            userCommunRepository.save(userToUpdate);

            return true;
        }
    }

}
