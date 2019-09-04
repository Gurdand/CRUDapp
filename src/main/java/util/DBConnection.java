package util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    public DBConnection() {

    }

    public Connection getConnection() {

            Properties properties = new Properties();
            URL res = getClass().getClassLoader().getResource("db.properties");

// Совет idea для метода toURI
        assert res != null;

        try (InputStream in = Files.newInputStream(Paths.get(res.toURI()))) {
                properties.load(in);

                DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance());

                return DriverManager.getConnection(properties.getProperty("url"),
                        properties.getProperty("login"), properties.getProperty("password"));

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

    }
}
