package dao;


import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.List;

public class UserDaoHibernateImpl implements UserDAO {

    private Session session;

    UserDaoHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public void createUser(User user) {
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
