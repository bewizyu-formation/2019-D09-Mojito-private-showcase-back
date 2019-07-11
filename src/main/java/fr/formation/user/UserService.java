package fr.formation.user;

import fr.formation.event.Event;
import fr.formation.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;

	private UserRoleRepository userRoleRepository;

	private EventRepository eventRepository;
	/**
	 * Instantiates a new User service.
	 *
	 * @param userRepository     the user repository
	 * @param userRoleRepository the user role repository
	 */
	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, EventRepository eventRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.eventRepository = eventRepository;
	}

	/**
	 * transform a list of roles (as {@link String}) into a list of {@link GrantedAuthority}
	 *
	 * @param userRoles
	 *
	 * @return
	 */
	private static Collection<? extends GrantedAuthority> transformToAuthorities(List<String> userRoles) {
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user != null) {
			List<String> roles = userRoleRepository.findRoleByUserName(username);
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
					transformToAuthorities(roles));
		} else {
			throw new UsernameNotFoundException("No user exists with username: " + username);
		}

	}

	/**
	 * Add a new user with the user repository
	 *
	 * @param username the username
	 * @param password the password
	 * @param roles    the roles
	 */
	public boolean addNewUser(String username, String password, String... roles) {

		User existingUser = userRepository.findByUsername(username);
		    if(existingUser == null) {

		    User user = new User();
		    user.setUsername(username);
		    user.setPassword(password);
		    user = userRepository.save(user);

		    for (String role : roles) {

			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUserId(user.getId());

			userRoleRepository.save(userRole);
		    }
		    return true;
		}else{
			return false;
		}
	}

	public User userById(long id){
		Optional<User> optUser = userRepository.findById(id);
		if(optUser.isPresent()){
			return optUser.get();
		}else{
			return null;
		}
	}



	public int getListSize(){
		return userRepository.findAll().size();
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public boolean addEvent(long id, Event event ) {

		User user = this.userById(id);
		System.out.println("user =>");
		System.out.println(user.getId());

		try {

			Event evt = new Event();
			evt.setNbPlace(event.getNbPlace());
			evt.setAdress(event.getAdress());
			evt.setDate(event.getDate());
			evt.setHour(event.getHour());
			evt.setId(event.getId());
			evt.setOwner(user);
			System.out.println(evt);

			user.addEvent(evt);
			eventRepository.save(evt);
			userRepository.save(user);
			return true;

		}
		catch(Error err){
			System.out.println(err);

			return false;
		}

	}

	public User getUserById(long id) {

		Optional<User> user = userRepository.findById(id);
		try {

			return user.get();

		}
		catch(Error err){
			System.out.println(err);

		}

		return user.get();
	}
}
