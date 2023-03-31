package org.example.dao;

import org.example.model.Equation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EquationDao {

    private final SessionFactory sessionFactory;

    public EquationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(final Equation equation) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            session.persist(equation);
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    public void update(final Long id, double root) {
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            txn = session.beginTransaction();
            Equation equation = session.get(Equation.class, id);
            equation.setRoot(root);
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) txn.rollback();
            e.printStackTrace();
        }
    }

    public Optional<List<Equation>> get(double root) {
        List<Equation> list = null;
        try (Session session = sessionFactory.openSession()) {
            list = session.createQuery(
                            "select e from Equation e where e.root = :root", Equation.class)
                    .setParameter("root", root)
                    .getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(list);
    }
}
