package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.*;
import java.util.*;

/**
 * SQL Implementation class for Restorani table in database
 * @author Amar Grizovic
 */
public class RestoraniDaoSQLImpl extends AbstractDao<Restorani> implements RestoraniDao{
    public RestoraniDaoSQLImpl(){
        super("Restorani");
    }
    /**
     * Lists all restaurants from table Restorani in database that have same manager
     * @param vlasnik first and last name of manager
     * @return List of all retaurants with same manager
     */
    @Override
    public List<Restorani> searchByManagerName(String vlasnik) throws OrderException {
        return executeQuery("SELECT * FROM Restorani WHERE vlasnik = ?", new Object[]{vlasnik});
    }

    /**
     * Lists all restaurants from table Restorani in database that are nearby
     * @param lokacija location for searching
     * @return List of all restaurants that are close to each other
     */
    @Override
    public List<Restorani> searchByLocation(String lokacija) throws OrderException {
        return executeQuery("SELECT * FROM Restorani WHERE lokacija = ?", new Object[]{lokacija});
    }

    @Override
    public Restorani row2object(ResultSet rs) throws OrderException {
        try {
            Restorani cat = new Restorani();
            cat.setId(rs.getInt("idRestorana"));
            cat.setNaziv(rs.getString("naziv"));
            cat.setVlasnik(rs.getString("vlasnik"));
            cat.setLokacija(rs.getString("lokacija"));
            return cat;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Restorani object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("idRestorana", object.getId());
        row.put("naziv", object.getNaziv());
        row.put("vlasnik", object.getVlasnik());
        row.put("lokacija", object.getLokacija());
        return row;
    }
}
