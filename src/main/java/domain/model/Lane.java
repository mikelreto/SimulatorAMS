package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lane database table.
 *
 */
@Entity
@NamedQuery(name = "Lane.findAll", query = "SELECT l FROM Lane l")
public class Lane implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id lane. */
	@Id
	@Column(name = "id_lane")
	private Integer idLane;

	/** The lane order. */
	@Column(name = "lane_order")
	private Integer laneOrder;

	/** The pos X final. */
	@Column(name = "pos_x_final")
	private double posXFinal;

	/** The pos X init lane. */
	@Column(name = "pos_x_init_lane")
	private double posXInitLane;

	/** The pos X init wait. */
	@Column(name = "pos_x_init_wait")
	private double posXInitWait;

	/** The pos Y final. */
	@Column(name = "pos_y_final")
	private double posYFinal;

	/** The pos Y init lane. */
	@Column(name = "pos_y_init_lane")
	private double posYInitLane;

	/** The pos Y init wait. */
	@Column(name = "pos_y_init_wait")
	private double posYInitWait;

	/** The taken. */
	private String taken;

	/** The airport. */
	//bi-directional many-to-one association to Airport
	@ManyToOne
	@JoinColumn(name = "id_airport")
	private Airport airport;

	/** The lane type. */
	//bi-directional many-to-one association to LaneType
	@ManyToOne
	@JoinColumn(name = "id_lane_type")
	private LaneType laneType;

	/** The planes. */
	//bi-directional many-to-one association to Plane
	@OneToMany(mappedBy = "lane", fetch = FetchType.EAGER)
	private List<Plane> planes;

	/**
	 * Instantiates a new lane.
	 */
	public Lane() {
	}

	/**
	 * Gets the id lane.
	 *
	 * @return the id lane
	 */
	public Integer getIdLane() {
		return this.idLane;
	}

	/**
	 * Sets the id lane.
	 *
	 * @param idLane the new id lane
	 */
	public void setIdLane(Integer idLane) {
		this.idLane = idLane;
	}

	/**
	 * Gets the lane order.
	 *
	 * @return the lane order
	 */
	public Integer getLaneOrder() {
		return this.laneOrder;
	}

	/**
	 * Sets the lane order.
	 *
	 * @param laneOrder the new lane order
	 */
	public void setLaneOrder(Integer laneOrder) {
		this.laneOrder = laneOrder;
	}

	/**
	 * Gets the pos X final.
	 *
	 * @return the pos X final
	 */
	public double getPosXFinal() {
		return this.posXFinal;
	}

	/**
	 * Sets the pos X final.
	 *
	 * @param posXFinal the new pos X final
	 */
	public void setPosXFinal(double posXFinal) {
		this.posXFinal = posXFinal;
	}

	/**
	 * Gets the pos X init lane.
	 *
	 * @return the pos X init lane
	 */
	public double getPosXInitLane() {
		return this.posXInitLane;
	}

	/**
	 * Sets the pos X init lane.
	 *
	 * @param posXInitLane the new pos X init lane
	 */
	public void setPosXInitLane(double posXInitLane) {
		this.posXInitLane = posXInitLane;
	}

	/**
	 * Gets the pos X init wait.
	 *
	 * @return the pos X init wait
	 */
	public double getPosXInitWait() {
		return this.posXInitWait;
	}

	/**
	 * Sets the pos X init wait.
	 *
	 * @param posXInitWait the new pos X init wait
	 */
	public void setPosXInitWait(double posXInitWait) {
		this.posXInitWait = posXInitWait;
	}

	/**
	 * Gets the pos Y final.
	 *
	 * @return the pos Y final
	 */
	public double getPosYFinal() {
		return this.posYFinal;
	}

	/**
	 * Sets the pos Y final.
	 *
	 * @param posYFinal the new pos Y final
	 */
	public void setPosYFinal(double posYFinal) {
		this.posYFinal = posYFinal;
	}

	/**
	 * Gets the pos Y init lane.
	 *
	 * @return the pos Y init lane
	 */
	public double getPosYInitLane() {
		return this.posYInitLane;
	}

	/**
	 * Sets the pos Y init lane.
	 *
	 * @param posYInitLane the new pos Y init lane
	 */
	public void setPosYInitLane(double posYInitLane) {
		this.posYInitLane = posYInitLane;
	}

	/**
	 * Gets the pos Y init wait.
	 *
	 * @return the pos Y init wait
	 */
	public double getPosYInitWait() {
		return this.posYInitWait;
	}

	/**
	 * Sets the pos Y init wait.
	 *
	 * @param posYInitWait the new pos Y init wait
	 */
	public void setPosYInitWait(double posYInitWait) {
		this.posYInitWait = posYInitWait;
	}

	/**
	 * Gets the taken.
	 *
	 * @return the taken
	 */
	public String getTaken() {
		return this.taken;
	}

	/**
	 * Sets the taken.
	 *
	 * @param taken the new taken
	 */
	public void setTaken(String taken) {
		this.taken = taken;
	}

	/**
	 * Gets the airport.
	 *
	 * @return the airport
	 */
	public Airport getAirport() {
		return this.airport;
	}

	/**
	 * Sets the airport.
	 *
	 * @param airport the new airport
	 */
	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	/**
	 * Gets the lane type.
	 *
	 * @return the lane type
	 */
	public LaneType getLaneType() {
		return this.laneType;
	}

	/**
	 * Sets the lane type.
	 *
	 * @param laneType the new lane type
	 */
	public void setLaneType(LaneType laneType) {
		this.laneType = laneType;
	}

	/**
	 * Gets the planes.
	 *
	 * @return the planes
	 */
	public List<Plane> getPlanes() {
		return this.planes;
	}

	/**
	 * Sets the planes.
	 *
	 * @param planes the new planes
	 */
	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	/**
	 * Adds the plane.
	 *
	 * @param plane the plane
	 * @return the plane
	 */
	public Plane addPlane(Plane plane) {
		getPlanes().add(plane);
		plane.setLane(this);

		return plane;
	}

	/**
	 * Removes the plane.
	 *
	 * @param plane the plane
	 * @return the plane
	 */
	public Plane removePlane(Plane plane) {
		getPlanes().remove(plane);
		plane.setLane(null);

		return plane;
	}

}