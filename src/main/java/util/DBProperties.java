package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DBProperties {

    private Properties properties = new Properties();

    public DBProperties() {

        URL res = DBHelper.class.getClassLoader().getResource("db.properties");

        assert res != null;

        try (InputStream in = Files.newInputStream(Paths.get(res.toURI()))) {
            properties.load(in);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getAppProperty(String key) {
        return properties.getProperty(key);
    }

}
