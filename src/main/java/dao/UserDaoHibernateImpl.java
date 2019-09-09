package dao;


import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(Configuration configuration) {
        try {
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            sessionFactory = null;
        }

    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void createUser(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            throw new TransactionException("Ошибка транзакции!");
        }
    }

    @Override
    //language=hql
    public List<User> getAllUsers() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<User> users = session.createQuery("FROM User").list();
            transaction.commit();
            session.close();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }

    }

    @Override
    public void updateUser(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            throw new TransactionException("Ошибка транзакции!");
        }
    }

    @Override
    //language=hql
    public void deleteUser(int id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createQuery("DELETE FROM User WHERE id = :id")
                    .setParameter("id", id).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            throw new TransactionException("Ошибка транзакции!");
        }
    }


}
