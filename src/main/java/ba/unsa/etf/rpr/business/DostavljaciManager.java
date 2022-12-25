package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Dostavljaci;
import ba.unsa.etf.rpr.exceptions.OrderException;

/**
 * Business Logic Layer for management of Dostavljaci
 * @author Amar Grizovic
 */
public class DostavljaciManager {
    public Dostavljaci add(Dostavljaci d) throws OrderException {
        if (d.getId() != 0)
            throw new OrderException("Can't add deliverer with ID. ID is autogenerated");
        return DaoFactory.dostavljaciDao().add(d);
    }
}
