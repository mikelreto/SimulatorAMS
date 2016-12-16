package domain.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lane database table.
 * 
 */
@Entity
@NamedQuery(name="Lane.findAll", query="SELECT l FROM Lane l")
public class Lane implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_lane")
	private Integer idLane;

	@Column(name="lane_order")
	private Integer laneOrder;

	@Column(name="pos_x_final")
	private double posXFinal;

	@Column(name="pos_x_init_lane")
	private double posXInitLane;

	@Column(name="pos_x_init_wait")
	private double posXInitWait;

	@Column(name="pos_y_final")
	private double posYFinal;

	@Column(name="pos_y_init_lane")
	private double posYInitLane;

	@Column(name="pos_y_init_wait")
	private double posYInitWait;

	private String taken;

	//bi-directional many-to-one association to Airport
	@ManyToOne
	@JoinColumn(name="id_airport")
	private Airport airport;

	//bi-directional many-to-one association to LaneType
	@ManyToOne
	@JoinColumn(name="id_lane_type")
	private LaneType laneType;

	public Lane() {
	}

	public Integer getIdLane() {
		return this.idLane;
	}

	public void setIdLane(Integer idLane) {
		this.idLane = idLane;
	}

	public Integer getLaneOrder() {
		return this.laneOrder;
	}

	public void setLaneOrder(Integer laneOrder) {
		this.laneOrder = laneOrder;
	}

	public double getPosXFinal() {
		return this.posXFinal;
	}

	public void setPosXFinal(double posXFinal) {
		this.posXFinal = posXFinal;
	}

	public double getPosXInitLane() {
		return this.posXInitLane;
	}

	public void setPosXInitLane(double posXInitLane) {
		this.posXInitLane = posXInitLane;
	}

	public double getPosXInitWait() {
		return this.posXInitWait;
	}

	public void setPosXInitWait(double posXInitWait) {
		this.posXInitWait = posXInitWait;
	}

	public double getPosYFinal() {
		return this.posYFinal;
	}

	public void setPosYFinal(double posYFinal) {
		this.posYFinal = posYFinal;
	}

	public double getPosYInitLane() {
		return this.posYInitLane;
	}

	public void setPosYInitLane(double posYInitLane) {
		this.posYInitLane = posYInitLane;
	}

	public double getPosYInitWait() {
		return this.posYInitWait;
	}

	public void setPosYInitWait(double posYInitWait) {
		this.posYInitWait = posYInitWait;
	}

	public String getTaken() {
		return this.taken;
	}

	public void setTaken(String taken) {
		this.taken = taken;
	}

	public Airport getAirport() {
		return this.airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public LaneType getLaneType() {
		return this.laneType;
	}

	public void setLaneType(LaneType laneType) {
		this.laneType = laneType;
	}

}