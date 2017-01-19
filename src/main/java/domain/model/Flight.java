package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the flight database table.
 * 
 */
@Entity
@NamedQuery(name="Flight.findAll", query="SELECT f FROM Flight f")
public class Flight implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id flight. */
	@Id
	@Column(name="id_flight")
	private Integer idFlight;

	/** The baggage number. */
	@Column(name="baggage_number")
	private Integer baggageNumber;

	/** The delay. */
	private Integer delay;

	/** The gate number. */
	@Column(name="gate_number")
	private Integer gateNumber;

	/** The time from. */
	@Column(name="time_from")
	private Time timeFrom;

	/** The time to. */
	@Column(name="time_to")
	private Time timeTo;

	/** The airport 1. */
	//bi-directional many-to-one association to Airport
	@ManyToOne
	@JoinColumn(name="id_airport_to")
	private Airport airport1;

	/** The airport 2. */
	//bi-directional many-to-one association to Airport
	@ManyToOne
	@JoinColumn(name="id_airport_from")
	private Airport airport2;

	/** The flight status. */
	//bi-directional many-to-one association to FlightStatus
	@ManyToOne
	@JoinColumn(name="id_status")
	private FlightStatus flightStatus;

	/** The plane. */
	//bi-directional many-to-one association to Plane
	@ManyToOne
	@JoinColumn(name="id_plane")
	private Plane plane;

	/** The tickets. */
	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="flight", fetch=FetchType.EAGER)
	private List<Ticket> tickets;

	/**
	 * Instantiates a new flight.
	 */
	public Flight() {
	}

	/**
	 * Gets the id flight.
	 *
	 * @return the id flight
	 */
	public Integer getIdFlight() {
		return this.idFlight;
	}

	/**
	 * Sets the id flight.
	 *
	 * @param idFlight the new id flight
	 */
	public void setIdFlight(Integer idFlight) {
		this.idFlight = idFlight;
	}

	/**
	 * Gets the baggage number.
	 *
	 * @return the baggage number
	 */
	public Integer getBaggageNumber() {
		return this.baggageNumber;
	}

	/**
	 * Sets the baggage number.
	 *
	 * @param baggageNumber the new baggage number
	 */
	public void setBaggageNumber(Integer baggageNumber) {
		this.baggageNumber = baggageNumber;
	}

	/**
	 * Gets the delay.
	 *
	 * @return the delay
	 */
	public Integer getDelay() {
		return this.delay;
	}

	/**
	 * Sets the delay.
	 *
	 * @param delay the new delay
	 */
	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	/**
	 * Gets the gate number.
	 *
	 * @return the gate number
	 */
	public Integer getGateNumber() {
		return this.gateNumber;
	}

	/**
	 * Sets the gate number.
	 *
	 * @param gateNumber the new gate number
	 */
	public void setGateNumber(Integer gateNumber) {
		this.gateNumber = gateNumber;
	}

	/**
	 * Gets the time from.
	 *
	 * @return the time from
	 */
	public Time getTimeFrom() {
		return this.timeFrom;
	}

	/**
	 * Sets the time from.
	 *
	 * @param timeFrom the new time from
	 */
	public void setTimeFrom(Time timeFrom) {
		this.timeFrom = timeFrom;
	}

	/**
	 * Gets the time to.
	 *
	 * @return the time to
	 */
	public Time getTimeTo() {
		return this.timeTo;
	}

	/**
	 * Sets the time to.
	 *
	 * @param timeTo the new time to
	 */
	public void setTimeTo(Time timeTo) {
		this.timeTo = timeTo;
	}

	/**
	 * Gets the airport 1.
	 *
	 * @return the airport 1
	 */
	public Airport getAirport1() {
		return this.airport1;
	}

	/**
	 * Sets the airport 1.
	 *
	 * @param airport1 the new airport 1
	 */
	public void setAirport1(Airport airport1) {
		this.airport1 = airport1;
	}

	/**
	 * Gets the airport 2.
	 *
	 * @return the airport 2
	 */
	public Airport getAirport2() {
		return this.airport2;
	}

	/**
	 * Sets the airport 2.
	 *
	 * @param airport2 the new airport 2
	 */
	public void setAirport2(Airport airport2) {
		this.airport2 = airport2;
	}

	/**
	 * Gets the flight status.
	 *
	 * @return the flight status
	 */
	public FlightStatus getFlightStatus() {
		return this.flightStatus;
	}

	/**
	 * Sets the flight status.
	 *
	 * @param flightStatus the new flight status
	 */
	public void setFlightStatus(FlightStatus flightStatus) {
		this.flightStatus = flightStatus;
	}

	/**
	 * Gets the plane.
	 *
	 * @return the plane
	 */
	public Plane getPlane() {
		return this.plane;
	}

	/**
	 * Sets the plane.
	 *
	 * @param plane the new plane
	 */
	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	/**
	 * Gets the tickets.
	 *
	 * @return the tickets
	 */
	public List<Ticket> getTickets() {
		return this.tickets;
	}

	/**
	 * Sets the tickets.
	 *
	 * @param tickets the new tickets
	 */
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	/**
	 * Adds the ticket.
	 *
	 * @param ticket the ticket
	 * @return the ticket
	 */
	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setFlight(this);

		return ticket;
	}

	/**
	 * Removes the ticket.
	 *
	 * @param ticket the ticket
	 * @return the ticket
	 */
	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setFlight(null);

		return ticket;
	}

}