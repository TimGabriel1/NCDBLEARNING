package Model;

import Enums.TrainingResidence;
import Enums.TrainingSponsor;

import java.util.ArrayList;

public class Nomination {
    private Training training;
    private TrainingProvider trainingProvider;
    private TrainingSponsor trainingSponsor;
    private TrainingResidence trainingResidence;
    private ArrayList<Staff> nominees;

    public Nomination() {
    }

    public Nomination(Training training, TrainingProvider trainingProvider, TrainingSponsor trainingSponsor, TrainingResidence trainingResidence, ArrayList<Staff> nominees) {
        this.training = training;
        this.trainingProvider = trainingProvider;
        this.trainingSponsor = trainingSponsor;
        this.trainingResidence = trainingResidence;
        this.nominees = nominees;
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

    public ArrayList<Staff> getNominees() {
        return nominees;
    }

    public void setNominees(ArrayList<Staff> nominees) {
        this.nominees = nominees;
    }

    @Override
    public String toString() {
        return "Nomination{" +
                "training=" + training +
                ", trainingProvider=" + trainingProvider +
                ", trainingSponsor=" + trainingSponsor +
                ", trainingResidence=" + trainingResidence +
                ", nominees=" + nominees +
                '}';
    }
}
