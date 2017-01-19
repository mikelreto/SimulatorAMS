package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the plane_status database table.
 * 
 */
@Entity
@Table(name="plane_status")
@NamedQuery(name="PlaneStatus.findAll", query="SELECT p FROM PlaneStatus p")
public class PlaneStatus implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id status. */
	@Id
	@Column(name="id_status")
	private Integer idStatus;

	/** The description. */
	private String description;

	/** The planes. */
	//bi-directional many-to-one association to Plane
	@OneToMany(mappedBy="planeStatus", fetch=FetchType.EAGER)
	private List<Plane> planes;

	/**
	 * Instantiates a new plane status.
	 */
	public PlaneStatus() {
	}

	/**
	 * Gets the id status.
	 *
	 * @return the id status
	 */
	public Integer getIdStatus() {
		return this.idStatus;
	}

	/**
	 * Sets the id status.
	 *
	 * @param idStatus the new id status
	 */
	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
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
		plane.setPlaneStatus(this);

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
		plane.setPlaneStatus(null);

		return plane;
	}

}