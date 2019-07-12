package fr.formation.userCommun;


import fr.formation.user.User;
import fr.formation.user.UserService;
import fr.formation.votes.Vote;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/common")
public class UserCommonController {

    @Autowired
    private UserCommunService userCommunService;


    /**
     * list all common users
     *
     * @return list of the common users
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Object> listCommonUsers() {

        List<UserCommun> result = userCommunService.listUserCommun();

        if (result == null) {
            return new ResponseEntity<>("parametres invalides", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    /**
     * get common user with given id
     *
     * @param id : the id of the user to look for
     * @return the user with id 'id'
     */
    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<Object> getCommonUserInfoById(@PathVariable long id) {
        UserCommun result = userCommunService.userCommunById(id);
        if (result == null) {
            return new ResponseEntity<>("parametres invalides", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    /**
     * get common user with given username
     *
     * @param username : the username of the user to look for
     * @return the user named 'username"
     */
    @GetMapping(value = "/name/{username}", produces = "application/json")
    public ResponseEntity<String> getCommonUserInfoByUsername(@PathVariable String username) {
        UserCommun result = userCommunService.userCommunByUsername(username);
        if (result == null) {
            return new ResponseEntity<>("parametres invalides", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
    }

    /**
     * add a new user with given json class
     *
     * @param newUser : the user given by request body
     * @return true if the user has been added, false otherwise
     */
    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<String> registerCommonUser(@RequestBody UserCommun newUser, @RequestParam String password, HttpServletResponse response) {
        newUser.setPassword(password);
        String message;
        int code = userCommunService.addUserCommun(newUser);
        message = code + "";
        if (code == 1) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } else if (code == 2) {
            return new ResponseEntity<>("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
        } else if (code == 3) {
            return new ResponseEntity<>("Ce nom est déjà pris", HttpStatus.BAD_REQUEST);
        } else if (code == 0) {
            return new ResponseEntity<>("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
        } else if (code == 4) {
            return new ResponseEntity<>("Email incorrect", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    /**
     * modify the user with given id
     *
     * @param id      : the id of the user to modify
     * @param newUser : the new data
     * @return true if the user has been modified, false otherwise
     */

    @PostMapping(value = "/id/{id}", consumes = "application/json")
    public ResponseEntity<String> modifyCommonUser(@PathVariable long id, @RequestBody UserCommun newUser) {
        boolean result = userCommunService.modifyUserCommun(id, newUser);
        if (result == true) {
            return new ResponseEntity<>("Modification non prise en compte", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
    }
}