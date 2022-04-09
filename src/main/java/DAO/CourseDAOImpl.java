package DAO;

import Enums.Qualification;
import Model.Course;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAOImpl implements CourseDAO{

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStmt;


    @Override
    public List<Course> getAllCourses() throws SQLException, ClassNotFoundException {
        List<Course> courseList = new ArrayList<>();

        String sql = "select * from Course";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Course course = new Course();
            course.setId(resultSet.getInt("id"));
            course.setName(resultSet.getString("name"));
            course.setDescription(resultSet.getString("description"));
            course.setQualification(Qualification.valueOf(resultSet.getString("qualification")));
            courseList.add(course);
        }
        DBUtil.closeConnection();
        return courseList;
    }

    @Override
    public boolean saveCourse(Course course) {
        boolean flag;
        try {

            String sql = "insert into Course(name, description, qualification) "
                    + "values(?,?,?)";
            try {
                connection = DBUtil.openConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1,course.getName());
            preparedStmt.setString(2, course.getDescription());
            preparedStmt.setString(3, course.getQualification().toString());

            preparedStmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }

    @Override
    public boolean deleteCourse(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM Course WHERE id=" + id;
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
    public Course getCourse(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from Course where id =" + id;
        Course course = null;
        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            course = new Course();
            course.setId(resultSet.getInt("id"));
            course.setName(resultSet.getString("name"));
            course.setDescription(resultSet.getString("description"));
            course.setQualification(Qualification.valueOf(resultSet.getString("qualification")));

        }
        DBUtil.closeConnection();
        return course;
    }

    @Override
    public boolean updateCourse(Course course) {
        boolean flag = false;

        try {
            String sql = "update Course set name=?, description=?, qualification=? where id= ?";
            connection = DBUtil.openConnection();
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1,course.getName());
            preparedStmt.setString(2, course.getDescription());
            preparedStmt.setString(3, String.valueOf(course.getQualification()));
            preparedStmt.setInt(4, course.getId());
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
