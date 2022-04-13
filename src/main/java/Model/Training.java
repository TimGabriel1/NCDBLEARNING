package Model;

import Enums.Qualification;
import Enums.TrainingResidence;
import Enums.TrainingSponsor;

import java.sql.Date;

public class Training {
    private int id;
    private String Title;
    private String label;
    private TrainingMajor major;
    private Date createdAt;
    private Date updatedAt;



    public Training() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TrainingMajor getMajor() {
        return major;
    }

    public void setMajor(TrainingMajor major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", label='" + label + '\'' +
                ", major=" + major +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
