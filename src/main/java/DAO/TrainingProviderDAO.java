package DAO;

import Model.TrainingProvider;

import java.sql.SQLException;
import java.util.List;

public interface TrainingProviderDAO {
    List<TrainingProvider> getAllTrainingProviders() throws SQLException, ClassNotFoundException;
    boolean saveTrainingProvider(TrainingProvider trainingProvider);
    boolean deleteTrainingProvider(int id);
    TrainingProvider getTrainingProvider(int id) throws SQLException, ClassNotFoundException;
    boolean updateTrainingProvider(TrainingProvider trainingProvider);
}
