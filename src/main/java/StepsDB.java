import aquality.selenium.core.logging.Logger;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.SQLException;

public class StepsDB {

    public static Connection connect(String pathToPropertiesFileForConnect) {
        try {
            Logger.getInstance().info("Connection");
            return GetConnect.getValueForConnectToDatabase(pathToPropertiesFileForConnect);
        }catch (SQLException ex){
            Logger.getInstance().debug("Connection error",ex);
        }
        return null;
    }

    public static void insertPatternInDBAndCheckResult(String pathToPropertiesFileForConnect,
                                                       String fields, String values,String countInsertRaw) {
        Logger.getInstance().info("Insert: "+values+" in fields: "+fields+" in data base");
        try {
            Assert.assertEquals(DBWork
                    .insertRowInTable(connect(pathToPropertiesFileForConnect), fields, values),Integer
                    .parseInt(countInsertRaw),"Insert success");
        } catch (SQLException ex) {
            Logger.getInstance().debug("Error " + ex);
        }
    }

    public static void getSelectUseWhere(String pathToPropertiesFileForConnect,String table, String whereSearch, String limit,int digitForSort) throws SQLException {
        DBWork.selectRequestWithWhereLimit(connect(pathToPropertiesFileForConnect),table,whereSearch,limit,digitForSort);
    }

}
