package Model;

import Enums.TrainingResidence;
import Enums.TrainingSponsor;

public class Nomination {
    private int id;
    private Training training;
    private TrainingProvider trainingProvider;
    private TrainingSponsor trainingSponsor;
    private TrainingResidence trainingResidence;
    private Staff nominee;

    public Nomination() {
    }

    public Nomination(int id, Training training, TrainingProvider trainingProvider, TrainingSponsor trainingSponsor, TrainingResidence trainingResidence, Staff nominee) {
        this.id = id;
        this.training = training;
        this.trainingProvider = trainingProvider;
        this.trainingSponsor = trainingSponsor;
        this.trainingResidence = trainingResidence;
        this.nominee = nominee;
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

    public TrainingProvider getTrainingProvider() {
        return trainingProvider;
    }

    public void setTrainingProvider(TrainingProvider trainingProvider) {
        this.trainingProvider = trainingProvider;
    }

    public TrainingSponsor getTrainingSponsor() {
        return trainingSponsor;
    }

    public void setTrainingSponsor(TrainingSponsor trainingSponsor) {
        this.trainingSponsor = trainingSponsor;
    }

    public TrainingResidence getTrainingResidence() {
        return trainingResidence;
    }

    public void setTrainingResidence(TrainingResidence trainingResidence) {
        this.trainingResidence = trainingResidence;
    }

    public Staff getNominee() {
        return nominee;
    }

    public void setNominee(Staff nominee) {
        this.nominee = nominee;
    }

    @Override
    public String toString() {
        return "Nomination{" +
                "id=" + id +
                ", training=" + training +
                ", trainingProvider=" + trainingProvider +
                ", trainingSponsor=" + trainingSponsor +
                ", trainingResidence=" + trainingResidence +
                ", nominees=" + nominee.toString() +
                '}';
    }
}
