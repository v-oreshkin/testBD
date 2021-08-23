import aquality.selenium.core.logging.Logger;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TestBD {
    public static void main(String[] args){
        Logger.getInstance().info("xz");
        try {
            Connection connection = GetConnect.getValueForConnectToDatabes(GlobalConstants.PATH_TO_SQL_PROPERTIES_FILE);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM status;");
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                System.out.printf("%d. %s \n", id, name);
            }
        } catch (SQLException e) {
            Logger.getInstance().debug("Error", e);
        }
    }
}
