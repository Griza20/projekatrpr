package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Dostavljaci;

import java.util.Date;
import java.util.List;

/**
 * Dao interface for Dostavljaci domain bean
 * @author Amar Grizovic
 */
public interface DostavljaciDao extends Dao<Dostavljaci>{
    /**
     * Searches for all deliverers that have birthday on same day
     * @param datumRodjenja date of birthday
     * @return List of all deliverers that have birthday on same day
     */
    List<Dostavljaci> searchByBirthDate(Date datumRodjenja);
}