package fr.formation.userCommun;


import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/common")
public class UserCommonController  {

    @Autowired
    private UserCommunService userCommunService;


    /**
     * list all common users
     * @return list of the common users
     */
    @GetMapping(value = "/",produces = "application/json")
    public List<UserCommun> listCommonUsers(){

        List<UserCommun> listUser =  userCommunService.listUserCommun();

        return listUser;
    }

    /**
     *  get common user with given id
     * @param id : the id of the user to look for
     * @return the user with id 'id'
     */
    @GetMapping(value = "/id/{id}",produces = "application/json")
    public UserCommun getCommonUserInfoById(@PathVariable long id){

        return userCommunService.userCommunById(id);
    }

    /**
     *  get common user with given username
     *
     * @param username : the username of the user to look for
     * @return the user named 'username"
     */
    @GetMapping(value="/name/{username}",produces = "application/json")
    public UserCommun getCommonUserInfoByUsername(@PathVariable String username){
        return userCommunService.userCommunByUsername(username);
    }

    /**
     * add a new user with given json class
     * @param newUser : the user given by request body
     * @return true if the user has been added, false otherwise
     */
    @PutMapping(value = "/", consumes = "application/json")

    public String registerCommonUser(@RequestBody UserCommun newUser,@RequestParam String password, HttpServletResponse response) {
        newUser.setPassword(password);
        String message;
        int code = userCommunService.addUserCommun(newUser);
        message = code+"";
        if(code == 1) {
            response.setStatus(Response.SC_OK);
        } else {
            response.setStatus(Response.SC_BAD_REQUEST);
        }
        return message;
    }

    /**
     *  modify the user with given id
     * @param id : the id of the user to modify
     * @param newUser : the new data
     * @return true if the user has been modified, false otherwise
     */
    @PostMapping(value = "/id/{id}", consumes = "application/json")
    public boolean modifyCommonUser(@PathVariable long id,@RequestBody UserCommun newUser) {
        return userCommunService.modifyUserCommun(id,newUser);
    }


}
