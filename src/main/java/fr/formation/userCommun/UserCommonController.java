package fr.formation.userCommun;


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
    public ResponseEntity<List<UserCommun>> listCommonUsers() {
        try {
            List<UserCommun> result = userCommunService.listUserCommun();
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get common user with given id
     *
     * @param id : the id of the user to look for
     * @return the user with id 'id'
     */
    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<UserCommun> getCommonUserInfoById(@PathVariable long id) {
        try {
            UserCommun result = userCommunService.userCommunById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get common user with given username
     *
     * @param username : the username of the user to look for
     * @return the user named 'username"
     */
    @GetMapping(value = "/name/{username}", produces = "application/json")
    public ResponseEntity<UserCommun> getCommonUserInfoByUsername(@PathVariable String username) {
        try {
            UserCommun result = userCommunService.userCommunByUsername(username);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * add a new user with given json class
     *
     * @param newUser : the user given by request body
     * @return true if the user has been added, false otherwise
     */
    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity registerCommonUser(@RequestBody UserCommun newUser, @RequestParam String password, HttpServletResponse response) {
        try {
            newUser.setPassword(password);
            int code = userCommunService.addUserCommun(newUser);
            if (code == 1) {
                return new ResponseEntity<>(newUser, HttpStatus.OK);
            } else if (code == 0 || code == 2) {
                return new ResponseEntity<>("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
            } else if (code == 3) {
                return new ResponseEntity<>("Ce nom est déjà pris", HttpStatus.BAD_REQUEST);
            } else if (code == 4) {
                return new ResponseEntity<>("Email incorrect", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * modify the user with given id
     *
     * @param id      : the id of the user to modify
     * @param newUser : the new data
     * @return true if the user has been modified, false otherwise
     */

    @PostMapping(value = "/id/{id}", consumes = "application/json")
    public ResponseEntity<UserCommun> modifyCommonUser(@PathVariable long id, @RequestBody UserCommun newUser) {
        try {
            boolean result = userCommunService.modifyUserCommun(id, newUser);
            return new ResponseEntity<>(newUser, result == true ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}