package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Dostavljaci;
import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            jela.setCijena(rs.getInt("cijena"));
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
        row.put("idRestorana", object.getIdRestorana());
        return row;
    }
}
