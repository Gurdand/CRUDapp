package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DBProperties {

    private String url;
    private String login;
    private String password;
    private boolean useHibernate;

    public DBProperties() {
        Properties properties = new Properties();
        URL res = DBHelper.class.getClassLoader().getResource("db.properties");

        assert res != null;

        try (InputStream in = Files.newInputStream(Paths.get(res.toURI()))) {
            properties.load(in);

            url = properties.getProperty("url");
            login = properties.getProperty("login");
            password = properties.getProperty("password");
            useHibernate = properties.getProperty("useHibernate").contains("true");

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isUseHibernate() {
        return useHibernate;
    }
}
