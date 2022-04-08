package DAO;


import Model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    List<Course> getAllCourses() throws SQLException, ClassNotFoundException;
    boolean saveCourse(Course course);
    boolean deleteCourse(int id);
   Course getCourse(int id) throws SQLException, ClassNotFoundException;
    boolean updateCourse(Course course);
}
