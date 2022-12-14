package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Narudzbe;
import ba.unsa.etf.rpr.domain.Restorani;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * SQL Implementation class for Restorani table in database
 * @author Amar Grizovic
 */
public class RestoraniDaoSQLImpl implements RestoraniDao{
    private Connection connection;
    /**
     * Constructor for RestoraniDaoSQLImpl class that connects class with database with hidden username and password
     */
    public RestoraniDaoSQLImpl(){
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
     * Gets an restaurant from database with given id
     * @param id primary key of entity
     * @return All information about a restaurant with given id
     */
    @Override
    public Restorani getById(int id) {
        String query = "SELECT * FROM Restorani WHERE idRestorana = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Restorani restoran = new Restorani();
                restoran.setIdRestorana(rs.getInt("idRestorana"));
                restoran.setNaziv(rs.getString("naziv"));
                restoran.setVlasnik(rs.getString("vlasnik"));
                restoran.setLokacija(rs.getString("lokacija"));
                rs.close();
                return restoran;
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
     * Adding a new restaurant to table Restorani in database
     * @param item data that will be saved in database
     * @return All information about added restaurant
     */
    @Override
    public Restorani add(Restorani item) {
        String insert = "INSERT INTO Restorani(naziv,vlasnik,lokacija) VALUES(?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,item.getNaziv());
            stmt.setString(2,item.getVlasnik());
            stmt.setString(3,item.getLokacija());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setIdRestorana(rs.getInt(1));
            return item;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updating information about already existing restaurant in database
     * @param item item that will be updated in database
     * @return All information about updated restaurant
     */
    @Override
    public Restorani update(Restorani item) {
        String insert = "UPDATE Restorani SET naziv = ?, vlasnik = ?, lokacija = ? WHERE idRestorana = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getNaziv());
            stmt.setObject(2, item.getVlasnik());
            stmt.setObject(3, item.getLokacija());
            stmt.setObject(4, item.getIdRestorana());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deleting a restaurant from database with given id
     * @param id primary key of entity
     */
    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Restorani WHERE idRestorana = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Lists all restaurants from table Restorani in database
     * @return List of all restaurants
     */
    @Override
    public List<Restorani> getAll() {
        String query = "SELECT * FROM Restorani";
        List<Restorani> restorani = new ArrayList<Restorani>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Restorani restoran = new Restorani();
                restoran.setIdRestorana(rs.getInt("idRestorana"));
                restoran.setNaziv(rs.getString("naziv"));
                restoran.setVlasnik(rs.getString("vlasnik"));
                restoran.setLokacija(rs.getString("lokacija"));
                restorani.add(restoran);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return restorani;
    }

    /**
     * Lists all restaurants from table Restorani in database that have same manager
     * @param vlasnik first and last name of manager
     * @return List of all retaurants with same manager
     */
    @Override
    public List<Restorani> searchByManagerName(String vlasnik) {
        String query = "SELECT * FROM Restorani";
        List<Restorani> restorani = new ArrayList<Restorani>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Restorani restoran = new Restorani();
                restoran.setIdRestorana(rs.getInt("idRestorana"));
                restoran.setNaziv(rs.getString("naziv"));
                restoran.setVlasnik(rs.getString("vlasnik"));
                restoran.setLokacija(rs.getString("lokacija"));
                if(restoran.getVlasnik().equals(vlasnik)) restorani.add(restoran);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return restorani;
    }

    /**
     * Lists all restaurants from table Restorani in database that are nearby
     * @param lokacija location for searching
     * @return List of all restaurants that are close to each other
     */
    @Override
    public List<Restorani> searchByLocation(String lokacija) {
        String query = "SELECT * FROM Restorani";
        List<Restorani> restorani = new ArrayList<Restorani>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Restorani restoran = new Restorani();
                restoran.setIdRestorana(rs.getInt("idRestorana"));
                restoran.setNaziv(rs.getString("naziv"));
                restoran.setVlasnik(rs.getString("vlasnik"));
                restoran.setLokacija(rs.getString("lokacija"));
                if(restoran.getLokacija().equals(lokacija)) restorani.add(restoran);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return restorani;
    }
}
