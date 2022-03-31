package DAO;

import Model.Staff;

import java.sql.SQLException;
import java.util.List;

public interface StaffDAO {
   List<Staff> getAllStaff() throws SQLException, ClassNotFoundException;
   boolean saveStaff(Staff staff);
   boolean deleteStaff(int id);
   Staff getStaff(int id) throws SQLException, ClassNotFoundException;
   boolean updateStaff(Staff staff);
   boolean deactivateStaff(int id);

}
