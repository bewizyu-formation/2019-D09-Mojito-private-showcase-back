package fr.formation.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> signup(@RequestParam String username, @RequestParam String password,
										 @RequestParam String... roles) {

		boolean result = userService.addNewUser(username, password, roles);
		if (result == true) {
			return new ResponseEntity<>(username, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Nouvel utilisateur non enregistré", HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("/")
	public ResponseEntity<String> getSize() {
		Integer result = userService.getListSize();
		try{
			return new ResponseEntity<>(result.toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Taille non disponible", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("all")
	public ResponseEntity<String> findAll() {
		ObjectMapper mapper = new ObjectMapper();
		List<User> result = userService.findAll();

		try {
			return new ResponseEntity<>(mapper.writeValueAsString(result), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("parametres invalides", HttpStatus.BAD_REQUEST);
		}
	}
}