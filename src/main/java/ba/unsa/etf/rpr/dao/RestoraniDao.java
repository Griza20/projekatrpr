package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;

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
    List<Restorani> searchByManagerName(String vlasnik) throws OrderException;

    /**
     * Searches for all restaurants that are nearby
     * @param lokacija location for searching
     * @return List of all restaurants that are nearby
     */
    List<Restorani> searchByLocation(String lokacija) throws OrderException;

    /**
     * Checks if the given owner has 2 or more restaurants
     * @param ime name of owner
     * @param restorani list of restaurants
     * @return true if has 2 or more, else false
     */
    boolean visestrukiVlasnici(String ime, List<Restorani> restorani);

    /**
     * Checks if the given location has 2 or more restaurants
     * @param lokacija location
     * @param restorani list of restaurants
     * @return true if has 2 or more, else false
     */
    boolean sirokSpektarRestorana(String lokacija, List<Restorani> restorani);
}
