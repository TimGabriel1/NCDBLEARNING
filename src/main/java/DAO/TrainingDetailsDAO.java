package DAO;


import Model.TrainingDetail;

import java.sql.SQLException;
import java.util.List;

public interface TrainingDetailsDAO {
    List<TrainingDetail> getAllTrainingDetails() throws SQLException, ClassNotFoundException;
    boolean saveTrainingDetail(TrainingDetail trainingDetail);
    boolean deleteTrainingDetail(int id);
    TrainingDetail getTrainingDetail(int id) throws SQLException, ClassNotFoundException;
    boolean updateTrainingDetail(TrainingDetail trainingDetail);
}
