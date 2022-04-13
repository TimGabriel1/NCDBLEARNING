package DAO;

import Model.Training;
import Model.TrainingMajor;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingDAOImpl implements TrainingDAO{


    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStmt;

    @Override
    public List<Training> getAllTrainings() throws SQLException, ClassNotFoundException {

        List<Training> trainingList = new ArrayList<>();

        String sql = "select * from Training ";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Training training = new Training();
            training.setId(resultSet.getInt("id"));
            training.setTitle(resultSet.getString("title"));
            training.setMajor(new TrainingMajorDAOImpl().getTrainingMajor(resultSet.getInt("major")));
            training.setLabel(resultSet.getString("label"));
            training.setCreatedAt(resultSet.getDate("createdAt"));
            training.setUpdatedAt(resultSet.getDate("updatedAt"));
            trainingList.add(training);
        }
        DBUtil.closeConnection();
        return trainingList;
    }

    @Override
    public boolean saveTraining(Training training) {
        boolean flag;
        try {

            String sql = "insert into Training(title, label, major, createdAt) "
                    + "values(?,?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1,training.getTitle());
            preparedStmt.setString(2, training.getLabel());
            preparedStmt.setInt(3, training.getMajor().getId());
            preparedStmt.setDate(4, Date.valueOf(new Date(System.currentTimeMillis()).toString()));

            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }

    @Override
    public boolean deleteTraining(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM Training WHERE id=" + id;
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
    public Training getTraining(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from Training where id =" + id;
        Training training = null;
        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            training = new Training();
            training.setId(resultSet.getInt("id"));
            training.setTitle(resultSet.getString("title"));
            training.setLabel(resultSet.getString("label"));
            training.setMajor(new TrainingMajorDAOImpl().getTrainingMajor(resultSet.getInt("major")));
            training.setCreatedAt(resultSet.getDate("createdAt"));
            training.setUpdatedAt(resultSet.getDate("updatedAt"));

        }
        DBUtil.closeConnection();
        return training;
    }

    @Override
    public boolean updateTraining(Training training) {
        boolean flag = false;

        try {
            String sql = "update Training set title=?, label=?, major=?, updatedAt=? where id= ?";
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1,training.getTitle());
            preparedStmt.setString(2, training.getLabel());
            preparedStmt.setInt(3, training.getMajor().getId());
            preparedStmt.setDate(4, Date.valueOf(new Date(System.currentTimeMillis()).toString()));
            preparedStmt.setInt(5, training.getId());
            preparedStmt.executeUpdate();
            flag = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }
}
