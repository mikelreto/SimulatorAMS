package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the plane_type database table.
 * 
 */
@Entity
@Table(name="plane_type")
@NamedQuery(name="PlaneType.findAll", query="SELECT p FROM PlaneType p")
public class PlaneType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id plane type. */
	@Id
	@Column(name="id_plane_type")
	private Integer idPlaneType;

	/** The description. */
	private String description;

	/** The planes. */
	//bi-directional many-to-one association to Plane
	@OneToMany(mappedBy="planeType", fetch=FetchType.EAGER)
	private List<Plane> planes;

	/**
	 * Instantiates a new plane type.
	 */
	public PlaneType() {
	}

	/**
	 * Gets the id plane type.
	 *
	 * @return the id plane type
	 */
	public Integer getIdPlaneType() {
		return this.idPlaneType;
	}

	/**
	 * Sets the id plane type.
	 *
	 * @param idPlaneType the new id plane type
	 */
	public void setIdPlaneType(Integer idPlaneType) {
		this.idPlaneType = idPlaneType;
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
		plane.setPlaneType(this);

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
		plane.setPlaneType(null);

		return plane;
	}

}