package domain.model;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the user_airport_controller database table.
 * 
 */
@Entity
@Table(name="user_airport_controller")
@NamedQuery(name="UserAirportController.findAll", query="SELECT u FROM UserAirportController u")
public class UserAirportController implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id user. */
	@Id
	@Column(name="id_user")
	private Integer idUser;

	/** The password. */
	private String password;

	/** The username. */
	private String username;

	/** The airport. */
	//bi-directional many-to-one association to Airport
	@ManyToOne
	@JoinColumn(name="id_airport")
	private Airport airport;

	/** The user type. */
	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="id_user_type")
	private UserType userType;

	/**
	 * Instantiates a new user airport controller.
	 */
	public UserAirportController() {
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public Integer getIdUser() {
		return this.idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the airport.
	 *
	 * @return the airport
	 */
	public Airport getAirport() {
		return this.airport;
	}

	/**
	 * Sets the airport.
	 *
	 * @param airport the new airport
	 */
	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public UserType getUserType() {
		return this.userType;
	}

	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}