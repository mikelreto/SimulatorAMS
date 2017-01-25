package domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tickets database table.
 *
 */
@Entity
@Table(name = "tickets")
@NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")
public class Ticket implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id ticket. */
	@Id
	@Column(name = "id_ticket")
	private Integer idTicket;

	/** The flight. */
	//bi-directional many-to-one association to Flight
	@ManyToOne
	@JoinColumn(name = "id_flight")
	private Flight flight;

	/** The user passenger. */
	//bi-directional many-to-one association to UserPassenger
	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserPassenger userPassenger;

	/**
	 * Instantiates a new ticket.
	 */
	public Ticket() {
	}

	/**
	 * Gets the id ticket.
	 *
	 * @return the id ticket
	 */
	public Integer getIdTicket() {
		return this.idTicket;
	}

	/**
	 * Sets the id ticket.
	 *
	 * @param idTicket the new id ticket
	 */
	public void setIdTicket(Integer idTicket) {
		this.idTicket = idTicket;
	}

	/**
	 * Gets the flight.
	 *
	 * @return the flight
	 */
	public Flight getFlight() {
		return this.flight;
	}

	/**
	 * Sets the flight.
	 *
	 * @param flight the new flight
	 */
	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	/**
	 * Gets the user passenger.
	 *
	 * @return the user passenger
	 */
	public UserPassenger getUserPassenger() {
		return this.userPassenger;
	}

	/**
	 * Sets the user passenger.
	 *
	 * @param userPassenger the new user passenger
	 */
	public void setUserPassenger(UserPassenger userPassenger) {
		this.userPassenger = userPassenger;
	}

}