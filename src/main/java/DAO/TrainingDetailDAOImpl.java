package DAO;

import Enums.*;
import Model.TrainingDetail;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingDetailDAOImpl implements TrainingDetailsDAO{

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStmt;

    @Override
    public List<TrainingDetail> getAllTrainingDetails() throws SQLException, ClassNotFoundException {

        List<TrainingDetail> trainingDetailList = new ArrayList<>();

        String sql = "select * from TrainingDetail ";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            TrainingDetail trainingDetail = new TrainingDetail();
            setTrainingDetailObject(trainingDetail);
            trainingDetailList.add(trainingDetail);
        }
        DBUtil.closeConnection();
        return trainingDetailList;
    }

    @Override
    public boolean saveTrainingDetail(TrainingDetail trainingDetail) {
        boolean flag;
        try {

            String sql = "insert into TrainingDetail(training, qualification, course, provider, residence, location, startDate, endDate, sponsor, certificate, status, action, completed) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            prepareStatement(trainingDetail, sql);
            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }

    @Override
    public boolean deleteTrainingDetail(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM TrainingDetail WHERE id=" + id;
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
    public TrainingDetail getTrainingDetail(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from TrainingDetail where id =" + id;
        TrainingDetail trainingDetail;
        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        trainingDetail = new TrainingDetail();
        while (resultSet.next()) {

            setTrainingDetailObject(trainingDetail);


        }
        DBUtil.closeConnection();
        return trainingDetail;
    }

    private void setTrainingDetailObject(TrainingDetail trainingDetail) throws SQLException, ClassNotFoundException {
        trainingDetail.setId(resultSet.getInt("id"));
        trainingDetail.setTraining(new TrainingDAOImpl().getTraining(resultSet.getInt("training")));
        trainingDetail.setQualification(Qualification.valueOf(resultSet.getString("qualification")));
        trainingDetail.setCourse(new CourseDAOImpl().getCourse(resultSet.getInt("course")));
        trainingDetail.setProvider(new TrainingProviderDAOImpl().getTrainingProvider(resultSet.getInt("provider")));
        trainingDetail.setResidence(TrainingResidence.valueOf(resultSet.getString("residence")));
        trainingDetail.setLocation(new LocationDAOImpl().getLocation(resultSet.getInt("location")));
        trainingDetail.setStartDate(resultSet.getDate("startDate"));
        trainingDetail.setEndDate(resultSet.getDate("endDate"));
        trainingDetail.setSponsor(TrainingSponsor.valueOf(resultSet.getString("sponsor")));
        trainingDetail.setCertificate(resultSet.getString("certificate"));
        trainingDetail.setStatus(TrainingStatus.valueOf(resultSet.getString("status")));
        trainingDetail.setAction(TrainingAction.valueOf(resultSet.getString("action")));
        trainingDetail.setCompleted(resultSet.getBoolean("completed"));
    }

    @Override
    public boolean updateTrainingDetail(TrainingDetail trainingDetail) {
        boolean flag = false;

        try {
            String sql = "update TrainingDetail set training=?, qualification=?, course=?, provider=?, residence=?, location=?, startDate=?, endDate=?, sponsor=?, certificate=?, status=?, action=?, completed=?  where id= ?";
            connection = DBUtil.openConnection();
            prepareStatement(trainingDetail, sql);
            preparedStmt.setInt(14, trainingDetail.getId());
            preparedStmt.executeUpdate();
            flag = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    private void prepareStatement(TrainingDetail trainingDetail, String sql) throws SQLException {
        preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setInt(1, trainingDetail.getTraining().getId());
        preparedStmt.setString(2, String.valueOf(trainingDetail.getQualification()));
        preparedStmt.setInt(3, trainingDetail.getCourse().getId());
        preparedStmt.setInt(4, trainingDetail.getProvider().getId() );
        preparedStmt.setString(5, String.valueOf(trainingDetail.getResidence()));
        preparedStmt.setInt(6, trainingDetail.getLocation().getId());
        preparedStmt.setDate(7, trainingDetail.getStartDate());
        preparedStmt.setDate(8, trainingDetail.getEndDate());
        preparedStmt.setString(9, String.valueOf(trainingDetail.getSponsor()));
        preparedStmt.setString(10, trainingDetail.getCertificate());
        preparedStmt.setString(11, String.valueOf(trainingDetail.getStatus()));
        preparedStmt.setString(12, String.valueOf(trainingDetail.getAction()));
        preparedStmt.setBoolean(13, trainingDetail.isCompleted());
    }

}
