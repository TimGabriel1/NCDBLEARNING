package Model;

import Enums.TrainingResidence;
import Enums.TrainingSponsor;
import Util.ServletUtil;

import java.util.List;

public class Nomination {
    private int id;
    private String nominationId;
    private Training training;
    private TrainingProvider trainingProvider;
    private TrainingSponsor trainingSponsor; // Enum
    private TrainingResidence trainingResidence; //Enum
    private List<Staff> nominees;

    public Nomination() {
    }

    public Nomination(int id, Training training, TrainingProvider trainingProvider, TrainingSponsor trainingSponsor, TrainingResidence trainingResidence, List<Staff> nominees) {
        this.id = id;
        this.training = training;
        this.trainingProvider = trainingProvider;
        this.trainingSponsor = trainingSponsor;
        this.trainingResidence = trainingResidence;
        this.nominees = nominees;
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

    public List<Staff> getNominee() {
        return nominees;
    }

    public void setNominee(List<Staff> nominees) {
        this.nominees = nominees;
    }

    public String getNominationId() {
        return nominationId;
    }

    public void setNominationId() {
        this.nominationId = ServletUtil.generateUUID();
    }

    public List<Staff> getNominees() {
        return nominees;
    }

    public void setNominees(List<Staff> nominees) {
        this.nominees = nominees;
    }

    @Override
    public String toString() {
        return "Nomination{" +
                "id=" + id +
                ", training=" + training +
                ", trainingProvider=" + trainingProvider +
                ", trainingSponsor=" + trainingSponsor +
                ", trainingResidence=" + trainingResidence +
                ", nomineess=" + nominees.toString() +
                '}';
    }
}
