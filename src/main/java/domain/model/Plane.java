package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the plane database table.
 * 
 */
@Entity
@NamedQuery(name="Plane.findAll", query="SELECT p FROM Plane p")
public class Plane implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_plane")
	private Integer idPlane;

	private Integer angle;

	@Column(name="pos_x")
	private double posX;

	@Column(name="pos_y")
	private double posY;

	private Integer speed;

	private Integer terminal;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="plane")
	private List<Flight> flights;

	//bi-directional many-to-one association to Airline
	@ManyToOne
	@JoinColumn(name="id_airline")
	private Airline airline;

	//bi-directional many-to-one association to PlaneStatus
	@ManyToOne
	@JoinColumn(name="id_status")
	private PlaneStatus planeStatus;

	//bi-directional many-to-one association to PlaneType
	@ManyToOne
	@JoinColumn(name="id_plane_type")
	private PlaneType planeType;

	public Plane() {
	}

	public Integer getIdPlane() {
		return this.idPlane;
	}

	public void setIdPlane(Integer idPlane) {
		this.idPlane = idPlane;
	}

	public Integer getAngle() {
		return this.angle;
	}

	public void setAngle(Integer angle) {
		this.angle = angle;
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

	public Integer getSpeed() {
		return this.speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getTerminal() {
		return this.terminal;
	}

	public void setTerminal(Integer terminal) {
		this.terminal = terminal;
	}

	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Flight addFlight(Flight flight) {
		getFlights().add(flight);
		flight.setPlane(this);

		return flight;
	}

	public Flight removeFlight(Flight flight) {
		getFlights().remove(flight);
		flight.setPlane(null);

		return flight;
	}

	public Airline getAirline() {
		return this.airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public PlaneStatus getPlaneStatus() {
		return this.planeStatus;
	}

	public void setPlaneStatus(PlaneStatus planeStatus) {
		this.planeStatus = planeStatus;
	}

	public PlaneType getPlaneType() {
		return this.planeType;
	}

	public void setPlaneType(PlaneType planeType) {
		this.planeType = planeType;
	}

}