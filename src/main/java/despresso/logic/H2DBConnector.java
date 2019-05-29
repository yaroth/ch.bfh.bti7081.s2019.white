package despresso.logic;

import java.sql.*;

public class H2DBConnector {
    //JDBC drivername and database URL

    private final String DB_URL = "jdbc:h2:mem:database";
    private final String JDBC_DRIVER = "org.h2.Driver";

    static final String USER = "sa";
    static final String PASS = "";

    public H2DBConnector() {

    }

    public String databaseGet(String query) {
        Connection connection = null;
        Statement statement = null;

        String returnParameter = "null";

        try {
            //Register server
            Class.forName(JDBC_DRIVER);

            //open connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            //execute query
            statement = connection.createStatement();

            ResultSet response = statement.executeQuery(query);

            ResultSetMetaData metadata = response.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                returnParameter += metadata.getColumnName(i) + ", ";
            }
            while (response.next()) {
                String row = "";
                for (int i = 1; i <= columnCount; i++) {
                    row += response.getString(i) + ", ";
                }
                returnParameter += row;

            }
            // STEP 4: Clean-up environment
            statement.close();
            connection.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
        return returnParameter;
    }

    public boolean databasePut(String query){
        return false;
    }

    public boolean databaseDelete(String query){
        return false;
    }


}