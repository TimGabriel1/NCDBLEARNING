package DAO;

import Model.Notification;

import java.sql.SQLException;
import java.util.List;

public interface NotificationDAO {
    List<Notification> getAllNotification() throws SQLException, ClassNotFoundException;
    boolean createNotification(Notification notification);
    boolean deleteNotification(int id);
    Notification getNotification(int id) throws SQLException, ClassNotFoundException;
    boolean readNotification(int id);
    boolean archiveNotification(int id);

}
