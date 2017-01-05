package domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_airport_controller database table.
 * 
 */
@Entity
@Table(name="user_airport_controller")
@NamedQuery(name="UserAirportController.findAll", query="SELECT u FROM UserAirportController u")
public class UserAirportController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user")
	private Integer idUser;

	private String password;

	private String username;

	//bi-directional many-to-one association to Airport
	@ManyToOne
	@JoinColumn(name="id_airport")
	private Airport airport;

	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="id_user_type")
	private UserType userType;

	public UserAirportController() {
	}

	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Airport getAirport() {
		return this.airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}