import Pojo.TableTest;
import aquality.selenium.core.logging.Logger;
import java.sql.*;

public class DBWork {

    public TableTest tableTest;

    public static void insertRowInTable(Connection connection, String fields, String value) throws SQLException {
        String sql = String.format("INSERT INTO test ("+fields+") Values ("+value+")");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int rows = preparedStatement.executeUpdate();
    }
    public static void selectRequestGetCertainCountOfRow(Connection connection, String fieldName, int lastCountOfRow) throws SQLException {
        TableTest tableTest = new TableTest();
        String sql = String.format("SELECT * FROM %s ORDER BY id DESC LIMIT %d",fieldName,lastCountOfRow);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            tableTest.setId(resultSet.getInt(1));
            tableTest.setName(resultSet.getString(2));
            tableTest.setStatus_id(resultSet.getInt(3));
            tableTest.setMethod_name(resultSet.getString(4));
            tableTest.setProject_id(resultSet.getInt(5));
            tableTest.setSession_id(resultSet.getInt(6));
            tableTest.setStart_time(resultSet.getString(7));
            tableTest.setEnd_time(resultSet.getString(8));
            tableTest.setEnv(resultSet.getString(9));
            tableTest.setBrowser(resultSet.getString(10));
            tableTest.setAuthor_id(resultSet.getInt(11));

            System.out.println(tableTest.toString());
        }
    }
    public static void insertRequest(Connection connection) throws SQLException{
        String sql = "INSERT INTO author (id,name,login,email) Values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "Vadim2");
        preparedStatement.setString(3,"Orex2");
        preparedStatement.setString(4,"v.oreshkin943@gmail.com");

        int rows = preparedStatement.executeUpdate();

        Logger.getInstance().info("Was added "+rows+" rows.");
    }
}
