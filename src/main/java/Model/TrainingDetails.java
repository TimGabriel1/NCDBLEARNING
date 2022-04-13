package Model;

import Enums.*;

import java.sql.Date;

public class TrainingDetails {
    private int id;
    private Training training;
    private Qualification qualification;
    private Course course;
    private TrainingProvider provider;
    private TrainingResidence residence;
    private Location Location;
    private Date startDate;
    private Date endDate;
    private TrainingSponsor sponsor;
    private String certificate;
    private TrainingStatus status;
    private TrainingAction action;
    private boolean completed;

    public TrainingDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public TrainingProvider getProvider() {
        return provider;
    }

    public void setProvider(TrainingProvider provider) {
        this.provider = provider;
    }

    public TrainingResidence getResidence() {
        return residence;
    }

    public void setResidence(TrainingResidence residence) {
        this.residence = residence;
    }

    public Model.Location getLocation() {
        return Location;
    }

    public void setLocation(Model.Location location) {
        Location = location;
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

    public TrainingSponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(TrainingSponsor sponsor) {
        this.sponsor = sponsor;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public TrainingStatus getStatus() {
        return status;
    }

    public void setStatus(TrainingStatus status) {
        this.status = status;
    }

    public TrainingAction getAction() {
        return action;
    }

    public void setAction(TrainingAction action) {
        this.action = action;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TrainingDetails{" +
                "id=" + id +
                ", training=" + training +
                ", qualification=" + qualification +
                ", course=" + course +
                ", provider=" + provider +
                ", residence=" + residence +
                ", Location=" + Location +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", sponsor=" + sponsor +
                ", certificate='" + certificate + '\'' +
                ", status=" + status +
                ", action=" + action +
                ", completed=" + completed +
                '}';
    }
}

