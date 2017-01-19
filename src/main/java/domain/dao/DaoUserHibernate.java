package domain.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import domain.model.*;
import hibernate.util.*;

/**
 * Class of DaoUserHibernate.
 * @author PBL5
 *
 */
public class DaoUserHibernate extends HibernateUtil {

	/**
	 * Constructor.
	 */
	public DaoUserHibernate() {
		super();
	}
	/**
	 * Function to load the list of the users.
	 * @return the list of the users.
	 */
	@SuppressWarnings("unchecked")
	public List<User> loadUser() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> items = null;
		System.out.println("LLEGA 4");

		try {
			items = (List<User>) session.
					createQuery("from User ").getResultList();
			System.out.println("LLEGA 5");

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return items;
	}
}