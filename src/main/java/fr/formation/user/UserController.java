package fr.formation.user;

import fr.formation.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/{id}")
	public User getUser(@PathVariable long id) {

		return userService.getUserById(id);

	}

	@GetMapping("all")
	public List<User> findAll(){
		return userService.findAll();
	}




}
