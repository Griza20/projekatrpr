package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.sql.ResultSet;
import java.util.Map;

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
        return null;
    }

    @Override
    public Map<String, Object> object2row(Jela object) {
        return null;
    }
}
