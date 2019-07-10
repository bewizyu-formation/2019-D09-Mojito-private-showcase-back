package fr.formation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Signup.
	 *
	 * @param username the username
	 * @param password the password
	 * @param roles    the roles
	 */
	@PutMapping("/")
	public boolean signup(@RequestParam String username, @RequestParam String password,
										 @RequestParam String... roles) {

		return userService.addNewUser(username, password, roles);

	}

	@GetMapping("/")
	public int getSize() {

		return userService.getListSize();

	}

	@GetMapping("all")
	public List<User> findAll(){
		return userService.findAll();
	}


}
