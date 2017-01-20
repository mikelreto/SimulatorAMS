package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the airport database table.
 *
 */
@Entity
@NamedQuery(name = "Airport.findAll", query = "SELECT a FROM Airport a")
public class Airport implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id airport. */
	@Id
	@Column(name = "id_airport")
	private Integer idAirport;

	/** The name. */
	private String name;

	/** The num terminals. */
	@Column(name = "num_terminals")
	private Integer numTerminals;

	/** The pos X. */
	@Column(name = "pos_x")
	private double posX;

	/** The pos Y. */
	@Column(name = "pos_y")
	private double posY;

	/** The flights 1. */
	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy = "airport1", fetch = FetchType.EAGER)
	private List<Flight> flights1;

	/** The flights 2. */
	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy = "airport2", fetch = FetchType.EAGER)
	private List<Flight> flights2;

	/** The lanes. */
	//bi-directional many-to-one association to Lane
	@OneToMany(mappedBy = "airport", fetch = FetchType.EAGER)
	private List<Lane> lanes;

	/** The user airport controllers. */
	//bi-directional many-to-one association to UserAirportController
	@OneToMany(mappedBy = "airport", fetch = FetchType.EAGER)
	private List<UserAirportController> userAirportControllers;

	/**
	 * Instantiates a new airport.
	 */
	public Airport() {
	}

	/**
	 * Gets the id airport.
	 *
	 * @return the id airport
	 */
	public Integer getIdAirport() {
		return this.idAirport;
	}

	/**
	 * Sets the id airport.
	 *
	 * @param idAirport the new id airport
	 */
	public void setIdAirport(Integer idAirport) {
		this.idAirport = idAirport;
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
	 * Gets the num terminals.
	 *
	 * @return the num terminals
	 */
	public Integer getNumTerminals() {
		return this.numTerminals;
	}

	/**
	 * Sets the num terminals.
	 *
	 * @param numTerminals the new num terminals
	 */
	public void setNumTerminals(Integer numTerminals) {
		this.numTerminals = numTerminals;
	}

	/**
	 * Gets the pos X.
	 *
	 * @return the pos X
	 */
	public double getPosX() {
		return this.posX;
	}

	/**
	 * Sets the pos X.
	 *
	 * @param posX the new pos X
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}

	/**
	 * Gets the pos Y.
	 *
	 * @return the pos Y
	 */
	public double getPosY() {
		return this.posY;
	}

	/**
	 * Sets the pos Y.
	 *
	 * @param posY the new pos Y
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}

	/**
	 * Gets the flights 1.
	 *
	 * @return the flights 1
	 */
	public List<Flight> getFlights1() {
		return this.flights1;
	}

	/**
	 * Sets the flights 1.
	 *
	 * @param flights1 the new flights 1
	 */
	public void setFlights1(List<Flight> flights1) {
		this.flights1 = flights1;
	}

	/**
	 * Adds the flights 1.
	 *
	 * @param flights1 the flights 1
	 * @return the flight
	 */
	public Flight addFlights1(Flight flights1) {
		getFlights1().add(flights1);
		flights1.setAirport1(this);

		return flights1;
	}

	/**
	 * Removes the flights 1.
	 *
	 * @param flights1 the flights 1
	 * @return the flight
	 */
	public Flight removeFlights1(Flight flights1) {
		getFlights1().remove(flights1);
		flights1.setAirport1(null);

		return flights1;
	}

	/**
	 * Gets the flights 2.
	 *
	 * @return the flights 2
	 */
	public List<Flight> getFlights2() {
		return this.flights2;
	}

	/**
	 * Sets the flights 2.
	 *
	 * @param flights2 the new flights 2
	 */
	public void setFlights2(List<Flight> flights2) {
		this.flights2 = flights2;
	}

	/**
	 * Adds the flights 2.
	 *
	 * @param flights2 the flights 2
	 * @return the flight
	 */
	public Flight addFlights2(Flight flights2) {
		getFlights2().add(flights2);
		flights2.setAirport2(this);

		return flights2;
	}

	/**
	 * Removes the flights 2.
	 *
	 * @param flights2 the flights 2
	 * @return the flight
	 */
	public Flight removeFlights2(Flight flights2) {
		getFlights2().remove(flights2);
		flights2.setAirport2(null);

		return flights2;
	}

	/**
	 * Gets the lanes.
	 *
	 * @return the lanes
	 */
	public List<Lane> getLanes() {
		return this.lanes;
	}

	/**
	 * Sets the lanes.
	 *
	 * @param lanes the new lanes
	 */
	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}

	/**
	 * Adds the lane.
	 *
	 * @param lane the lane
	 * @return the lane
	 */
	public Lane addLane(Lane lane) {
		getLanes().add(lane);
		lane.setAirport(this);

		return lane;
	}

	/**
	 * Removes the lane.
	 *
	 * @param lane the lane
	 * @return the lane
	 */
	public Lane removeLane(Lane lane) {
		getLanes().remove(lane);
		lane.setAirport(null);

		return lane;
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
	public void setUserAirportControllers(
			List<UserAirportController> userAirportControllers) {
		this.userAirportControllers = userAirportControllers;
	}

	/**
	 * Adds the user airport controller.
	 *
	 * @param userAirportController the user airport controller
	 * @return the user airport controller
	 */
	public UserAirportController addUserAirportController(
			UserAirportController userAirportController) {
		getUserAirportControllers().add(userAirportController);
		userAirportController.setAirport(this);

		return userAirportController;
	}

	/**
	 * Removes the user airport controller.
	 *
	 * @param userAirportController the user airport controller
	 * @return the user airport controller
	 */
	public UserAirportController removeUserAirportController(
			UserAirportController userAirportController) {
		getUserAirportControllers().remove(userAirportController);
		userAirportController.setAirport(null);

		return userAirportController;
	}

}