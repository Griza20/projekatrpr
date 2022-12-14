package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Restorani;

import java.util.List;

/**
 * Dao interface for Restorani domain bean
 * @author Amar Grizovic
 */
public interface RestoraniDao extends Dao<Restorani> {
    /**
     * Searches for all restaurants that have the same manager
     * @param vlasnik first and last name of manager
     * @return List of all restaurants where given parameter is a manager
     */
    List<Restorani> searchByManagerName(String vlasnik);

    /**
     * Searches for all restaurants that are nearby
     * @param lokacija location for searching
     * @return List of all restaurants that are nearby
     */
    List<Restorani> searchByLocation(String lokacija);
}
