package Model;

import Enums.NotificationStatus;
import Enums.NotificationType;
import java.sql.Timestamp;

public class Notification {
    private int id;
    private String title;
    private String description;
    private NotificationType notificationType;
    private Timestamp timestamp_generated;
    private NotificationStatus notificationStatus;
    private Staff recipient;
    private Timestamp timestamp_read;

    public Notification() {
    }

    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Timestamp getTimestamp_generated() {
        return timestamp_generated;
    }

    public void setTimestamp_generated(Timestamp timestamp_generated) {
        this.timestamp_generated = timestamp_generated;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public Staff getRecipient() {
        return recipient;
    }

    public void setRecipient(Staff recipient) {
        this.recipient = recipient;
    }

    public Timestamp getTimestamp_read() {
        return timestamp_read;
    }

    public void setTimestamp_read(Timestamp timestamp_read) {
        this.timestamp_read = timestamp_read;
    }
}