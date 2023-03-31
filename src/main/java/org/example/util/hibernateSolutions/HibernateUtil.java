package org.example.util.hibernateSolutions;

import org.example.model.Equation;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public synchronized static SessionFactory getFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().addAnnotatedClass(Equation.class).buildSessionFactory();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
