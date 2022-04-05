package Model;

import Enums.TrainingQualification;

public class TrainingCourse {
    private int id;
    private String name;
    private String Description;
    private TrainingQualification trainingQualification;

    public TrainingCourse() {
    }

    public TrainingCourse(int id, String name, String description, TrainingQualification trainingQualification) {
        this.id = id;
        this.name = name;
        Description = description;
        this.trainingQualification = trainingQualification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public TrainingQualification getTrainingQualification() {
        return trainingQualification;
    }

    public void setTrainingQualification(TrainingQualification trainingQualification) {
        this.trainingQualification = trainingQualification;
    }

    @Override
    public String toString() {
        return "TrainingCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Description='" + Description + '\'' +
                ", trainingQualification=" + trainingQualification +
                '}';
    }
}
