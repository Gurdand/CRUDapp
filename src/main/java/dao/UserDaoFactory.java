package dao;

import org.hibernate.SessionFactory;
import util.DBHelper;
import util.DBProperties;

public abstract class UserDaoFactory {

    public abstract UserDAO getUserDAO();

    public static UserDaoFactory getFactory() {

        return (new DBProperties().getAppProperty("useHibernate").contains("true")) ? new HibernateUserDaoFactory() : new JdbcUserDaoFactory();
    }


    static class JdbcUserDaoFactory extends UserDaoFactory {
        @Override
        public UserDAO getUserDAO() {
            return new UserDaoJDBCimpl(new DBHelper().getConnection());
        }
    }

    static class HibernateUserDaoFactory extends UserDaoFactory {

        private SessionFactory sessionFactory;

        HibernateUserDaoFactory() {
            try{
                sessionFactory = new DBHelper().getConfiguration().buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                sessionFactory = null;
            }
        }

        @Override
        public UserDAO getUserDAO() {

            try {
                return new UserDaoHibernateImpl(sessionFactory.openSession());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


}
