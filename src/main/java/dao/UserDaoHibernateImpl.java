package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDAO {

    private Session session;

    UserDaoHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public void createUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(int id) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id = :id")
                .setParameter("id", id).executeUpdate();
        transaction.commit();
        session.close();
    }


}
