package domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tickets database table.
 * 
 */
@Entity
@Table(name="tickets")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ticket")
	private Integer idTicket;

	//bi-directional many-to-one association to Flight
	@ManyToOne
	@JoinColumn(name="id_flight")
	private Flight flight;

	//bi-directional many-to-one association to UserPassenger
	@ManyToOne
	@JoinColumn(name="id_user")
	private UserPassenger userPassenger;

	public Ticket() {
	}

	public Integer getIdTicket() {
		return this.idTicket;
	}

	public void setIdTicket(Integer idTicket) {
		this.idTicket = idTicket;
	}

	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public UserPassenger getUserPassenger() {
		return this.userPassenger;
	}

	public void setUserPassenger(UserPassenger userPassenger) {
		this.userPassenger = userPassenger;
	}

}