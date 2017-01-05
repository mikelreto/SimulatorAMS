package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the airline database table.
 * 
 */
@Entity
@NamedQuery(name="Airline.findAll", query="SELECT a FROM Airline a")
public class Airline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_airline")
	private Integer idAirline;

	private String name;

	//bi-directional many-to-one association to Plane
	@OneToMany(mappedBy="airline")
	private List<Plane> planes;

	//bi-directional many-to-one association to UserAirline
	@OneToMany(mappedBy="airline")
	private List<UserAirline> userAirlines;

	public Airline() {
	}

	public Integer getIdAirline() {
		return this.idAirline;
	}

	public void setIdAirline(Integer idAirline) {
		this.idAirline = idAirline;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Plane> getPlanes() {
		return this.planes;
	}

	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	public Plane addPlane(Plane plane) {
		getPlanes().add(plane);
		plane.setAirline(this);

		return plane;
	}

	public Plane removePlane(Plane plane) {
		getPlanes().remove(plane);
		plane.setAirline(null);

		return plane;
	}

	public List<UserAirline> getUserAirlines() {
		return this.userAirlines;
	}

	public void setUserAirlines(List<UserAirline> userAirlines) {
		this.userAirlines = userAirlines;
	}

	public UserAirline addUserAirline(UserAirline userAirline) {
		getUserAirlines().add(userAirline);
		userAirline.setAirline(this);

		return userAirline;
	}

	public UserAirline removeUserAirline(UserAirline userAirline) {
		getUserAirlines().remove(userAirline);
		userAirline.setAirline(null);

		return userAirline;
	}

}