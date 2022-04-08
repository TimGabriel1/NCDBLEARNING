package DAO;

import Enums.DepartmentType;
import Model.Department;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartmentDAOImpl implements DepartmentDAO{

    private PreparedStatement preparedStmt;
    private ResultSet resultSet;
    private Connection connection;

    @Override
    public List<Department> getAllDepartment() throws SQLException, ClassNotFoundException {
        List<Department> departmentList = new ArrayList<>();
        Connection connection;
        String sql = "select * from Department ";
        connection = DBUtil.openConnection();
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Department department = new Department();
            createDepartmentObject(department);
            departmentList.add(department);

        }
        DBUtil.closeConnection();

        return departmentList;
    }

    private void createDepartmentObject(Department department) throws SQLException {
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));
        department.setLabel(resultSet.getString("label"));
        department.setCode(resultSet.getString("code"));
        department.setParent(resultSet.getInt("parent"));
        department.setType(DepartmentType.valueOf(resultSet.getString("type")));
    }

    @Override
    public boolean saveDepartment(Department department) {
        boolean flag = false;
        try {

            String sql = "insert into Department( name, label, code, parent, type) "
                    + "values(?,?,?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DepartmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, department.getName());
            preparedStmt.setString(2, department.getLabel());
            preparedStmt.setString(3, department.getCode());
            preparedStmt.setInt(4, department.getParent());
            preparedStmt.setString(5, String.valueOf(department.getType()));

            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return flag;
    }

    @Override
    public boolean deleteDepartment(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM Department WHERE id=" + id;
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBUtil.closeConnection();
        return flag;
    }

    @Override
    public Department getDepartment(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from Department where id="+ id;
        connection = DBUtil.openConnection();
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        Department department = null;
        while (resultSet.next()) {
            department = new Department();
            createDepartmentObject(department);


        }
        DBUtil.closeConnection();

        return department;
    }

    @Override
    public boolean updateDepartment(Department department) {
        boolean flag = false;

        try {
            String sql = "update Department set name=?, label=?, code=?, parent=?, type=?  where id= ?";
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, department.getName());
            preparedStmt.setString(2, department.getLabel());
            preparedStmt.setString(3, department.getCode());
            preparedStmt.setString(4, String.valueOf(department.getParent()));
            preparedStmt.setString(5, department.getType().toString());
            preparedStmt.setInt(6, department.getId());
            preparedStmt.executeUpdate();
            flag = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }


}
