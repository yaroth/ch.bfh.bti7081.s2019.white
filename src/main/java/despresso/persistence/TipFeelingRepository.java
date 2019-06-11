package despresso.persistence;

import despresso.logic.Feeling;
import despresso.logic.Feeling;
import despresso.logic.Tip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipFeelingRepository {

    private final String DB_URL = "jdbc:h2:mem:database";
    private final String JDBC_DRIVER = "org.h2.Driver";

    private static final String USER = "sa";
    private static final String PASS = "";


    private List<Feeling> databaseGet(String query) {
        List<Feeling> resultList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            //Register server
            Class.forName(JDBC_DRIVER);

            //open connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();

            //execute query
            ResultSet resultSet = statement.executeQuery(query);
            resultList = populateFeelingList(resultSet);

            // STEP 4: Clean-up environment
            statement.close();
            connection.close();
        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } //end try
        return resultList;
    }
    
    private List<Feeling> populateFeelingList(ResultSet resultSet) throws SQLException {
        List<Feeling> feelingList = new ArrayList<>();
        while (resultSet.next()) {
            feelingList.add(Feeling.valueOf(resultSet.getString("description")));
        }
        return feelingList;
    }

    public List<Feeling> getFeelingListforTip(Tip tip) {
        String query = "SELECT feeling.description from tipfeeling join feeling on feeling.id = tipfeeling.feelingid where tipid='" + tip.getId() + "'";
        return databaseGet(query);
    }
}