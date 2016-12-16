package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the airport database table.
 * 
 */
@Entity
@NamedQuery(name="Airport.findAll", query="SELECT a FROM Airport a")
public class Airport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_airport")
	private Integer idAirport;

	private String name;

	@Column(name="num_terminals")
	private Integer numTerminals;

	@Column(name="pos_x")
	private double posX;

	@Column(name="pos_y")
	private double posY;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="airport1")
	private List<Flight> flights1;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="airport2")
	private List<Flight> flights2;

	//bi-directional many-to-one association to Lane
	@OneToMany(mappedBy="airport")
	private List<Lane> lanes;

	//bi-directional many-to-one association to UserAirportController
	@OneToMany(mappedBy="airport")
	private List<UserAirportController> userAirportControllers;

	public Airport() {
	}

	public Integer getIdAirport() {
		return this.idAirport;
	}

	public void setIdAirport(Integer idAirport) {
		this.idAirport = idAirport;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumTerminals() {
		return this.numTerminals;
	}

	public void setNumTerminals(Integer numTerminals) {
		this.numTerminals = numTerminals;
	}

	public double getPosX() {
		return this.posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return this.posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public List<Flight> getFlights1() {
		return this.flights1;
	}

	public void setFlights1(List<Flight> flights1) {
		this.flights1 = flights1;
	}

	public Flight addFlights1(Flight flights1) {
		getFlights1().add(flights1);
		flights1.setAirport1(this);

		return flights1;
	}

	public Flight removeFlights1(Flight flights1) {
		getFlights1().remove(flights1);
		flights1.setAirport1(null);

		return flights1;
	}

	public List<Flight> getFlights2() {
		return this.flights2;
	}

	public void setFlights2(List<Flight> flights2) {
		this.flights2 = flights2;
	}

	public Flight addFlights2(Flight flights2) {
		getFlights2().add(flights2);
		flights2.setAirport2(this);

		return flights2;
	}

	public Flight removeFlights2(Flight flights2) {
		getFlights2().remove(flights2);
		flights2.setAirport2(null);

		return flights2;
	}

	public List<Lane> getLanes() {
		return this.lanes;
	}

	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}

	public Lane addLane(Lane lane) {
		getLanes().add(lane);
		lane.setAirport(this);

		return lane;
	}

	public Lane removeLane(Lane lane) {
		getLanes().remove(lane);
		lane.setAirport(null);

		return lane;
	}

	public List<UserAirportController> getUserAirportControllers() {
		return this.userAirportControllers;
	}

	public void setUserAirportControllers(List<UserAirportController> userAirportControllers) {
		this.userAirportControllers = userAirportControllers;
	}

	public UserAirportController addUserAirportController(UserAirportController userAirportController) {
		getUserAirportControllers().add(userAirportController);
		userAirportController.setAirport(this);

		return userAirportController;
	}

	public UserAirportController removeUserAirportController(UserAirportController userAirportController) {
		getUserAirportControllers().remove(userAirportController);
		userAirportController.setAirport(null);

		return userAirportController;
	}

}