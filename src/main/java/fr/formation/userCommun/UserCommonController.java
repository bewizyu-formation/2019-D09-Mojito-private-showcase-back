package fr.formation.userCommun;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
public class UserCommonController {

    /**
     *
     * @return
     */
    @GetMapping("/")
    public String listCommonUsers(){
        return "Getting all users";
    }

    @GetMapping("/id/{id}")
    public String getCommonUserInfoById(@PathVariable long id){
        return "Getting the user with id " + id;
    }

    @GetMapping("/name/{username}")
    public String getCommonUserInfoByUsername(@PathVariable String username){
        return "Getting the user with id " + username;
    }
}
