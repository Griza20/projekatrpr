package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * SQL Implementation class for Jela table in database
 * @author Amar Grizovic
 */
public class JelaDaoSQLImpl extends AbstractDao<Jela>  implements JelaDao{
    public JelaDaoSQLImpl() {
        super("Jela");
    }

    @Override
    public Jela row2object(ResultSet rs) throws OrderException {
        try {
            Jela jela = new Jela();
            jela.setId(rs.getInt("idJela"));
            jela.setJelo(rs.getString("jelo"));
            jela.setCijena(rs.getDouble("cijena"));
            jela.setOpis(rs.getString("opis"));
            jela.setIdRestorana(rs.getInt("idRestorana"));
            return jela;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Jela object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("idJela", object.getId());
        row.put("jelo", object.getJelo());
        row.put("cijena", object.getCijena());
        row.put("opis",object.getOpis());
        row.put("idRestorana", object.getIdRestorana());
        return row;
    }

    /**
     * Gets all meals from a restaurant whose id is given as parameter
     * @param idRestorana unique id of a restaurant
     * @return List of all meals from a restaurant
     */
    @Override
    public List<Jela> getAllMealsFromRestaurant(int idRestorana) throws OrderException {
        return executeQuery("SELECT * FROM Jela WHERE idRestorana = ?",new Object[]{idRestorana});
    }
}
