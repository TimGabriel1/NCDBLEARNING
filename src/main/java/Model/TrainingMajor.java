package Model;

import Enums.TrainingSponsor;

import java.sql.Date;

public class TrainingMajor {
    private int id;
    private Date startDate;
    private Date endDate;
    private TrainingProvider trainingProvider;
    private String location;
    private String certificate;
    private boolean active;
    private Course course;
    private TrainingSponsor trainingSponsor;

    public TrainingMajor() {
    }

    public TrainingMajor(int id, Date startDate, Date endDate, TrainingProvider trainingProvider, String location, String certificate, boolean active, Course course, TrainingSponsor trainingSponsor) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainingProvider = trainingProvider;
        this.location = location;
        this.certificate = certificate;
        this.active = active;
        this.course = course;
        this.trainingSponsor = trainingSponsor;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TrainingProvider getTrainingProvider() {
        return trainingProvider;
    }

    public void setTrainingProvider(TrainingProvider trainingProvider) {
        this.trainingProvider = trainingProvider;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Course getTrainingCourse() {
        return course;
    }

    public void setTrainingCourse(Course course) {
        this.course = course;
    }

    public TrainingSponsor getTrainingSponsor() {
        return trainingSponsor;
    }

    public void setTrainingSponsor(TrainingSponsor trainingSponsor) {
        this.trainingSponsor = trainingSponsor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "TrainingMajor{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", trainingProvider=" + trainingProvider +
                ", location='" + location + '\'' +
                ", certificate='" + certificate + '\'' +
                ", active=" + active +
                ", course=" + course +
                ", trainingSponsor=" + trainingSponsor +
                '}';
    }
}
