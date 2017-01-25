package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the flight_status database table.
 *
 */
@Entity
@Table(name = "flight_status")
@NamedQuery(name = "FlightStatus.findAll", query = "SELECT f FROM FlightStatus f")
public class FlightStatus implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id status. */
	@Id
	@Column(name = "id_status")
	private Integer idStatus;

	/** The description. */
	private String description;

	/** The flights. */
	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy = "flightStatus", fetch = FetchType.EAGER)
	private List<Flight> flights;

	/**
	 * Instantiates a new flight status.
	 */
	public FlightStatus() {
	}

	/**
	 * Gets the id status.
	 *
	 * @return the id status
	 */
	public Integer getIdStatus() {
		return this.idStatus;
	}

	/**
	 * Sets the id status.
	 *
	 * @param idStatus the new id status
	 */
	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
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
	 * Gets the flights.
	 *
	 * @return the flights
	 */
	public List<Flight> getFlights() {
		return this.flights;
	}

	/**
	 * Sets the flights.
	 *
	 * @param flights the new flights
	 */
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	/**
	 * Adds the flight.
	 *
	 * @param flight the flight
	 * @return the flight
	 */
	public Flight addFlight(Flight flight) {
		getFlights().add(flight);
		flight.setFlightStatus(this);

		return flight;
	}

	/**
	 * Removes the flight.
	 *
	 * @param flight the flight
	 * @return the flight
	 */
	public Flight removeFlight(Flight flight) {
		getFlights().remove(flight);
		flight.setFlightStatus(null);

		return flight;
	}

}