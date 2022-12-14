package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Narudzbe;

import java.util.List;

/**
 * Dao interface for Narudzbe domain bean
 * @author Amar Grizovic
 */
public interface NarudzbeDao extends Dao<Narudzbe>{
    /**
     * Shows the orders that should be delivered first
     * @return List of all orders ordered by time ascending
     */
    List<Narudzbe> getByTimeOrdered();
}
