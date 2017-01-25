package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lane_type database table.
 *
 */
@Entity
@Table(name = "lane_type")
@NamedQuery(name = "LaneType.findAll", query = "SELECT l FROM LaneType l")
public class LaneType implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id lane type. */
	@Id
	@Column(name = "id_lane_type")
	private Integer idLaneType;

	/** The description. */
	private String description;

	/** The lanes. */
	//bi-directional many-to-one association to Lane
	@OneToMany(mappedBy = "laneType", fetch = FetchType.EAGER)
	private List<Lane> lanes;

	/**
	 * Instantiates a new lane type.
	 */
	public LaneType() {
	}

	/**
	 * Gets the id lane type.
	 *
	 * @return the id lane type
	 */
	public Integer getIdLaneType() {
		return this.idLaneType;
	}

	/**
	 * Sets the id lane type.
	 *
	 * @param idLaneType the new id lane type
	 */
	public void setIdLaneType(Integer idLaneType) {
		this.idLaneType = idLaneType;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the lanes.
	 *
	 * @return the lanes
	 */
	public List<Lane> getLanes() {
		return this.lanes;
	}

	/**
	 * Sets the lanes.
	 *
	 * @param lanes the new lanes
	 */
	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}

	/**
	 * Adds the lane.
	 *
	 * @param lane the lane
	 * @return the lane
	 */
	public Lane addLane(Lane lane) {
		getLanes().add(lane);
		lane.setLaneType(this);

		return lane;
	}

	/**
	 * Removes the lane.
	 *
	 * @param lane the lane
	 * @return the lane
	 */
	public Lane removeLane(Lane lane) {
		getLanes().remove(lane);
		lane.setLaneType(null);

		return lane;
	}

}