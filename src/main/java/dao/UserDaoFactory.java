package dao;

import org.hibernate.SessionFactory;
import util.DBHelper;
import util.DBProperties;

public abstract class UserDaoFactory {

    public abstract UserDAO getUserDAO();

    public static UserDaoFactory getFactory() {

        return (new DBProperties().isUseHibernate()) ? new HibernateUserDaoFactory() : new JdbcUserDaoFactory();
    }


    static class JdbcUserDaoFactory extends UserDaoFactory {

        @Override
        public UserDAO getUserDAO() {
            return new UserDaoJDBCimpl(new DBHelper().getConnection());
        }
    }

    static class HibernateUserDaoFactory extends UserDaoFactory {

        private SessionFactory sessionFactory = new DBHelper().getConfiguration().buildSessionFactory();

        @Override
        public UserDAO getUserDAO() {
            return new UserDaoHibernateImpl(sessionFactory.openSession());
        }
    }


}
