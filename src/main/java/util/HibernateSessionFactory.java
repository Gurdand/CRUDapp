package util;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class HibernateSessionFactory {

    private static final org.hibernate.SessionFactory ourSessionFactory;

    static {

        Properties properties = new Properties();
        URL res = HibernateSessionFactory.class.getClassLoader().getResource("db.properties");

        assert res != null;

        try (InputStream in = Files.newInputStream(Paths.get(res.toURI()))) {
            properties.load(in);

            Configuration configuration = new Configuration();
            configuration.configure();

            configuration.addAnnotatedClass(User.class);
            //configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.username", properties.getProperty("login"));
            //configuration.setProperty("hibernate.connection.password", "12345");
            configuration.setProperty("hibernate.connection.password", properties.getProperty("password"));
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "validate");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


}
