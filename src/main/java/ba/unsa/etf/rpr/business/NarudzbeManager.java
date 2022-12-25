package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Narudzbe;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Business logic layer for Narudzbe
 * @author Amar Grizovic
 */
public class NarudzbeManager {
    public Narudzbe getById(int idNarudzbe) throws OrderException {
        return DaoFactory.narudzbeDao().getById(idNarudzbe);
    }

    public Narudzbe add(Narudzbe n) throws OrderException{
        return DaoFactory.narudzbeDao().add(n);
    }

    public void update(Narudzbe n) throws OrderException{
        DaoFactory.narudzbeDao().update(n);
    }

    public void delete(int idNarudzbe) throws OrderException{
        DaoFactory.narudzbeDao().delete(idNarudzbe);
    }

    public List<Narudzbe> getAll() throws OrderException {
        return DaoFactory.narudzbeDao().getAll();
    }

    public List<Narudzbe> getByTimeOrdered() {
        return DaoFactory.narudzbeDao().getByTimeOrdered();
    }
}
