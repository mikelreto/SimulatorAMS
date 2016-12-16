package hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The class HibernateUtil.
 * @author PBL5
 *
 */
public class HibernateUtil {

    /**
     * The session factory.
     */
    private static SessionFactory sessionFactory = buildSessionFactory();

    /**
     * The session factory builder.
     * @return the sessionFactory.
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            System.out.println("LLEGA 3");
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();

            return sessionFactory;
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    /**
     * Getter of the session factory.
     * @return the session factory.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Function of shutdown.
     */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
