package domain.model;

import java.io.Serializable;
import javax.persistence.*;

import domain.monitor.GestorPistas;
import domain.monitor.Monitor;
import main.Main;

import java.util.List;


/**
 * The persistent class for the plane database table.
 *
 */
@Entity
@NamedQuery(name = "Plane.findAll", query = "SELECT p FROM Plane p")
public class Plane implements Serializable, Runnable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id plane. */
	@Id
	@Column(name = "id_plane")
	private Integer idPlane;

	/** The angle. */
	private Integer angle;

	/** The pos X. */
	@Column(name = "pos_x")
	private double posX;

	/** The pos Y. */
	@Column(name = "pos_y")
	private double posY;

	/** The speed. */
	private Integer speed;

	/** The terminal. */
	private Integer terminal;

	/** The flights. */
	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy = "plane", fetch = FetchType.EAGER)
	private List<Flight> flights;

	/** The airline. */
	//bi-directional many-to-one association to Airline
	@ManyToOne
	@JoinColumn(name = "id_airline")
	private Airline airline;

	/** The lane. */
	//bi-directional many-to-one association to Lane
	@ManyToOne
	@JoinColumn(name = "id_lane")
	private Lane lane;

	/** The plane status. */
	//bi-directional many-to-one association to PlaneStatus
	@ManyToOne
	@JoinColumn(name = "id_status")
	private PlaneStatus planeStatus;

	/** The plane type. */
	//bi-directional many-to-one association to PlaneType
	@ManyToOne
	@JoinColumn(name = "id_plane_type")
	private PlaneType planeType;

	/**
	 * Instantiates a new plane.
	 */
	public Plane() {
	}

	/**
	 * Gets the id plane.
	 *
	 * @return the id plane
	 */
	public Integer getIdPlane() {
		return this.idPlane;
	}

	/**
	 * Sets the id plane.
	 *
	 * @param idPlane the new id plane
	 */
	public void setIdPlane(Integer idPlane) {
		this.idPlane = idPlane;
	}

	/**
	 * Gets the angle.
	 *
	 * @return the angle
	 */
	public Integer getAngle() {
		return this.angle;
	}

	/**
	 * Sets the angle.
	 *
	 * @param angle the new angle
	 */
	public void setAngle(Integer angle) {
		this.angle = angle;
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
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public Integer getSpeed() {
		return this.speed;
	}

	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	/**
	 * Gets the terminal.
	 *
	 * @return the terminal
	 */
	public Integer getTerminal() {
		return this.terminal;
	}

	/**
	 * Sets the terminal.
	 *
	 * @param terminal the new terminal
	 */
	public void setTerminal(Integer terminal) {
		this.terminal = terminal;
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
		flight.setPlane(this);

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
		flight.setPlane(null);

		return flight;
	}

	/**
	 * Gets the airline.
	 *
	 * @return the airline
	 */
	public Airline getAirline() {
		return this.airline;
	}

	/**
	 * Sets the airline.
	 *
	 * @param airline the new airline
	 */
	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	/**
	 * Gets the lane.
	 *
	 * @return the lane
	 */
	public Lane getLane() {
		return this.lane;
	}

	/**
	 * Sets the lane.
	 *
	 * @param lane the new lane
	 */
	public void setLane(Lane lane) {
		this.lane = lane;
	}

	/**
	 * Gets the plane status.
	 *
	 * @return the plane status
	 */
	public PlaneStatus getPlaneStatus() {
		return this.planeStatus;
	}

	/**
	 * Sets the plane status.
	 *
	 * @param planeStatus the new plane status
	 */
	public void setPlaneStatus(PlaneStatus planeStatus) {
		this.planeStatus = planeStatus;
	}

	/**
	 * Gets the plane type.
	 *
	 * @return the plane type
	 */
	public PlaneType getPlaneType() {
		return this.planeType;
	}

	/**
	 * Sets the plane type.
	 *
	 * @param planeType the new plane type
	 */
	public void setPlaneType(PlaneType planeType) {
		this.planeType = planeType;
	}

	/**
	 * En aeropuerto.
	 *
	 * @return true, if successful
	 */
	private boolean enAeropuerto() {
		Boolean enAeropuerto = true;
		if (this.getLane() != null) {
			if (this.getLane().getLaneType().getIdLaneType() == GestorPistas.DESPEGUE) {
				if (this.getPosX() >= this.getLane().getPosXFinal()) {
					enAeropuerto = false;
				}
			}
		}
		return enAeropuerto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	/**
	 * Run function for the multiple threads.
	 */
	public void run() {
		int nextLaneId = 0;
		try {
			Main.getAiportConSemaforo().getSemaforoAeropuerto().acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (enAeropuerto()) {
			nextLaneId = GestorPistas.seeNextLane(this);
			Monitor.enterPista(nextLaneId, this);
		}
		Main.getAiportConSemaforo().getSemaforoAeropuerto().release();
	}

}