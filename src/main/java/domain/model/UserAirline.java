package domain.model;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the user_airline database table.
 * 
 */
@Entity
@Table(name="user_airline")
@NamedQuery(name="UserAirline.findAll", query="SELECT u FROM UserAirline u")
public class UserAirline implements Serializable {
	
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

	/** The airline. */
	//bi-directional many-to-one association to Airline
	@ManyToOne
	@JoinColumn(name="id_airline")
	private Airline airline;

	/** The user type. */
	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="id_user_type")
	private UserType userType;

	/**
	 * Instantiates a new user airline.
	 */
	public UserAirline() {
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
	 * Gets the airline.
	 *
	 * @return the airline
	 */
	public Airline getAirline() {
		return this.airline;
	}

	/**
	 * Sets the airline.
	 *
	 * @param airline the new airline
	 */
	public void setAirline(Airline airline) {
		this.airline = airline;
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