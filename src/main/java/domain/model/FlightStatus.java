package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the flight_status database table.
 * 
 */
@Entity
@Table(name="flight_status")
@NamedQuery(name="FlightStatus.findAll", query="SELECT f FROM FlightStatus f")
public class FlightStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_status")
	private Integer idStatus;

	private String description;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="flightStatus", fetch=FetchType.EAGER)
	private List<Flight> flights;

	public FlightStatus() {
	}

	public Integer getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Flight addFlight(Flight flight) {
		getFlights().add(flight);
		flight.setFlightStatus(this);

		return flight;
	}

	public Flight removeFlight(Flight flight) {
		getFlights().remove(flight);
		flight.setFlightStatus(null);

		return flight;
	}

}