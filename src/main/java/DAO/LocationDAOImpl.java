package DAO;

import Model.Location;
import Model.Location;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationDAOImpl implements LocationDAO{
    private Connection connection;
    private ResultSet resultSet;

    @Override
    public List<Location> getAllLocation() throws SQLException, ClassNotFoundException {

        List<Location> locationList = new ArrayList<>();

        String sql = "select * from Location ";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Location location = new Location();
            location.setId(resultSet.getInt("id"));
            location.setName(resultSet.getString("name"));
            location.setLabel(resultSet.getString("label"));
            location.setAddress(resultSet.getString("address"));
            locationList.add(location);
        }

        DBUtil.closeConnection();
//        System.out.println(locationList.toString());
        return locationList;
    }

    @Override
    public boolean saveLocation(Location location) {
        return false;
    }

    @Override
    public boolean deleteLocation(int id) {
        return false;
    }

    @Override
    public Location getLocation(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateLocation(Location location) {
        return false;
    }
}
