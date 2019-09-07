package util;

import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DBHelper {

    private DBProperties properties = new DBProperties();

    private static Connection connection;

    private static Configuration configuration;

    public Connection getConnection() {
        if (connection == null) {
            try {
                DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance());

                connection = DriverManager.getConnection(properties.getUrl(),
                        properties.getLogin(), properties.getPassword());

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return connection;
    }


    public Configuration getConfiguration() {
        if (configuration == null) {

            try {

                configuration = new Configuration().configure();

            } catch (Exception e) {
                return null;
            }
        }
        return configuration;
    }

}
