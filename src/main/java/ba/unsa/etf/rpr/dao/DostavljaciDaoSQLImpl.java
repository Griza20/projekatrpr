package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Dostavljaci;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * SQL Implementation class for Dostavljaci table in database
 * @author Amar Grizovic
 */
public class DostavljaciDaoSQLImpl implements DostavljaciDao{
    private Connection connection;

    /**
     * Constructor for DostavljaciDaoSQLImpl class that connects class with database with hidden username and password
     */
    public DostavljaciDaoSQLImpl(){
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
     * Gets a deliverer from database with given id
     * @param id primary key of entity
     * @return All information about deliverer with given id
     */
    @Override
    public Dostavljaci getById(int id) {
        String query = "SELECT * FROM Dostavljaci WHERE idDostavljaca = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Dostavljaci dostavljac = new Dostavljaci();
                dostavljac.setIdDostavljaca(rs.getInt("idDostavljaca"));
                dostavljac.setIme(rs.getString("ime"));
                dostavljac.setPrezime(rs.getString("prezime"));
                dostavljac.setBroj(rs.getString("broj"));
                dostavljac.setDatumRodjenja(rs.getDate("datumRodjenja"));
                rs.close();
                return dostavljac;
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
     * Adding a new deliverer to table Dostavljaci in database
     * @param item data that will be saved in database
     * @return All information about added deliverer
     */
    @Override
    public Dostavljaci add(Dostavljaci item) {
        String insert = "INSERT INTO Dostavljaci(ime,prezime,broj,datumRodjenja) VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,item.getIme());
            stmt.setString(2,item.getPrezime());
            stmt.setString(3,item.getBroj());
            stmt.setDate(4, item.getDatumRodjenja());
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
     * Updating information about already existing deliverer in database
     * @param item item that will be updated in database
     * @return All information about updated deliverer
     */
    @Override
    public Dostavljaci update(Dostavljaci item) {
        String insert = "UPDATE Dostavljaci SET ime = ?, prezime = ?, broj = ?, datumRodjenja = ? WHERE idDostavljaca = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getIme());
            stmt.setObject(2, item.getPrezime());
            stmt.setObject(3, item.getBroj());
            stmt.setObject(4, item.getDatumRodjenja());
            stmt.setObject(5, item.getIdDostavljaca());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Dostavljaci> getAll() {
        return null;
    }

    @Override
    public List<Dostavljaci> searchByBirthDate(Date datumRodjenja) {
        return null;
    }
}
