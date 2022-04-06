package DAO;

import Model.Location;

import java.sql.SQLException;
import java.util.List;

public interface LocationDAO {
    List<Location> getAllLocation() throws SQLException, ClassNotFoundException;
    boolean saveLocation(Location location);
    boolean deleteLocation(int id);
    Location getLocation(int id) throws SQLException, ClassNotFoundException;
    boolean updateLocation(Location location);
}
