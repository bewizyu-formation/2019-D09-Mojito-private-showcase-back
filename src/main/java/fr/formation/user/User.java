package fr.formation.user;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.formation.event.Event;

import javax.persistence.*;
import java.util.Set;

/**
 * The type User.
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username")
	private String username;


	@Column(name = "password")
	@JsonIgnore
	private String password;

	@OneToMany(mappedBy = "owner")
	private Set<Event> events;

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * Sets username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Event> getEvents() {
		return events;
	}

	public void addEvent(Event event) {
		this.events.add(event);
	}
}
