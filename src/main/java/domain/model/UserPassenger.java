package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_passenger database table.
 * 
 */
@Entity
@Table(name="user_passenger")
@NamedQuery(name="UserPassenger.findAll", query="SELECT u FROM UserPassenger u")
public class UserPassenger implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user")
	private Integer idUser;

	private String email;

	private String password;

	private String telephone;

	private String username;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="userPassenger")
	private List<Ticket> tickets;

	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="id_user_type")
	private UserType userType;

	public UserPassenger() {
	}

	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setUserPassenger(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setUserPassenger(null);

		return ticket;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}