package domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.model.Lane;
import hibernate.util.HibernateUtil;

public class DaoLane extends HibernateUtil{
	
	public DaoLane(){
		super();
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<Lane> loadLane() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Lane> items = null;
        System.out.println("LLEGA 4");
        try {
            items = (List<Lane>) session.
                    createQuery("from Lane ").getResultList();
            System.out.println("LLEGA 5");

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally{
            session.close();
        }
        return items;
    }
	
	@SuppressWarnings("deprecation")
	public synchronized static int getnextLane(Integer TypeID, Integer Orden) {
		System.out.println("En funcion getNextLane de DaoLane");
		int nextLaneId = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println(nextLaneId);
		Query query = session.createSQLQuery(
				"SELECT GetNextLane(:TypeID, :Orden)")
				.setParameter("Orden", Orden)
				.setParameter("TypeID", TypeID);
		nextLaneId = (Integer) query.getSingleResult();
		System.out.println(nextLaneId);
		session.close();
		return nextLaneId;
	}
	
	@SuppressWarnings("deprecation")
	public synchronized static int getTypeSix() {
		int nextLaneId = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery(
				"SELECT getTypeSix()");
		nextLaneId = (Integer) query.getSingleResult();
		session.close();
		return nextLaneId;
	}

	@SuppressWarnings("deprecation")
	public synchronized static int getNextLaneSameOrder(Integer TypeID, Integer Orden) {
		int nextLaneId = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery(
				"SELECT GetNextLaneSameOrder(:TypeID, :Orden)")
				.setParameter("Orden", Orden)
				.setParameter("TypeID", TypeID);
		nextLaneId = (Integer) query.getSingleResult();
		session.close();
		return nextLaneId;
	}

	@SuppressWarnings("deprecation")
	public synchronized static int getNextLaneWithNextOrder(Integer TypeID, Integer Orden) {
		int nextLaneId = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery(
				"SELECT GetNextLaneWithNextOrder(:TypeID, :Orden)")
				.setParameter("Orden", Orden)
				.setParameter("TypeID", TypeID);
		nextLaneId = (Integer) query.getSingleResult();
		session.close();
		return nextLaneId;
	}
	
	@SuppressWarnings("deprecation")
	public synchronized static float getTakeOffLaneFinalPosX(){
		float finalPosX = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery(
				"SELECT getTakeOffLaneFinalPosX()");
		finalPosX =  (Float) query.getSingleResult();
		session.close();
		return finalPosX;
	}
	
	public synchronized void updateLane(Lane lane) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;
		 try{
		 	tx = session.beginTransaction();
		 	session.update(lane);
		 	tx.commit();
		 }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	    }		
	}
}
