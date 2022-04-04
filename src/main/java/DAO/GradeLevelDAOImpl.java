package DAO;


import Model.GradeLevel;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GradeLevelDAOImpl implements GradeLevelDAO {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStmt;


    @Override
    public List<GradeLevel> getAllGradeLevels() throws SQLException, ClassNotFoundException {

        List<GradeLevel> gradeLevelList = new ArrayList<>();

        String sql = "select * from GradeLevel ";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            GradeLevel gradeLevel = new GradeLevel();
            gradeLevel.setId(resultSet.getInt("id"));
            gradeLevel.setName(resultSet.getString("name"));
            gradeLevel.setLabel(resultSet.getString("label"));
            gradeLevel.setKey(resultSet.getString("key"));
            gradeLevelList.add(gradeLevel);
        }

            DBUtil.closeConnection();
            return gradeLevelList;
        }

    @Override
    public boolean saveGradeLevel(GradeLevel gradeLevel) {

        boolean flag;
        try {

            String sql = "insert into GradeLevel(name, label, `key`) "
                    + "values(?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GradeLevelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, gradeLevel.getName());
            preparedStmt.setString(2, gradeLevel.getLabel());
            preparedStmt.setString(3, gradeLevel.getKey());

            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean deleteGradeLevel(int id) {
        boolean flag = false;

        try{
            String sql =  "DELETE FROM gradeLevel WHERE id="+ id;
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.executeUpdate();
            flag = true;
        }catch(SQLException ex){
            ex.printStackTrace();
            flag = false;
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        DBUtil.closeConnection();
        return flag;
    }

    @Override
    public GradeLevel getGradeLevel(int id) throws SQLException, ClassNotFoundException {

        String sql = "select * from GradeLevel where id="+ id;

        connection = DBUtil.openConnection();
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        GradeLevel gradeLevel = null;

        while (resultSet.next()) {
            gradeLevel = new GradeLevel();
            gradeLevel.setId(resultSet.getInt("id"));
            gradeLevel.setName(resultSet.getString("name"));
            gradeLevel.setKey(resultSet.getString("key"));
            gradeLevel.setLabel(resultSet.getString("label"));


        }
        DBUtil.closeConnection();
        return gradeLevel;
    }

    @Override
    public boolean updateGradeLevel(GradeLevel gradeLevel) {
        boolean flag = false;

        try {
            String sql = "update GradeLevel set name=?, label=?, `key`=? where id= ?";
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, gradeLevel.getName());
            preparedStmt.setString(2, gradeLevel.getLabel());
            preparedStmt.setString(3, gradeLevel.getKey());
             preparedStmt.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GradeLevelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception e){
            System.out.println(e);
        }
        return flag;
    }

}
