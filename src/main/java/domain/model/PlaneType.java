package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the plane_type database table.
 * 
 */
@Entity
@Table(name="plane_type")
@NamedQuery(name="PlaneType.findAll", query="SELECT p FROM PlaneType p")
public class PlaneType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_plane_type")
	private Integer idPlaneType;

	private String description;

	//bi-directional many-to-one association to Plane
	@OneToMany(mappedBy="planeType")
	private List<Plane> planes;

	public PlaneType() {
	}

	public Integer getIdPlaneType() {
		return this.idPlaneType;
	}

	public void setIdPlaneType(Integer idPlaneType) {
		this.idPlaneType = idPlaneType;
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
		plane.setPlaneType(this);

		return plane;
	}

	public Plane removePlane(Plane plane) {
		getPlanes().remove(plane);
		plane.setPlaneType(null);

		return plane;
	}

}