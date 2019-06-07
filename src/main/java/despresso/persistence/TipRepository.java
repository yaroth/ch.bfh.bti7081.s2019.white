package despresso.persistence;

import despresso.logic.Tip;
import despresso.logic.TipDuration;
import despresso.logic.TipLocation;
import despresso.logic.TipType;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TipRepository implements RepositoryInterface<Tip> {

    private final String DB_URL = "jdbc:h2:mem:database";
    private final String JDBC_DRIVER = "org.h2.Driver";

    private static final String USER = "sa";
    private static final String PASS = "";

    // TODO: never make a call to get ALL data!!!
    @Override
    public List<Tip> getAll() {
        String query = "SELECT * FROM tip";
        List<Tip> tipList = databaseGet(query);
        TipFeelingRepository tipFeelingRepository = new TipFeelingRepository();
        for (Tip tip : tipList) {
            tip.setFeelingList(tipFeelingRepository.getFeelingListforTip(tip));
        }
        return tipList;
    }

    @Override
    public Tip getByID(int id) {
        String query = "SELECT * FROM tip where id='" + id + "'";
        List<Tip> resultList = databaseGet(query);
        if (resultList.size() > 0) return resultList.get(0);
        else return null;
    }

    @Override
    public void update(Tip tip) {
        String query = "UPDATE tip SET";
        // TODO: implement correct methods
        /*query += " fname='" + tip.getFname() + "', ";
        query += " lname='" + tip.getLname() + "', ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        query += " dob = '" + tip.getDob().format(formatter) + "'";*/
        query += " WHERE id='" + tip.getId() + "'";
        databaseModify(query);
    }

    private List<Tip> databaseGet(String query) {
        List<Tip> resultList = new ArrayList<>();
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
            resultList = populateTipList(resultSet);

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

    private List<Tip> populateTipList(ResultSet resultSet) throws SQLException {
        List<Tip> tipList = new ArrayList<>();
        while (resultSet.next()) {
            Tip tip = new Tip();
            tip.setId(Integer.parseInt(resultSet.getString("id")));
            int locationInt = Integer.parseInt(resultSet.getString("location"))-1;
            tip.setTipLocation(TipLocation.values()[locationInt]);
            int typeInt = Integer.parseInt(resultSet.getString("type"))-1;
            tip.setTipType(TipType.values()[typeInt]);
            int durationInt = Integer.parseInt(resultSet.getString("duration"))-1;
            tip.setTipDuration(TipDuration.values()[durationInt]);
            tip.setDescription(resultSet.getString("description"));
            tipList.add(tip);
        }
        return tipList;
    }


    @Override
    public void insert(Tip tip) {
        String query = "INSERT INTO ";
        // TODO: implement correct methods
/*        query += "tip (fname, lname, dob) VALUES (";
        query += "'" + tip.getFname() + "', ";
        query += "'" + tip.getLname() + "', ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        query += "'" + tip.getDob().format(formatter) + "'";*/
        query += ")";
        databaseModify(query);

    }

    @Override
    public void delete(Tip tip) {
        String query = "DELETE FROM tip WHERE id='" + tip.getId() + "'";
        databaseModify(query);
    }
}