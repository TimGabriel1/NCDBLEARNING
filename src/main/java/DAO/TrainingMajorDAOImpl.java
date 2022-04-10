package DAO;

import Enums.TrainingSponsor;
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
    private final TrainingProviderDAOImpl trainingProviderDAO;
    private final CourseDAOImpl courseDAO;

    public TrainingMajorDAOImpl() {
        this.trainingProviderDAO = new TrainingProviderDAOImpl();
        this.courseDAO = new CourseDAOImpl();
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

            String sql = "insert into TrainingMajor(startDate, endDate, trainingProvider, location, certificate, active, course, trainingSponsor) "
                    + "values(?,?,?,?,?,?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            prepareStatement(trainingMajor, sql);

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

    private void setTrainingMajorObject(TrainingMajor trainingMajor) throws SQLException, ClassNotFoundException {
        trainingMajor.setId(resultSet.getInt("id"));
        trainingMajor.setStartDate(resultSet.getDate("startDate"));
        trainingMajor.setEndDate(resultSet.getDate("endDate"));
        trainingMajor.setTrainingProvider(trainingProviderDAO.getTrainingProvider(resultSet.getInt("trainingProvider")));
        trainingMajor.setLocation(resultSet.getString("location"));
        trainingMajor.setCertificate(resultSet.getString("certificate"));
        trainingMajor.setActive(resultSet.getBoolean("active"));
        trainingMajor.setTrainingCourse(courseDAO.getCourse(resultSet.getInt("course")));
        trainingMajor.setTrainingSponsor(TrainingSponsor.valueOf(resultSet.getString("trainingSponsor")));
    }

    @Override
    public boolean updateTrainingMajor(TrainingMajor trainingMajor) {
        boolean flag = false;

        try {
            String sql = "update TrainingMajor set startDate=?, endDate=?, trainingProvider=?, location=?, certificate=?, active=?, course=?, trainingSponsor=? where id= ?";
            connection = DBUtil.openConnection();
            prepareStatement(trainingMajor, sql);
            preparedStmt.setInt(9, trainingMajor.getId());
            preparedStmt.executeUpdate();
            flag = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    private void prepareStatement(TrainingMajor trainingMajor, String sql) throws SQLException {
        preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setDate(1,trainingMajor.getStartDate());
        preparedStmt.setDate(2, trainingMajor.getEndDate());
        preparedStmt.setInt(3, trainingMajor.getTrainingProvider().getId());
        preparedStmt.setString(4, trainingMajor.getLocation());
        preparedStmt.setString(5, trainingMajor.getCertificate());
        preparedStmt.setBoolean(6, trainingMajor.isActive());
        preparedStmt.setInt(7, trainingMajor.getCourse().getId());
        preparedStmt.setString(8, String.valueOf(trainingMajor.getTrainingSponsor()));
    }
}
