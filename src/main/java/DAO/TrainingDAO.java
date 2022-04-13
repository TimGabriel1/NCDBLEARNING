package DAO;

import Model.Training;

import java.sql.SQLException;
import java.util.List;

public interface TrainingDAO {
    List<Training> getAllTrainings() throws SQLException, ClassNotFoundException;
    boolean saveTraining(Training training);
    boolean deleteTraining(int id);
    Training getTraining(int id) throws SQLException, ClassNotFoundException;
    boolean updateTraining(Training training);

}
