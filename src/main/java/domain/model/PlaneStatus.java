package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the plane_status database table.
 * 
 */
@Entity
@Table(name="plane_status")
@NamedQuery(name="PlaneStatus.findAll", query="SELECT p FROM PlaneStatus p")
public class PlaneStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_status")
	private Integer idStatus;

	private String description;

	//bi-directional many-to-one association to Plane
	@OneToMany(mappedBy="planeStatus")
	private List<Plane> planes;

	public PlaneStatus() {
	}

	public Integer getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Plane> getPlanes() {
		return this.planes;
	}

	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	public Plane addPlane(Plane plane) {
		getPlanes().add(plane);
		plane.setPlaneStatus(this);

		return plane;
	}

	public Plane removePlane(Plane plane) {
		getPlanes().remove(plane);
		plane.setPlaneStatus(null);

		return plane;
	}

}