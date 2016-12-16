package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lane_type database table.
 * 
 */
@Entity
@Table(name="lane_type")
@NamedQuery(name="LaneType.findAll", query="SELECT l FROM LaneType l")
public class LaneType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_lane_type")
	private Integer idLaneType;

	private String description;

	//bi-directional many-to-one association to Lane
	@OneToMany(mappedBy="laneType")
	private List<Lane> lanes;

	public LaneType() {
	}

	public Integer getIdLaneType() {
		return this.idLaneType;
	}

	public void setIdLaneType(Integer idLaneType) {
		this.idLaneType = idLaneType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Lane> getLanes() {
		return this.lanes;
	}

	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}

	public Lane addLane(Lane lane) {
		getLanes().add(lane);
		lane.setLaneType(this);

		return lane;
	}

	public Lane removeLane(Lane lane) {
		getLanes().remove(lane);
		lane.setLaneType(null);

		return lane;
	}

}