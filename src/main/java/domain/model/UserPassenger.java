package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_passenger database table.
 *
 */
@Entity
@Table(name = "user_passenger")
@NamedQuery(name = "UserPassenger.findAll", query = "SELECT u FROM UserPassenger u")
public class UserPassenger implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id user. */
	@Id
	@Column(name = "id_user")
	private Integer idUser;

	/** The email. */
	private String email;

	/** The password. */
	private String password;

	/** The telephone. */
	private String telephone;

	/** The username. */
	private String username;

	/** The tickets. */
	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy = "userPassenger", fetch = FetchType.EAGER)
	private List<Ticket> tickets;

	/** The user type. */
	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name = "id_user_type")
	private UserType userType;

	/**
	 * Instantiates a new user passenger.
	 */
	public UserPassenger() {
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public Integer getIdUser() {
		return this.idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * Sets the telephone.
	 *
	 * @param telephone the new telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
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
		ticket.setUserPassenger(this);

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
		ticket.setUserPassenger(null);

		return ticket;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public UserType getUserType() {
		return this.userType;
	}

	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}