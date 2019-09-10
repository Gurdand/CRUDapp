package dao;

import exception.ApplicationException;
import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(Configuration configuration) throws ApplicationException {
        try {
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("Ошибка создания sessionFactory!");
        }

    }

    private Session getSession() throws ApplicationException {
        try {
            return sessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("Ошибка открытия сессии!");
        }
    }

    @Override
    public void createUser(User user) throws ApplicationException {

        Transaction transaction = null;

        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Ошибка транзакции!");
        }
    }

    @Override
    //language=hql
    public List<User> getAllUsers() throws ApplicationException {
        try (Session session = getSession()) {

            Transaction transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User").list();
            transaction.commit();
            return users;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("Ошибка получения данных!");
        }

    }

    @Override
    public void updateUser(User user) throws ApplicationException {

        Transaction transaction = null;

        try (Session session = getSession()) {

            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Ошибка транзакции!");
        }
    }

    @Override
    //language=hql
    public void deleteUser(int id) throws ApplicationException {

        Transaction transaction = null;

        try (Session session = getSession()) {

            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id = :id")
                    .setParameter("id", id).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Ошибка транзакции!");
        }
    }


}
