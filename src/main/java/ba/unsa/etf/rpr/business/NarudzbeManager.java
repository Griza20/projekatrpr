package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Narudzbe;
import ba.unsa.etf.rpr.exceptions.OrderException;

/**
 * Business logic layer for Narudzbe
 * @author Amar Grizovic
 */
public class NarudzbeManager {
    public Narudzbe getById(int idNarudzbe) throws OrderException {
        return DaoFactory.narudzbeDao().getById(idNarudzbe);
    }
}
