package despresso.persistence;

import despresso.logic.Setting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingRepository implements RepositoryInterface<Setting> {

    private final String DB_URL = "jdbc:h2:mem:database";
    private final String JDBC_DRIVER = "org.h2.Driver";

    private static final String USER = "sa";
    private static final String PASS = "";

    // TODO: never make a call to get ALL data!!!
    @Override
    public List<Setting> getAll() {
        String query = "SELECT * FROM setting";
        List<Setting> resultList = databaseGet(query);
        return resultList;
    }

    @Override
    public Setting getByID(int id) {
        String query = "SELECT * FROM setting where id='" + id + "'";
        List<Setting> resultList = databaseGet(query);
        if (resultList.size() > 0) return resultList.get(0);
        else return null;
    }

    @Override
    public void update(Setting setting) {
        String query = "UPDATE setting SET";
        // TODO: implement correct methods
        /*query += " fname='" + setting.getFname() + "', ";
        query += " lname='" + setting.getLname() + "', ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        query += " dob = '" + setting.getDob().format(formatter) + "'";
        query += " WHERE id='" + setting.getId() + "'";*/
        databaseModify(query);
    }

    private List<Setting> databaseGet(String query) {
        List<Setting> resultList = new ArrayList<>();
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
            resultList = populateSettingList(resultSet);

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

    private void databaseModify(String query) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            //Register server
            Class.forName(JDBC_DRIVER);

            //open connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

            connection.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    excep.printStackTrace();
                }
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                assert connection != null;
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Setting> populateSettingList(ResultSet resultSet) throws SQLException {
        List<Setting> userList = new ArrayList<>();
        while (resultSet.next()) {
            Setting setting = new Setting();
            // TODO: implement correct methods
          /*  setting.setId(Integer.parseInt(resultSet.getString("id")));
            setting.setFname(resultSet.getString("fname"));
            setting.setLname(resultSet.getString("lname"));
            setting.setDob(LocalDate.parse(resultSet.getString("dob")));*/
            userList.add(setting);
        }
        return userList;
    }


    @Override
    public void insert(Setting Setting) {
        String query = "INSERT INTO ";
        Setting setting = (Setting) Setting;
        // TODO: implement correct methods

      /*  query += "setting (fname, lname, dob) VALUES (";
        query += "'" + setting.getFname() + "', ";
        query += "'" + setting.getLname() + "', ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        query += "'" + setting.getDob().format(formatter) + "'";*/
        query += ")";
        databaseModify(query);

    }

    @Override
    public void delete(Setting setting) {
        String query = "DELETE FROM setting WHERE id='" + setting.getId() + "'";
        databaseModify(query);
    }
}