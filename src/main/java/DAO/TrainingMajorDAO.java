package DAO;

import Model.TrainingMajor;

import java.sql.SQLException;
import java.util.List;

public interface TrainingMajorDAO {
    List<TrainingMajor> getAllTrainingMajors() throws SQLException, ClassNotFoundException;
    boolean saveTrainingMajor(TrainingMajor trainingMajor);
    boolean deleteTrainingMajor(int id);
    TrainingMajor getTrainingMajor(int id) throws SQLException, ClassNotFoundException;
    boolean updateTrainingMajor(TrainingMajor trainingMajor);

}
