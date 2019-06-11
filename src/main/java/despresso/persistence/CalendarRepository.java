package despresso.persistence;

import despresso.logic.CalendarEntry;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarRepository implements RepositoryInterface<CalendarEntry>{

    private final String DB_URL = "jdbc:h2:mem:database";
    private final String JDBC_DRIVER = "org.h2.Driver";

    private static final String USER = "sa";
    private static final String PASS = "";

    @Override
    public List<CalendarEntry> getAll() {
        String query = "SELECT * FROM calendarEntry";
        List<CalendarEntry> resultList = databaseGet(query);
        return resultList;
    }

    // TODO: needs correction once dev_repo_to_DB merged into master.
    @Override
    public CalendarEntry getByID(int id) {
        String query = "SELECT * FROM calendarEntry where id='" + id + "'";
        List<CalendarEntry> resultList = databaseGet(query);
        if (resultList.size() > 0) return resultList.get(0);
        else return null;
    }

    @Override
    public void insert(CalendarEntry entry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String query = "INSERT INTO ";
        query += "calendarEntry (title, start, end, description, color, isDone) VALUES (";
        query += "'" + entry.getTitle() + "', ";
        query += "'" + entry.getStart().format(formatter) + "', ";
        query += "'" + entry.getEnd().format(formatter) + "', ";
        query += "'" + entry.getDescription() + "', ";
        query += "'" + entry.getColor() + "', ";
        query += "'" + (entry.getIsDone() ? 1 : 0) + "', ";
        query += ")";
        databaseModify(query);

    }

    @Override
    public void update(CalendarEntry entry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String query = "UPDATE calendarEntry SET";
        query += " title='" + entry.getTitle() + "', ";
        query += " start='" + entry.getStart().format(formatter) + "', ";
        query += " end='" + entry.getEnd().format(formatter) + "', ";
        query += " description = '" + entry.getDescription() + "'";
        query += " color = '" + entry.getColor() + "'";
        query += " isDone = '" + (entry.getIsDone() ? 1 : 0) + "'";
        query += " WHERE id='" + entry.getId() + "'";
        databaseModify(query);
    }

    @Override
    public void delete(CalendarEntry entry) {
        String query = "DELETE FROM calendarEntry WHERE id='" + entry.getId() + "'";
        databaseModify(query);
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

    private List<CalendarEntry> databaseGet(String query) {
        List<CalendarEntry> resultList = new ArrayList<>();
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
            resultList = populateCalendarEntryList(resultSet);

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

    private List<CalendarEntry> populateCalendarEntryList(ResultSet resultSet) throws SQLException {
        List<CalendarEntry> userList = new ArrayList<>();
        while (resultSet.next()) {
            CalendarEntry entry = new CalendarEntry();
            entry.setId(String.valueOf(Integer.parseInt(resultSet.getString("id"))));
            entry.setTitle(resultSet.getString("title"));
            entry.setStart(LocalDateTime.parse(resultSet.getString("start")));
            entry.setEnd(LocalDateTime.parse(resultSet.getString("end")));
            entry.setDescription(resultSet.getString("description"));
            entry.setColor(resultSet.getString("color"));
            entry.setIsDone(resultSet.getString("isDone").equals("1"));
            userList.add(entry);
        }
        return userList;
    }
}
