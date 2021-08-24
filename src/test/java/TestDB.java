import aquality.selenium.core.logging.Logger;
import org.testng.annotations.Test;
import utils.GetRandom;

import java.sql.SQLException;

public class TestDB {

    @Test
    public void testTS1(){
        Logger.getInstance().info("Start test 1");
        StepsDB.insertPatternInDBAndCheckResult(GlobalConstants.PATH_TO_SQL_PROPERTIES_FILE_FOR_CONNECT,ValuesFromPropertiesFiles
                .getValue("fields",GlobalConstants.PATH_TO_FILE_FOR_TS1),ValuesFromPropertiesFiles
                .getValue("value",GlobalConstants.PATH_TO_FILE_FOR_TS1),ValuesFromPropertiesFiles
                .getValue("countRow",GlobalConstants.PATH_TO_FILE_FOR_TS1));
        Logger.getInstance().info("Test pass");
    }

    @Test
    public void testTS2() throws SQLException {
        Logger.getInstance().info("Start test 2");
        StepsDB.getSelectUseWhere(GlobalConstants.PATH_TO_SQL_PROPERTIES_FILE_FOR_CONNECT,ValuesFromPropertiesFiles
                .getValue("table",GlobalConstants.PATH_TO_FILE_FOR_TS2),ValuesFromPropertiesFiles
                .getValue("whereSearch",GlobalConstants.PATH_TO_FILE_FOR_TS2),ValuesFromPropertiesFiles
                .getValue("limit",GlobalConstants.PATH_TO_FILE_FOR_TS2),GetRandom.getRandomDoubleValue());
    }
}
