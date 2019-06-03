package despresso.persistence;

import despresso.DataType;
import despresso.logic.DataTypeInterface;
import despresso.logic.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class H2DBConnector {
    //JDBC drivername and database URL

    private final String DB_URL = "jdbc:h2:mem:database";
    private final String JDBC_DRIVER = "org.h2.Driver";

    private static final String USER = "sa";
    private static final String PASS = "";

    // TODO: do we need a H2DBConnector Singleton????
    public H2DBConnector() {
    }

    private List<DataTypeInterface> databaseGet(DataType type, String query) {
        List<DataTypeInterface> resultList = new ArrayList<>();
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
            // TODO: case for each DataType (SETTING, MOOD, CALENDAR_ENTRY, TIP)
            if (type.equals(DataType.USER)) {
                resultList = populateUserList(resultSet);
            }

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

    private List<DataTypeInterface> populateUserList(ResultSet resultSet) throws SQLException {
        List<DataTypeInterface> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(Integer.parseInt(resultSet.getString("id")));
            user.setFname(resultSet.getString("fname"));
            user.setLname(resultSet.getString("lname"));
            user.setDob(LocalDate.parse(resultSet.getString("dob")));
            userList.add(user);
        }
        return userList;
    }

    // TODO: never make a call to get ALL data
    public List<DataTypeInterface> getAll(DataType type) {
        String query = "SELECT * FROM ";
        // TODO: lots of cases for the _ type sets
        if (type.equals(DataType.USER)) {
            query += "user";
        }
        List<DataTypeInterface> resultList = databaseGet(type, query);
        return resultList;
    }

    public DataTypeInterface getById(DataType type, int id) {
        String query = "SELECT * FROM ";
        // TODO: lots of case for the different type sets
        if (type.equals(DataType.USER)) {
            query += "user where id='" + id + "'";
        }
        List<DataTypeInterface> resultList = databaseGet(type, query);
        // TODO: catch error if size is > 1
        if (resultList.size() > 0) return resultList.get(0);
        else return null;
    }

    public void insert(DataTypeInterface dataTypeInterface) {
        String query = "INSERT INTO ";
        // TODO: lots of cases for the different type sets
        if (dataTypeInterface instanceof User) {
            User user = (User) dataTypeInterface;
            query += "user (fname, lname, dob) VALUES (";
            query += "'" + user.getFname() + "', ";
            query += "'" + user.getLname() + "', ";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
            query += "'" + user.getDob().format(formatter) + "'";
            query += ")";
        }
        databaseModify(query);

    }

    public void update(DataTypeInterface dataTypeInterface) {
        String query = "UPDATE";
        // TODO: lots of cases for the different type sets
        if (dataTypeInterface instanceof User) {
            User user = (User) dataTypeInterface;
            query += " user SET";
            query += " fname='" + user.getFname() + "', ";
            query += " lname='" + user.getLname() + "', ";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
            query += " dob = '" + user.getDob().format(formatter) + "'";
            query += " WHERE id='" + user.getId() + "'";
        }
        databaseModify(query);
    }

    public void delete(DataTypeInterface dataTypeInterface) {
        String query = "DELETE FROM";
        // TODO: lots of cases for the different type sets
        if (dataTypeInterface instanceof User) {
            User user = (User) dataTypeInterface;
            query += " user";
            query += " WHERE id='" + user.getId() + "'";
        }
        databaseModify(query);
    }
}