package dao;

import org.hibernate.SessionFactory;
import util.DBHelper;
import util.DBProperties;

public interface UserDaoFactory {

    UserDAO getUserDAO();

    static UserDaoFactory getInstance() {
        if (new DBProperties().getAppProperty("useHibernate").contains("true")) {
            return new HibernateDaoFactory();
        } else {
            return new JDBCDaoFactory();
        }
    }

    class HibernateDaoFactory implements UserDaoFactory {

        private SessionFactory sessionFactory;

        @Override
        public UserDAO getUserDAO() {
            if (sessionFactory == null) {
                try {
                    sessionFactory = new DBHelper().getConfiguration().buildSessionFactory();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            return new UserDaoHibernateImpl(sessionFactory.openSession());
        }
    }

    class JDBCDaoFactory implements UserDaoFactory {

        @Override
        public UserDAO getUserDAO() {
            return new UserDaoJDBCimpl(new DBHelper().getConnection());
        }
    }



    /*public static UserDAO getUserDAOo() {
        if (new DBProperties().getAppProperty("useHibernate").contains("true")) {

            try {

                sessionFactory = new DBHelper().getConfiguration().buildSessionFactory();

                return new UserDaoHibernateImpl(sessionFactory.openSession());

//                Configuration configuration = new DBHelper().getConfiguration();
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
//
                //SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
                //SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                //return new UserDaoHibernateImpl(sessionFactory.openSession());
                //return new UserDaoHibernateImpl(new DBHelper().getConfiguration().buildSessionFactory());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return new UserDaoJDBCimpl(new DBHelper().getConnection());
        }
    }*/

    /*ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/

/*    public static UserDaoFactory getFactory() {

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
    }*/


}
