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
    public DostavljaciDaoSQLImpl(){
        super("Dostavljaci");
    }

    /**
     * Lists all deliverers from table Dostavljaci in database who have birthday on the same day
     * @param datumRodjenja date of birthday
     * @return List of all deliverers who have birthday on the same day
     */
    @Override
    public List<Dostavljaci> searchByBirthDate(Date datumRodjenja) {
        String query = "SELECT * FROM Dostavljaci WHERE datumRodjenja = ?";
        List<Dostavljaci> dostavljaci = new ArrayList<Dostavljaci>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Dostavljaci dostavljac = new Dostavljaci();
                dostavljac.setId(rs.getInt("idDostavljaca"));
                dostavljac.setIme(rs.getString("ime"));
                dostavljac.setPrezime(rs.getString("prezime"));
                dostavljac.setBroj(rs.getString("broj"));
                dostavljac.setDatumRodjenja(rs.getDate("datumRodjenja"));
                if(dostavljac.getDatumRodjenja().getDay()==datumRodjenja.getDay() && dostavljac.getDatumRodjenja().getMonth()==datumRodjenja.getMonth()) dostavljaci.add(dostavljac);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dostavljaci;
    }

    @Override
    public Dostavljaci row2object(ResultSet rs) throws OrderException {
        try {
            Dostavljaci cat = new Dostavljaci();
            cat.setId(rs.getInt("idDostavljaca"));
            cat.setIme(rs.getString("ime"));
            cat.setPrezime(rs.getString("prezime"));
            cat.setBroj(rs.getString("broj"));
            cat.setDatumRodjenja(rs.getDate("datumRodjenja"));
            return cat;
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
        return row;
    }
}
