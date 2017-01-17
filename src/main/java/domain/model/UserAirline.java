package domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_airline database table.
 * 
 */
@Entity
@Table(name="user_airline")
@NamedQuery(name="UserAirline.findAll", query="SELECT u FROM UserAirline u")
public class UserAirline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user")
	private Integer idUser;

	private String password;

	private String username;

	//bi-directional many-to-one association to Airline
	@ManyToOne
	@JoinColumn(name="id_airline")
	private Airline airline;

	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="id_user_type")
	private UserType userType;

	public UserAirline() {
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

	public Airline getAirline() {
		return this.airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}