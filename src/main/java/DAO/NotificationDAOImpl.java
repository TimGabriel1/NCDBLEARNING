package DAO;

import Model.Notification;
import Model.Notification;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOImpl implements NotificationDAO{

    private Connection connection;
    private ResultSet resultSet;

    @Override
    public List<Notification> getAllNotification() throws SQLException, ClassNotFoundException {

        List<Notification> notificationList = new ArrayList<>();

        String sql = "select * from Notification ";

        connection = DBUtil.openConnection();


        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Notification notification = new Notification();
            notification.setId(resultSet.getInt("id"));
//            notification.setName(resultSet.getString("name"));
//            notification.setLabel(resultSet.getString("label"));
//            notification.setAddress(resultSet.getString("address"));
            notificationList.add(notification);
        }
        DBUtil.closeConnection();
        return notificationList;
    }

    @Override
    public boolean createNotification(Notification notification) {
        return false;
    }

    @Override
    public boolean deleteNotification(int id) {
        return false;
    }

    @Override
    public Notification getNotification(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean readNotification(int id) {
        return false;
    }

    @Override
    public boolean archiveNotification(int id) {
        return false;
    }
}
