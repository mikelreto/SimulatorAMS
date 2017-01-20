package domain.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.model.Flight;
import hibernate.util.HibernateUtil;

/**
 * The Class DaoFlight.
 */
public class DaoFlight extends HibernateUtil {

	/** The Constant WAITINGSTATE. */
    private static final int WAITINGSTATE = 8;
	/**
	 * Instantiates a new dao flight.
	 */
	public DaoFlight() {
		super();
	}

	/**
	 * Update flight.
	 *
	 * @param vuelo the vuelo
	 */
	public static void updateFlight(final Flight vuelo) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;
		 try {
		 	tx = session.beginTransaction();
		 	session.update(vuelo);
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

	/**
	 * Gets the new flight.
	 *
	 * @param idPlane the idPlane
	 * @return the new flight
	 */
	public static Object getNewFlight(final Integer idPlane) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Object vuelo = null;
		int idStatus = WAITINGSTATE;
		try {
            Query query = session.createQuery("from Flight"
            		+ " where id_plane = :id_plane "
            		+ "and id_status = :id_status order by time_from asc");
            query.setParameter("id_plane", idPlane);
            query.setParameter("id_status", idStatus);
            vuelo = (Flight) query.getSingleResult();
		} catch (HibernateException e) {
            e.printStackTrace();
        } catch (NoResultException e1) {
        	vuelo = null;
        }
		return vuelo;
	}
}
