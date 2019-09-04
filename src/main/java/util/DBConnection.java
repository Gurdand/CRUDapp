package util;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {

    }

    public static Connection getConnection() {
        if (connection == null) {

            //Properties properties = new Properties();

            try{
                //(InputStream in = Files.newInputStream(Paths.get("resources","db.properties")))
                //properties.load(in);

                DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

                //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

//                String url = "jdbc:mysql://localhost:3306/user_db?serverTimezone=Europe/Moscow&useSSL=false";
//                String userName = "root";
//                String password = "12345";

                StringBuilder url = new StringBuilder();

                url.
                        append("jdbc:mysql://").        //db type
                        append("localhost:").           //host name
                        append("3306/").                //port
                        append("user_db?").          //db name
                        append("serverTimezone=Europe/Moscow&useSSL=false&useUnicode=yes&characterEncoding=utf8&").
                        append("user=root&").          //login
                        append("password=12345");       //password

                System.out.println("URL: " + url + "\n");

//                connection = DriverManager.getConnection(properties.getProperty("url"),
//                        properties.getProperty("login"), properties.getProperty("password"));

                connection = DriverManager.getConnection(url.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return connection;
    }
}
