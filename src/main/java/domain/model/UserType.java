package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the user_type database table.
 * 
 */
@Entity
@Table(name="user_type")
@NamedQuery(name="UserType.findAll", query="SELECT u FROM UserType u")
public class UserType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id user type. */
	@Id
	@Column(name="id_user_type")
	private Integer idUserType;

	/** The description. */
	private String description;

	/** The user airlines. */
	//bi-directional many-to-one association to UserAirline
	@OneToMany(mappedBy="userType", fetch=FetchType.EAGER)
	private List<UserAirline> userAirlines;

	/** The user airport controllers. */
	//bi-directional many-to-one association to UserAirportController
	@OneToMany(mappedBy="userType", fetch=FetchType.EAGER)
	private List<UserAirportController> userAirportControllers;

	/** The user passengers. */
	//bi-directional many-to-one association to UserPassenger
	@OneToMany(mappedBy="userType", fetch=FetchType.EAGER)
	private List<UserPassenger> userPassengers;

	/** The users. */
	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userType", fetch=FetchType.EAGER)
	private List<User> users;

	/**
	 * Instantiates a new user type.
	 */
	public UserType() {
	}

	/**
	 * Gets the id user type.
	 *
	 * @return the id user type
	 */
	public Integer getIdUserType() {
		return this.idUserType;
	}

	/**
	 * Sets the id user type.
	 *
	 * @param idUserType the new id user type
	 */
	public void setIdUserType(Integer idUserType) {
		this.idUserType = idUserType;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the user airlines.
	 *
	 * @return the user airlines
	 */
	public List<UserAirline> getUserAirlines() {
		return this.userAirlines;
	}

	/**
	 * Sets the user airlines.
	 *
	 * @param userAirlines the new user airlines
	 */
	public void setUserAirlines(List<UserAirline> userAirlines) {
		this.userAirlines = userAirlines;
	}

	/**
	 * Adds the user airline.
	 *
	 * @param userAirline the user airline
	 * @return the user airline
	 */
	public UserAirline addUserAirline(UserAirline userAirline) {
		getUserAirlines().add(userAirline);
		userAirline.setUserType(this);

		return userAirline;
	}

	/**
	 * Removes the user airline.
	 *
	 * @param userAirline the user airline
	 * @return the user airline
	 */
	public UserAirline removeUserAirline(UserAirline userAirline) {
		getUserAirlines().remove(userAirline);
		userAirline.setUserType(null);

		return userAirline;
	}

	/**
	 * Gets the user airport controllers.
	 *
	 * @return the user airport controllers
	 */
	public List<UserAirportController> getUserAirportControllers() {
		return this.userAirportControllers;
	}

	/**
	 * Sets the user airport controllers.
	 *
	 * @param userAirportControllers the new user airport controllers
	 */
	public void setUserAirportControllers(List<UserAirportController> userAirportControllers) {
		this.userAirportControllers = userAirportControllers;
	}

	/**
	 * Adds the user airport controller.
	 *
	 * @param userAirportController the user airport controller
	 * @return the user airport controller
	 */
	public UserAirportController addUserAirportController(UserAirportController userAirportController) {
		getUserAirportControllers().add(userAirportController);
		userAirportController.setUserType(this);

		return userAirportController;
	}

	/**
	 * Removes the user airport controller.
	 *
	 * @param userAirportController the user airport controller
	 * @return the user airport controller
	 */
	public UserAirportController removeUserAirportController(UserAirportController userAirportController) {
		getUserAirportControllers().remove(userAirportController);
		userAirportController.setUserType(null);

		return userAirportController;
	}

	/**
	 * Gets the user passengers.
	 *
	 * @return the user passengers
	 */
	public List<UserPassenger> getUserPassengers() {
		return this.userPassengers;
	}

	/**
	 * Sets the user passengers.
	 *
	 * @param userPassengers the new user passengers
	 */
	public void setUserPassengers(List<UserPassenger> userPassengers) {
		this.userPassengers = userPassengers;
	}

	/**
	 * Adds the user passenger.
	 *
	 * @param userPassenger the user passenger
	 * @return the user passenger
	 */
	public UserPassenger addUserPassenger(UserPassenger userPassenger) {
		getUserPassengers().add(userPassenger);
		userPassenger.setUserType(this);

		return userPassenger;
	}

	/**
	 * Removes the user passenger.
	 *
	 * @param userPassenger the user passenger
	 * @return the user passenger
	 */
	public UserPassenger removeUserPassenger(UserPassenger userPassenger) {
		getUserPassengers().remove(userPassenger);
		userPassenger.setUserType(null);

		return userPassenger;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers() {
		return this.users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User addUser(User user) {
		getUsers().add(user);
		user.setUserType(this);

		return user;
	}

	/**
	 * Removes the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUserType(null);

		return user;
	}

}