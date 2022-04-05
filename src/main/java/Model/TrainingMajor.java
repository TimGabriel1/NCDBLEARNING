package Model;

import Enums.TrainingSponsor;

import java.util.Date;

public class TrainingMajor {
    private Date startDate;
    private Date endDate;
    private TrainingProvider trainingProvider;
    private String location;
    private String certificate;
    private boolean active;
    private TrainingCourse trainingCourse;
    private TrainingSponsor trainingSponsor;

    public TrainingMajor() {
    }

    public TrainingMajor(Date startDate, Date endDate, TrainingProvider trainingProvider, String location, String certificate, boolean active, TrainingCourse trainingCourse, TrainingSponsor trainingSponsor) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainingProvider = trainingProvider;
        this.location = location;
        this.certificate = certificate;
        this.active = active;
        this.trainingCourse = trainingCourse;
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

    public TrainingCourse getTrainingCourse() {
        return trainingCourse;
    }

    public void setTrainingCourse(TrainingCourse trainingCourse) {
        this.trainingCourse = trainingCourse;
    }

    public TrainingSponsor getTrainingSponsor() {
        return trainingSponsor;
    }

    public void setTrainingSponsor(TrainingSponsor trainingSponsor) {
        this.trainingSponsor = trainingSponsor;
    }

    @Override
    public String toString() {
        return "TrainingMajor{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", trainingProvider=" + trainingProvider +
                ", location='" + location + '\'' +
                ", certificate='" + certificate + '\'' +
                ", active=" + active +
                ", trainingCourse=" + trainingCourse +
                ", trainingSponsor=" + trainingSponsor +
                '}';
    }
}
