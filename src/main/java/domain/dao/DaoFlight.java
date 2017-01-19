package domain.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.model.Flight;
import hibernate.util.HibernateUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class DaoFlight.
 */
public class DaoFlight extends HibernateUtil{
	
	/**
	 * Instantiates a new dao flight.
	 */
	public DaoFlight(){
		super();
	}
	
	/**
	 * Update flight.
	 *
	 * @param vuelo the vuelo
	 */
	public static void updateFlight(Flight vuelo){
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;
		 try{
		 	tx = session.beginTransaction();
		 	session.update(vuelo);
		 	tx.commit();
		 }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	    }
	}

	/**
	 * Gets the new flight.
	 *
	 * @param id_plane the id plane
	 * @return the new flight
	 */
	public static Object getNewFlight(Integer id_plane) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Object vuelo = null;
		int id_status = 8;
		try {
            Query query = session.createQuery("from Flight where id_plane = :id_plane and id_status = :id_status order by time_from asc");
            query.setParameter("id_plane", id_plane);
            query.setParameter("id_status", id_status);
            vuelo = (Flight) query.getSingleResult();
		} catch (HibernateException e) {
            e.printStackTrace();
        } catch (NoResultException e1) {
        	vuelo = null;
        }
		return vuelo;
	}
}
