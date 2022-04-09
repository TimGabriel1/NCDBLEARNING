package DAO;

import Model.Nomination;

import java.sql.SQLException;
import java.util.List;

public interface NominationDAO {
    List<Nomination> getAllNominations() throws SQLException, ClassNotFoundException;
    boolean saveNomination(Nomination nomination);
    boolean deleteNomination(int id);
    Nomination getNomination(int id) throws SQLException, ClassNotFoundException;
    boolean updateNomination(Nomination nomination);
}
