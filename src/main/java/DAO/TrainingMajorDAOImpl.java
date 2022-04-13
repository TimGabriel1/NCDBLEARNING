package DAO;

import Model.TrainingMajor;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingMajorDAOImpl implements TrainingMajorDAO{
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStmt;

    public TrainingMajorDAOImpl() {

    }


    @Override
    public List<TrainingMajor> getAllTrainingMajors() throws SQLException, ClassNotFoundException {

        List<TrainingMajor> trainingMajorList = new ArrayList<>();

        String sql = "select * from TrainingMajor ";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            TrainingMajor trainingMajor = new TrainingMajor();
            setTrainingMajorObject(trainingMajor);
            trainingMajorList.add(trainingMajor);
        }
        DBUtil.closeConnection();
        return trainingMajorList;
    }

    @Override
    public boolean saveTrainingMajor(TrainingMajor trainingMajor) {
        boolean flag;
        try {

            String sql = "insert into TrainingMajor(active, name, label, createdAt) "
                    + "values(?,?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setBoolean(1, trainingMajor.isActive());
            preparedStmt.setString(2, trainingMajor.getName());
            preparedStmt.setString(3, trainingMajor.getLabel());
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
    public boolean deleteTrainingMajor(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM TrainingMajor WHERE id=" + id;
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
    public TrainingMajor getTrainingMajor(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from TrainingMajor where id =" + id;
        TrainingMajor trainingMajor;
        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        trainingMajor = new TrainingMajor();
        while (resultSet.next()) {

            setTrainingMajorObject(trainingMajor);


        }
        DBUtil.closeConnection();
        return trainingMajor;
    }

    private void setTrainingMajorObject(TrainingMajor trainingMajor) throws SQLException {
        trainingMajor.setId(resultSet.getInt("id"));
        trainingMajor.setActive(resultSet.getBoolean("active"));
        trainingMajor.setName(resultSet.getString("name"));
        trainingMajor.setLabel(resultSet.getString("label"));
        trainingMajor.setCreatedAt(resultSet.getDate("createdAt"));
        trainingMajor.setUpdatedAt(resultSet.getDate("updatedAt"));

         }

    @Override
    public boolean updateTrainingMajor(TrainingMajor trainingMajor) {
        boolean flag = false;

        try {
            String sql = "update TrainingMajor set active=?, name=?, label=?, updatedAt=? where id= ?";
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setBoolean(1, trainingMajor.isActive());
            preparedStmt.setString(2, trainingMajor.getName());
            preparedStmt.setString(3, trainingMajor.getLabel());
            preparedStmt.setDate(4, Date.valueOf(new Date(System.currentTimeMillis()).toString()));
            preparedStmt.setInt(5, trainingMajor.getId());
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
