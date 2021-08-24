import Pojo.TableTest;
import aquality.selenium.core.logging.Logger;
import java.sql.*;
import java.util.ArrayList;

public class DBWork {

    public TableTest tableTest;

    public static int insertRowInTable(Connection connection, String fields, String value) throws SQLException {
        Logger.getInstance().info("Run method insertRowInTable");
        String sql = String.format("INSERT INTO test ("+fields+") Values ("+value+")");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeUpdate();
    }

    public static ArrayList getArrayListFromTableTest(Connection connection, String whereSearch, String limit, int digitForSort) throws SQLException {
        Logger.getInstance().info("Run method getArrayListFromTableTest");
        ArrayList <TableTest> listWithResults = new ArrayList<>();
        String sql = String.format("SELECT * FROM test Where %s Like '%%%d%%' LIMIT %d",whereSearch,digitForSort,Integer.parseInt(limit));
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            TableTest actualTableTest = new TableTest(resultSet.getInt(1),resultSet.getString(2)
                    ,resultSet.getInt(3),resultSet.getString(4),resultSet.getInt(5),
                    resultSet.getInt(6),resultSet.getString(7),resultSet.getString(8)
                    ,resultSet.getString(9),resultSet.getString(10),resultSet.getInt(11));
            listWithResults.add(actualTableTest);
            Logger.getInstance().info(listWithResults.get(listWithResults.size()-1).toString());
        }
        return listWithResults;
    }
    public static int updateTable(ArrayList<TableTest> list,Connection connection) throws SQLException {
        Logger.getInstance().info("Run method updateTable");
        int result = 0;
        for (int i = 0;i <= list.size() -1;i++){
            TableTest tableTest = new TableTest();
        tableTest = list.get(i);
        String sql ="UPDATE test SET Status_id = ? ,End_time = ?,Start_time = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,tableTest.getStatus_id());
        preparedStatement.setString(2, tableTest.getStart_time());
        preparedStatement.setString(3, tableTest.getEnd_time());
        preparedStatement.setInt(4,tableTest.getId());
        int resultRaw = preparedStatement.executeUpdate();
        result += resultRaw;
        }
        return result;
    }

    public static int deleteValue(Connection connection,ArrayList<TableTest> list) throws SQLException {
        Logger.getInstance().info("Run method deleteValue");
        int result = 0;
        for (TableTest tableTestFromList: list) {
            TableTest tableTest = tableTestFromList;
            Logger.getInstance().info("Delete row: "+tableTest.toString());
            String selectSQL = "DELETE FROM test WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, tableTest.getId());
            result += preparedStatement.executeUpdate();
        }
        return result;
    }
}
