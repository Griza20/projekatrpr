package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Narudzbe;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

/**
 * SQL Implementation class for Narudzbe table in database
 * @author Amar Grizovic
 */
public class NarudzbeDaoSQLImpl implements NarudzbeDao{
    private Connection connection;
    /**
     * Constructor for NarudzbeDaoSQLImpl class that connects class with database with hidden username and password
     */
    public NarudzbeDaoSQLImpl(){
        try{
            FileReader reader = new FileReader("baza.properties");
            Properties property = new Properties();
            property.load(reader);
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Baza",property.getProperty("username"),property.getProperty("password"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Gets an order from database with given id
     * @param id primary key of entity
     * @return All information about an order with given id
     */
    @Override
    public Narudzbe getById(int id) {
        String query = "SELECT * FROM Narudzbe WHERE idNarudzbe = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Narudzbe narudzba = new Narudzbe();
                narudzba.setIdNarudzbe(rs.getInt("idNarudzbe"));
                narudzba.setNarudzba(rs.getString("narudzba"));
                narudzba.setVrijemeNarucivanja(rs.getString("vrijemeNarucivanja"));
                narudzba.setIdRestorana(rs.getInt("idRestorana"));
                narudzba.setIdDostavljaca(rs.getInt("idDostavljaca"));
                rs.close();
                return narudzba;
            }
            else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adding a new order to table Narudzbe in database
     * @param item data that will be saved in database
     * @return All information about added order
     */
    @Override
    public Narudzbe add(Narudzbe item) {
        String insert = "INSERT INTO Narudzbe(narudzba,vrijemeNarucivanja,idRestorana,idDostavljaca) VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,item.getNarudzba());
            stmt.setString(2,item.getVrijemeNarucivanja());
            stmt.setInt(3,item.getIdRestorana());
            stmt.setInt(4, item.getIdDostavljaca());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setIdDostavljaca(rs.getInt(1));
            return item;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updating information about already existing order in database
     * @param item item that will be updated in database
     * @return All information about updated order
     */
    @Override
    public Narudzbe update(Narudzbe item) {
        String insert = "UPDATE Dostavljaci SET narudzba = ?, vrijemeNarucivanja = ?, idRestorana = ?, idDostavljaca = ? WHERE idNarudzbe = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getNarudzba());
            stmt.setObject(2, item.getVrijemeNarucivanja());
            stmt.setObject(3, item.getIdRestorana());
            stmt.setObject(4, item.getIdDostavljaca());
            stmt.setObject(5, item.getIdNarudzbe());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deleting an order from database with given id
     * @param id primary key of entity
     */
    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Narudzbe WHERE idNarudzbe = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Narudzbe> getAll() {
        return null;
    }

    @Override
    public List<Narudzbe> getByTimeOrdered() {
        return null;
    }
}
