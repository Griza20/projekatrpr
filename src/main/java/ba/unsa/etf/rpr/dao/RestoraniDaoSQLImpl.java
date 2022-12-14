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

    @Override
    public Restorani add(Restorani item) {
        return null;
    }

    @Override
    public Restorani update(Restorani item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Restorani> getAll() {
        return null;
    }

    @Override
    public List<Restorani> searchByManagerName(String vlasnik) {
        return null;
    }

    @Override
    public List<Restorani> searchByLocation(String lokacija) {
        return null;
    }
}
