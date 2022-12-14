package ba.unsa.etf.rpr.dao;

import java.util.List;

/**
 * Dao interface for all DAO interfaces and classes
 * @param <Tip> type of item
 * @author Amar Grizovic
 */
public interface Dao<Tip> {
    /**
     * Get entity from database base by id
     * @param id primary key of entity
     * @return Entity from database
     */
    Tip getById(int id);
}
