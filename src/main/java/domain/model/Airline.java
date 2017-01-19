package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the airline database table.
 * 
 */
@Entity
@NamedQuery(name="Airline.findAll", query="SELECT a FROM Airline a")
public class Airline implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id airline. */
	@Id
	@Column(name="id_airline")
	private Integer idAirline;

	/** The name. */
	private String name;

	/** The planes. */
	//bi-directional many-to-one association to Plane
	@OneToMany(mappedBy="airline", fetch=FetchType.EAGER)
	private List<Plane> planes;

	/** The user airlines. */
	//bi-directional many-to-one association to UserAirline
	@OneToMany(mappedBy="airline", fetch=FetchType.EAGER)
	private List<UserAirline> userAirlines;

	/**
	 * Instantiates a new airline.
	 */
	public Airline() {
	}

	/**
	 * Gets the id airline.
	 *
	 * @return the id airline
	 */
	public Integer getIdAirline() {
		return this.idAirline;
	}

	/**
	 * Sets the id airline.
	 *
	 * @param idAirline the new id airline
	 */
	public void setIdAirline(Integer idAirline) {
		this.idAirline = idAirline;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the planes.
	 *
	 * @return the planes
	 */
	public List<Plane> getPlanes() {
		return this.planes;
	}

	/**
	 * Sets the planes.
	 *
	 * @param planes the new planes
	 */
	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	/**
	 * Adds the plane.
	 *
	 * @param plane the plane
	 * @return the plane
	 */
	public Plane addPlane(Plane plane) {
		getPlanes().add(plane);
		plane.setAirline(this);

		return plane;
	}

	/**
	 * Removes the plane.
	 *
	 * @param plane the plane
	 * @return the plane
	 */
	public Plane removePlane(Plane plane) {
		getPlanes().remove(plane);
		plane.setAirline(null);

		return plane;
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
		userAirline.setAirline(this);

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
		userAirline.setAirline(null);

		return userAirline;
	}

}