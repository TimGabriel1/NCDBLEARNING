package DAO;

import Model.TrainingProvider;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingProviderDAOImpl implements  TrainingProviderDAO{
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStmt;

    @Override
    public List<TrainingProvider> getAllTrainingProviders() throws SQLException, ClassNotFoundException {
        List<TrainingProvider> trainingProviderList = new ArrayList<>();

        String sql = "select * from TrainingProvider ";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            TrainingProvider trainingProvider = new TrainingProvider();
            setTrainingProviderObject(trainingProvider);
            trainingProviderList.add(trainingProvider);
        }
        DBUtil.closeConnection();
        return trainingProviderList;
    }

    @Override
    public boolean saveTrainingProvider(TrainingProvider trainingProvider) {
        boolean flag;
        try {

            String sql = "insert into TrainingProvider(name, address, email, phoneNumber, website) "
                    + "values(?,?,?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1,trainingProvider.getName());
            preparedStmt.setString(2, trainingProvider.getAddress());
            preparedStmt.setString(3, trainingProvider.getEmail());
            preparedStmt.setString(4, trainingProvider.getPhoneNumber());
            preparedStmt.setString(5, trainingProvider.getWebsite());

            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }

    @Override
    public boolean deleteTrainingProvider(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM TrainingProvider WHERE id=" + id;
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
    public TrainingProvider getTrainingProvider(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from TrainingProvider where id =" + id;
        TrainingProvider trainingProvider;
        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        trainingProvider = new TrainingProvider();
        while (resultSet.next()) {

            setTrainingProviderObject(trainingProvider);

        }
        DBUtil.closeConnection();
        return trainingProvider;
    }

    private void setTrainingProviderObject(TrainingProvider trainingProvider) throws SQLException {
        trainingProvider.setId(resultSet.getInt("id"));
        trainingProvider.setName(resultSet.getString("name"));
        trainingProvider.setEmail(resultSet.getString("email"));
        trainingProvider.setAddress(resultSet.getString("address"));
        trainingProvider.setPhoneNumber(resultSet.getString("phoneNumber"));
        trainingProvider.setWebsite(resultSet.getString("website"));
    }

    @Override
    public boolean updateTrainingProvider(TrainingProvider trainingProvider) {
        boolean flag = false;

        try {
            String sql = "update TrainingProvider set name=?, address=?, email=?, phonenumber=?, website=?  where id= ?";
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1,trainingProvider.getName());
            preparedStmt.setString(2, trainingProvider.getAddress());
            preparedStmt.setString(3, trainingProvider.getEmail());
            preparedStmt.setString(4, trainingProvider.getPhoneNumber());
            preparedStmt.setString(5, trainingProvider.getWebsite());
            preparedStmt.setInt(6, trainingProvider.getId());
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
