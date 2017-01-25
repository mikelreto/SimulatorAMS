package domain.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.model.Plane;
import hibernate.util.HibernateUtil;

/**
 * The Class DaoAirplane.
 */
public class DaoAirplane extends HibernateUtil {

	/**
	 * Instantiates a new dao airplane.
	 */
	public DaoAirplane() {
		super();
	}

	/**
	 * Load plane.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Plane> loadPlane() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Plane> items = null;
        try {
            items = (List<Plane>) session.
                    createQuery("from Plane ").getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

	/**
	 * Update plane.
	 *
	 * @param plane the plane
	 */
	public void updatePlane(final Plane plane) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;
		 try {
		 	tx = session.beginTransaction();
		 	session.update(plane);
		 	tx.commit();
		 } catch (HibernateException e) {
	         if (tx != null) {
				tx.rollback();
			}
	         e.printStackTrace();
	     } finally {
	         session.close();
	    }
	}

}
