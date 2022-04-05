package Model;

import Enums.NotificationStatus;
import Enums.NotificationType;

public class Notification {
    private String id;
    private String title;
    private String description;
    private NotificationType notificationType;
    private NotificationStatus notificationStatus;

    public Notification() {
    }

    public Notification(String id, String title, String description, NotificationType notificationType, NotificationStatus notificationStatus) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.notificationType = notificationType;
        this.notificationStatus = notificationStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }
}
