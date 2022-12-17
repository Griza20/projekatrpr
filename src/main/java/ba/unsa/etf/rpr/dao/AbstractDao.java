package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.io.FileReader;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 * @author Amar Grizovic
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T>{
    private Connection connection;
    private String imeTabele;

    public AbstractDao(String imeTabele){
        try{
            this.imeTabele=imeTabele;
            FileReader reader = new FileReader("baza.properties");
            Properties property = new Properties();
            property.load(reader);
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Baza",property.getProperty("username"),property.getProperty("password"));
        }catch(Exception e){
            System.out.println("Nije uspostavljena konekcija sa bazom.");
            e.printStackTrace();
        }
    }
    public abstract T row2object(ResultSet rs) throws OrderException;

    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws OrderException{
        String query = "SELECT * FROM "+this.imeTabele+" WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                T result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new OrderException("Object not found");
            }
        } catch (SQLException e) {
            throw new OrderException(e.getMessage(), e);
        }
    }
}
