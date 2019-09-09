package dao;

import util.DBHelper;
import util.DBProperties;

public abstract class UserDaoFactory {

    public static UserDAO getUserDAO() {
        DBHelper helper = new DBHelper();

        return new DBProperties().getAppProperty("useHibernate").contains("true")
                ? new UserDaoHibernateImpl(helper.getConfiguration())
                : new UserDaoJDBCimpl(helper.getConnection());
    }

}
