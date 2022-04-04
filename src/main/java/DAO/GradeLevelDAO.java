package DAO;

import Model.GradeLevel;


import java.sql.SQLException;
import java.util.List;

public interface GradeLevelDAO {
    List<GradeLevel> getAllGradeLevels() throws SQLException, ClassNotFoundException;
    boolean saveGradeLevel(GradeLevel gradeLevel);
    boolean deleteGradeLevel(int id);
    GradeLevel getGradeLevel(int id) throws SQLException, ClassNotFoundException;
    boolean updateGradeLevel(GradeLevel gradeLevel);

}
