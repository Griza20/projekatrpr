package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Narudzbe;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.*;
import java.util.*;

/**
 * SQL Implementation class for Narudzbe table in database
 * @author Amar Grizovic
 */
public class NarudzbeDaoSQLImpl extends AbstractDao<Narudzbe> implements NarudzbeDao{
    private static NarudzbeDaoSQLImpl instance = null;
    private NarudzbeDaoSQLImpl(){
        super("Narudzbe");
    }
    /**
     * @author Amar Grizovic
     * @return NarudzbeDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'Narudzbe' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static NarudzbeDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new NarudzbeDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Narudzbe row2object(ResultSet rs) throws OrderException {
        try {
            Narudzbe cat = new Narudzbe();
            cat.setId(rs.getInt("idNarudzbe"));
            cat.setNarudzba(rs.getString("narudzba"));
            cat.setVrijemeNarucivanja(rs.getString("vrijemeNarucivanja"));
            cat.setIdRestorana(rs.getInt("idRestorana"));
            cat.setIdDostavljaca(rs.getInt("idDostavljaca"));
            return cat;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Narudzbe object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("idNarudzbe", object.getId());
        row.put("narudzba", object.getNarudzba());
        row.put("vrijemeNarucivanja", object.getVrijemeNarucivanja());
        row.put("idRestorana", object.getIdRestorana());
        row.put("idDostavljaca", object.getIdDostavljaca());
        return row;
    }

    /**
     * Lists all orders from table Narudzbe in database, sorted by time of making order
     * @return List of all orders, sorted ascending by the time of making order
     */
    @Override
    public List<Narudzbe> getByTimeOrdered() {
        String query = "SELECT * FROM Narudzbe";
        List<Narudzbe> narudzbe = new ArrayList<Narudzbe>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Narudzbe narudzba = new Narudzbe();
                narudzba.setId(rs.getInt("idNarudzbe"));
                narudzba.setNarudzba(rs.getString("narudzba"));
                narudzba.setVrijemeNarucivanja(rs.getString("vrijemeNarucivanja"));
                narudzba.setIdRestorana(rs.getInt("idRestorana"));
                narudzba.setIdDostavljaca(rs.getInt("idDostavljaca"));
                narudzbe.add(narudzba);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        Collections.sort(narudzbe, new Comparator<Narudzbe>() {
            public int compare(Narudzbe n1, Narudzbe n2) {
                return n1.getVrijemeNarucivanja().compareTo(n2.getVrijemeNarucivanja());
            }});
        return narudzbe;
    }
}
