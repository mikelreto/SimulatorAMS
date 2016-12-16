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

    /*public User loadUser222(User user){
        Session session =
                HibernateUtil.createSessionFactory().getCurrentSession();
        session.beginTransaction();
        User usuario = null;
        try{
            Query query = session.createQuery(
                          "from User where username =
                          :username and password = :password ");
            query.setParameter("username", user.getUsername());
            query.setParameter("password", user.getPassword());
            usuario = (User) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return usuario;
    }*/

    /*
     // For adding items in the Items table.
     public ItemMaster add(ItemMaster item) {
        Session session = HibernateUtil.
                          createSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        return item;
    }

    // For deleting item from Items table.

    public ItemMaster delete(Long id) {
        Session session = HibernateUtil.
                          createSessionFactory().getCurrentSession();
        session.beginTransaction();
        ItemMaster item = (ItemMaster) session.
                          load(ItemMaster.class, id);
        if (null != item) {
            session.delete(item);
        }
        session.getTransaction().commit();
        return item;
    }
    // For generating , executing hibernate
     * select query and returns items as a
    // list.
*/
    /**
     * Function to load the list of the users.
     * @return the list of the users.
     */
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