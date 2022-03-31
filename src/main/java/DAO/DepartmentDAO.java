package DAO;

import Model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
   List<Department> getAllDepartment() throws SQLException, ClassNotFoundException;
   boolean saveDepartment(Department staff);
   boolean deleteDepartment(int id);
   Department getDepartment(int id) throws SQLException, ClassNotFoundException;
   boolean updateDepartment(Department staff);


}
