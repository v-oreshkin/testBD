import Pojo.TableTest;
import aquality.selenium.core.logging.Logger;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

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

    public static void insertPatternInDBAndCheckResultSteps(String pathToPropertiesFileForConnect,
                                                            String fields, String values, String countInsertRaw) {
        Logger.getInstance().info("Run method insertPatternInDBAndCheckResultSteps");
        try {
            Assert.assertEquals(DBWork
                    .insertRowInTable(connect(pathToPropertiesFileForConnect), fields, values),Integer
                    .parseInt(countInsertRaw),"Insert success");
        } catch (SQLException ex) {
            Logger.getInstance().debug("Error " + ex);
        }
    }

    public static void getSelectUseWhereChangeDatesChangeAndUpdateSteps(String pathToPropertiesFileForConnect, String whereSearch, String limit, int digitForSort) throws SQLException {
        Logger.getInstance().info("getSelectUseWhereChangeDatesSteps method is run");
        ArrayList<TableTest> list = DBWork.getArrayListFromTableTest(connect(pathToPropertiesFileForConnect),whereSearch,limit,digitForSort);
        changeValueDateAndStatusSteps(list);
        Assert.assertEquals(DBWork.updateTable(list,connect(pathToPropertiesFileForConnect)), list.size(),"Was not update not all row");
        Assert.assertEquals(DBWork.deleteValue(connect(pathToPropertiesFileForConnect),list),list.size(),"Was not deleted all row");
    }

    public static ArrayList changeValueDateAndStatusSteps(ArrayList<TableTest> list){
        Logger.getInstance().info("changeValueDateAndStatusSteps method is run");
        DateTimeFormatter patterFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0;i < list.size();i++){
            TableTest tableTest = list.get(i);
            tableTest.setStart_time(now.format(patterFormat));
            tableTest.setEnd_time((now.format(patterFormat)));
            tableTest.setStatus_id(new Random().nextInt(3 - 1 + 1) + 1);
            list.set(i,tableTest);
        }
        return list;
    }

}
