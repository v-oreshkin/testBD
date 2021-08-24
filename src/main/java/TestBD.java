import aquality.selenium.core.logging.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TestBD {
    public static void main(String[] args){
        Logger.getInstance().info("Start");
        try(Connection connection = GetConnect.getValueForConnectToDatabase(GlobalConstants.PATH_TO_SQL_PROPERTIES_FILE_FOR_CONNECT)) {
            Logger.getInstance().info("Connection successful");
            DBWork.insertRowInTable(connection,ValuesFromPropertiesFiles
                    .getValue("fields",GlobalConstants.PATH_TO_FILE_FOR_TS1),ValuesFromPropertiesFiles
                    .getValue("value",GlobalConstants.PATH_TO_FILE_FOR_TS1));
        } catch (SQLException e) {
            Logger.getInstance().debug("Connection unsuccessful", e);
        }
    }
}
