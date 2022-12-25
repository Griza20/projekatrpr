package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.util.List;

/**
 * Business Logic Layer for Restorani
 * @author Amar Grizovic
 */
public class RestoraniManager {
    public Restorani getById(int idRestorana) throws OrderException {
        return DaoFactory.restoraniDao().getById(idRestorana);
    }

    public Restorani add(Restorani r) throws OrderException{
        return DaoFactory.restoraniDao().add(r);
    }

    public void update(Restorani r) throws OrderException{
        DaoFactory.restoraniDao().update(r);
    }

    public void delete(int idRestorana) throws OrderException{
        DaoFactory.restoraniDao().delete(idRestorana);
    }

    public List<Restorani> getAll() throws OrderException {
        return DaoFactory.restoraniDao().getAll();
    }
}
