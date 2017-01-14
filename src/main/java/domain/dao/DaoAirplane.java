package domain.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.model.Plane;
import hibernate.util.HibernateUtil;

/**
 * Class to connect and make changes on airplanes in the data-base.
 * @author PBL5
 *
 */
public class DaoAirplane extends HibernateUtil{
	
	public DaoAirplane(){
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Plane> loadPlane() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Plane> items = null;
        System.out.println("LLEGA 4");

        try {
            items = (List<Plane>) session.
                    createQuery("from Plane ").getResultList();
            System.out.println("LLEGA 5");

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally{
            session.close();
        }
        return items;
    }

	public void updatePlane(Plane plane) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;
		 try{
		 	tx = session.beginTransaction();
		 	session.update(plane);
		 	tx.commit();
		 }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	    }		
	}

}
