package fr.formation.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find by username user.
	 *
	 * @param username the username
	 *
	 * @return the user
	 */
	public User findByUsername(String username);

	public List<User> findAll();

}
