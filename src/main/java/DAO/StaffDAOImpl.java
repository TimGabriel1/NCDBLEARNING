package DAO;

import Enums.StaffType;
import Model.Staff;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffDAOImpl implements StaffDAO{
    Connection connection = null;
    private PreparedStatement preparedStmt;
    private ResultSet resultSet;

    @Override
    public List<Staff> getAllStaff() throws SQLException, ClassNotFoundException {
        List<Staff> staffList = new ArrayList<Staff>();

        String sql = "select * from Staff ";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Staff staff = new Staff();
            staff.setId(resultSet.getInt("id"));
            staff.setFirstName(resultSet.getString("firstName"));
            staff.setMiddleName(resultSet.getString("middleName"));
            staff.setSurnName(resultSet.getString("surnName"));
            staff.setStaffNo(resultSet.getString("staffNo"));

            staff.setActive(resultSet.getBoolean("isActive"));
            staffList.add(staff);


        }
        DBUtil.closeConnection();
        return staffList;
    }

    @Override
    public boolean saveStaff(Staff staff) {

        boolean flag = false;
        try {

            String sql = "insert into Staff( firstName, middleName,surnName, staffNo, department, location, gradeLevel, type, status, roles) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, staff.getFirstName());
            preparedStmt.setString(2, staff.getMiddleName());
            preparedStmt.setString(3, staff.getSurnName());
            preparedStmt.setString(4, staff.getStaffNo());
            preparedStmt.setInt(5, staff.getDepartment().getId());
            preparedStmt.setInt(6, staff.getLocation().getId());
            preparedStmt.setInt(7, staff.getGradeLevel().getId());
            preparedStmt.setString(8, staff.getType().toString());
            preparedStmt.setString(9, staff.getStatus().toString());
            preparedStmt.setString(10, staff.getRoles().toString());
            preparedStmt.setBoolean(11, staff.isActive());
            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }

    @Override
    public boolean deleteStaff(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM Staff WHERE id=" + id;
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
    public Staff getStaff(int id) throws SQLException, ClassNotFoundException {


        String sql = "select * from Staff where id="+ id;

        connection = DBUtil.openConnection();
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        Staff staff = null;

        while (resultSet.next()) {
           staff = new Staff();
            staff.setId(resultSet.getInt("id"));
            staff.setFirstName(resultSet.getString("firstName"));
            staff.setMiddleName(resultSet.getString("middleName"));
            staff.setSurnName(resultSet.getString("surnName"));
            staff.setStaffNo(resultSet.getString("staffNo"));
            staff.setType(StaffType.valueOf(resultSet.getString("type")));
            staff.setActive(resultSet.getBoolean("isActive"));


        }
        DBUtil.closeConnection();
        return staff;
    }

    @Override
    public boolean updateStaff(Staff staff) {
        boolean flag = false;

        try {
            String sql = "update Staff set firstName=?, middleName=?, surnName=?, staffNo=?, department=?, location=?, gradeLevel=?, type=?, status=?, roles=?, isActive=?  where id= ?";
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, staff.getFirstName());
            preparedStmt.setString(2, staff.getMiddleName());
            preparedStmt.setString(3, staff.getSurnName());
            preparedStmt.setString(4, staff.getStaffNo());
            preparedStmt.setInt(5, staff.getDepartment().getId());
            preparedStmt.setInt(6, staff.getLocation().getId());
            preparedStmt.setInt(7, staff.getGradeLevel().getId());
            preparedStmt.setString(8, staff.getType().toString());
            preparedStmt.setString(9, staff.getStatus().toString());
            preparedStmt.setString(10, staff.getRoles().toString());
            preparedStmt.setBoolean(11, staff.isActive());



            preparedStmt.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception e){
            System.out.println(e);
        }
        return flag;
    }

    @Override
    public boolean deactivateStaff(int id) {
        boolean flag = false;

        try {
            String sql = "update Staff set status= 0 where id=" + id;
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
