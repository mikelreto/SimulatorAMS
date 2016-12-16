package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_type database table.
 * 
 */
@Entity
@Table(name="user_type")
@NamedQuery(name="UserType.findAll", query="SELECT u FROM UserType u")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user_type")
	private Integer idUserType;

	private String description;

	//bi-directional many-to-one association to UserAirline
	@OneToMany(mappedBy="userType")
	private List<UserAirline> userAirlines;

	//bi-directional many-to-one association to UserAirportController
	@OneToMany(mappedBy="userType")
	private List<UserAirportController> userAirportControllers;

	//bi-directional many-to-one association to UserPassenger
	@OneToMany(mappedBy="userType")
	private List<UserPassenger> userPassengers;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userType")
	private List<User> users;

	public UserType() {
	}

	public Integer getIdUserType() {
		return this.idUserType;
	}

	public void setIdUserType(Integer idUserType) {
		this.idUserType = idUserType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserAirline> getUserAirlines() {
		return this.userAirlines;
	}

	public void setUserAirlines(List<UserAirline> userAirlines) {
		this.userAirlines = userAirlines;
	}

	public UserAirline addUserAirline(UserAirline userAirline) {
		getUserAirlines().add(userAirline);
		userAirline.setUserType(this);

		return userAirline;
	}

	public UserAirline removeUserAirline(UserAirline userAirline) {
		getUserAirlines().remove(userAirline);
		userAirline.setUserType(null);

		return userAirline;
	}

	public List<UserAirportController> getUserAirportControllers() {
		return this.userAirportControllers;
	}

	public void setUserAirportControllers(List<UserAirportController> userAirportControllers) {
		this.userAirportControllers = userAirportControllers;
	}

	public UserAirportController addUserAirportController(UserAirportController userAirportController) {
		getUserAirportControllers().add(userAirportController);
		userAirportController.setUserType(this);

		return userAirportController;
	}

	public UserAirportController removeUserAirportController(UserAirportController userAirportController) {
		getUserAirportControllers().remove(userAirportController);
		userAirportController.setUserType(null);

		return userAirportController;
	}

	public List<UserPassenger> getUserPassengers() {
		return this.userPassengers;
	}

	public void setUserPassengers(List<UserPassenger> userPassengers) {
		this.userPassengers = userPassengers;
	}

	public UserPassenger addUserPassenger(UserPassenger userPassenger) {
		getUserPassengers().add(userPassenger);
		userPassenger.setUserType(this);

		return userPassenger;
	}

	public UserPassenger removeUserPassenger(UserPassenger userPassenger) {
		getUserPassengers().remove(userPassenger);
		userPassenger.setUserType(null);

		return userPassenger;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUserType(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUserType(null);

		return user;
	}

}