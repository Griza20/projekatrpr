package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.OrderException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 * @author Amar Grizovic
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T>{
    private Connection connection;
    private String imeTabele;
    private String idIme;

    public Connection getConnection(){
        return this.connection;
    }

    public AbstractDao(String imeTabele){
        try{
            this.imeTabele=imeTabele;
            this.idIme = "id" + this.imeTabele.substring(0,this.imeTabele.length()-1);
            if(this.imeTabele.equals("Narudzbe")){
                idIme = idIme + "e";
            }
            else{
                idIme = idIme + "a";
            }
            FileReader reader = new FileReader("C:\\Users\\User\\IdeaProjects\\projekatrpr\\src\\main\\resources\\baza.properties");
            Properties property = new Properties();
            property.load(reader);
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Baza?sessionVariables=WAIT_TIMEOUT=28800",property.getProperty("username"),property.getProperty("password"));
        }catch(Exception e){
            System.out.println("Nije uspostavljena konekcija sa bazom.");
            e.printStackTrace();
        }
    }
    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws OrderException in case of error with db
     */
    public abstract T row2object(ResultSet rs) throws OrderException;

    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws OrderException{
        String query = "SELECT * FROM "+this.imeTabele+" WHERE " + this.idIme + " = ?";
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

    public T add(T item) throws OrderException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(imeTabele);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = this.connection.prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals(idIme)) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));

            return item;
        }catch (SQLException e){
            throw new OrderException(e.getMessage(), e);
        }
    }

    public T update(T item) throws OrderException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(imeTabele)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE ")
                .append(idIme)
                .append(" = ?");

        try{
            PreparedStatement stmt = this.connection.prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals(idIme)) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new OrderException(e.getMessage(), e);
        }
    }

    public void delete(int id) throws OrderException {
        String sql = "DELETE FROM "+imeTabele+" WHERE " + idIme + " = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new OrderException(e.getMessage(), e);
        }
    }

    public List<T> getAll() throws OrderException {
        String query = "SELECT * FROM "+ imeTabele;
        List<T> results = new ArrayList<T>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                T object = row2object(rs);
                results.add(object);
            }
            rs.close();
            return results;
        }catch (SQLException e){
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
            if (entry.getKey().equals(idIme)) continue;
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
            if (entry.getKey().equals(idIme)) continue;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
    /**
     * Utility method for executing any kind of query
     * @param query - SQL query
     * @param params - params for query
     * @return List of objects from database
     * @throws OrderException in case of error with db
     */
    public List<T> executeQuery(String query, Object[] params) throws OrderException{
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new OrderException(e.getMessage(), e);
        }
    }
}
