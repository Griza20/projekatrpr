package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.exceptions.OrderException;
import java.util.List;

/**
 * Dao interface for Jela domain bean
 * @author Amar Grizovic
 */
public interface JelaDao extends Dao<Jela>{
    /**
     * Gets all meals from a restaurant whose id is given as parameter
     * @param idRestorana unique id of a restaurant
     * @return List of all meals from a restaurant
     */
    List<Jela> getAllMealsFromRestaurant(int idRestorana) throws OrderException;
}
