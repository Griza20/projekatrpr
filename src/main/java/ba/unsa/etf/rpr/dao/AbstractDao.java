package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.io.FileReader;
import java.sql.*;
import java.util.AbstractMap;
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

    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement id=?, name=?, ...
     * @param row Row that will be updated
     * @return String with prepared parts for update
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
}
