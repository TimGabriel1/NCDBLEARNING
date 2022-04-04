package Model;

import java.util.Date;

public class Training {
    private int id;
    private String Title;
    private TrainingMajor trainingMajor;

    public Training() {
    }

    public Training(int id, String title, TrainingMajor trainingMajor) {
        this.id = id;
        Title = title;
        this.trainingMajor = trainingMajor;
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

    public TrainingMajor getTrainingMajor() {
        return trainingMajor;
    }

    public void setTrainingMajor(TrainingMajor trainingMajor) {
        this.trainingMajor = trainingMajor;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", trainingMajor=" + trainingMajor +
                '}';
    }
}
