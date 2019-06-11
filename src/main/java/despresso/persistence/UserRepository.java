package despresso.persistence;

import despresso.logic.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements RepositoryInterface<User> {

    private final String DB_URL = "jdbc:h2:mem:database";
    private final String JDBC_DRIVER = "org.h2.Driver";

    private static final String USER = "sa";
    private static final String PASS = "";

    // TODO: never make a call to get ALL data!!!
    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM user";
        List<User> resultList = databaseGet(query);
        return resultList;
    }

    @Override
    public User getByID(int id) {
        String query = "SELECT * FROM user where id='" + id + "'";
        List<User> resultList = databaseGet(query);
        if (resultList.size() > 0) return resultList.get(0);
        else return null;
    }

    @Override
    public void update(User user) {
        String query = "UPDATE user SET";
        query += " fname='" + user.getFname() + "', ";
        query += " lname='" + user.getLname() + "', ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        query += " dob = '" + user.getDob().format(formatter) + "'";
        query += " WHERE id='" + user.getId() + "'";
        databaseModify(query);
    }

    private List<User> databaseGet(String query) {
        List<User> resultList = new ArrayList<>();
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
            resultList = populateUserList(resultSet);

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

    private List<User> populateUserList(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
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


    @Override
    public void insert(User User) {
        String query = "INSERT INTO ";
        User user = (User) User;
        query += "user (fname, lname, dob) VALUES (";
        query += "'" + user.getFname() + "', ";
        query += "'" + user.getLname() + "', ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        query += "'" + user.getDob().format(formatter) + "'";
        query += ")";
        databaseModify(query);

    }

    @Override
    public void delete(User user) {
        String query = "DELETE FROM user WHERE id='" + user.getId() + "'";
        databaseModify(query);
    }
}