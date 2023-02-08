package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Dostavljaci;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * SQL Implementation class for Dostavljaci table in database
 * @author Amar Grizovic
 */
public class DostavljaciDaoSQLImpl extends AbstractDao<Dostavljaci> implements DostavljaciDao{
    private static DostavljaciDaoSQLImpl instance = null;

    public static DostavljaciDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new DostavljaciDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    private DostavljaciDaoSQLImpl(){
        super("Dostavljaci");
    }

    /**
     * Lists all deliverers from table Dostavljaci in database who have birthday on the same day
     * @param datumRodjenja date of birthday
     * @return List of all deliverers who have birthday on the same day
     */
    @Override
    public List<Dostavljaci> searchByBirthDate(Date datumRodjenja) throws OrderException {
        return executeQuery("SELECT * FROM Dostavljaci WHERE datumRodjenja = ?", new Object[]{datumRodjenja});
    }

    @Override
    public Dostavljaci row2object(ResultSet rs) throws OrderException {
        try {
            Dostavljaci d = new Dostavljaci();
            d.setId(rs.getInt("idDostavljaca"));
            d.setIme(rs.getString("ime"));
            d.setPrezime(rs.getString("prezime"));
            d.setBroj(rs.getString("broj"));
            d.setDatumRodjenja(rs.getDate("datumRodjenja"));
            d.setSpol(rs.getString("spol"));
            d.setVisina(rs.getInt("visina"));
            d.setVozacka(rs.getBoolean("vozacka"));
            return d;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Dostavljaci object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("idDostavljaca", object.getId());
        row.put("ime", object.getIme());
        row.put("prezime", object.getPrezime());
        row.put("broj", object.getBroj());
        row.put("datumRodjenja", object.getDatumRodjenja());
        row.put("spol",object.getSpol());
        row.put("visina",object.getVisina());
        row.put("vozacka",object.getVozacka());
        return row;
    }
}
