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

    @Override
    public Dostavljaci add(Dostavljaci item) {
        return null;
    }

    @Override
    public Dostavljaci update(Dostavljaci item) {
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
