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

    /**
     * Saves entity into database
     * @param item data that will be saved in database
     * @return saved item
     */
    Tip add(Tip item);

    /**
     * Updates already existing entity in database
     * @param item item that will be updated in database
     * @return updated item
     */
    Tip update(Tip item);

    /**
     * Deletes entity from database with given id
     * @param id primary key of entity
     */
    void delete(int id);
}
