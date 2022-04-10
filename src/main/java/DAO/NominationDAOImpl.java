package DAO;

import Model.Nomination;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NominationDAOImpl implements NominationDAO{
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStmt;
    @Override
    public List<Nomination> getAllNominations() throws SQLException, ClassNotFoundException {
        List<Nomination> nominationList = new ArrayList<>();

        String sql = "select * from Nomination ";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Nomination nomination = new Nomination();
            nomination.setId(resultSet.getInt("id"));

            nominationList.add(nomination);
        }
        DBUtil.closeConnection();
        return nominationList;
    }

    @Override
    public boolean saveNomination(Nomination nomination) {
        boolean flag;
        try {

            String sql = "insert into Nomination(training, trainingProvider, trainingSponsor, trainingResidence, nominationID) "
                    + "values(?,?,?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            setNominationObject(nomination, sql);
            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }

    @Override
    public boolean deleteNomination(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM Nomination WHERE id=" + id;
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBUtil.closeConnection();
        return flag;
    }

    @Override
    public Nomination getNomination(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from Nomination where id =" + id;
        Nomination nomination = null;
        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            nomination = new Nomination();
            nomination.setId(resultSet.getInt("id"));


        }
        DBUtil.closeConnection();
        return nomination;
    }

    @Override
    public boolean updateNomination(Nomination nomination) {
        boolean flag = false;

        try {
            String sql = "update Nomination set training=?, trainingProvider=?, trainingSponsor=?, trainingResidence=?, nominationID=? where id= ?";
            connection = DBUtil.openConnection();
            setNominationObject(nomination, sql);
            preparedStmt.setInt(6, nomination.getId());
            preparedStmt.executeUpdate();
            flag = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    private void setNominationObject(Nomination nomination, String sql) throws SQLException {
        preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setInt(1, nomination.getTraining().getId());
        preparedStmt.setInt(2, nomination.getTrainingProvider().getId());
        preparedStmt.setString(3, nomination.getTrainingSponsor().name());
        preparedStmt.setString(4, nomination.getTrainingResidence().name());
        preparedStmt.setString(5, nomination.getNominationId());
    }
}
