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

    @Override
    public Narudzbe add(Narudzbe item) {
        return null;
    }

    @Override
    public Narudzbe update(Narudzbe item) {
        return null;
    }

    @Override
    public void delete(int id) {

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
