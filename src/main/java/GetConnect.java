import aquality.selenium.core.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnect {
    public static Connection getValueForConnectToDatabase(String propertiesFilePath) throws SQLException {
        Logger.getInstance().info("Get value from properties file");
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(propertiesFilePath))) {
            props.load(in);
        }catch (IOException e){
            Logger.getInstance().debug("Exception",e);
        }
        String urlToDatabase = props.getProperty("urlToDatabase");
        String userName = props.getProperty("userName");
        String password = props.getProperty("password");

        return DriverManager.getConnection(urlToDatabase, userName, password);
    }
}
