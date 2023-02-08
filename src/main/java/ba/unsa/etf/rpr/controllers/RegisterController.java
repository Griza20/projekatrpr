package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class RegisterController {
    public TextField userField = new TextField();
    public PasswordField pwField = new PasswordField();
    public PasswordField rpwField = new PasswordField();
    private Connection connection;

    @FXML
    public void initialize() {
        userField.getStyleClass().removeAll("poljeNijeIspravno");
        pwField.getStyleClass().removeAll("poljeNijeIspravno");
        rpwField.getStyleClass().removeAll("poljeNijeIspravno");
    }

    public void registerClick(ActionEvent actionEvent) {
        userField.getStyleClass().removeAll("poljeNijeIspravno");
        pwField.getStyleClass().removeAll("poljeNijeIspravno");
        rpwField.getStyleClass().removeAll("poljeNijeIspravno");
        //Validacija username
        String user = userField.getText();
        boolean test = true;
        if(user.length()<=3 || user.length()>15){
            userField.getStyleClass().add("poljeNijeIspravno");
            test = false;
        }
        //Validacija pw
        String sifra = pwField.getText(), sifra1 = rpwField.getText();
        int brojac_brojeva=0, brojac_velikih_slova=0;
        for(int i=0; i<sifra.length(); i++) {
            if (sifra.charAt(i) >= 'A' && sifra.charAt(i) <= 'Z') brojac_velikih_slova = brojac_velikih_slova + 1;
            else if (sifra.charAt(i) >= '0' && sifra.charAt(i) <= '9') brojac_brojeva = brojac_brojeva + 1;
        }
        if(sifra.length()<5 || sifra.length()>20 || brojac_brojeva==0 || brojac_velikih_slova==0){
            pwField.getStyleClass().add("poljeNijeIspravno");
            test = false;
        }
        if(!sifra.equals(sifra1) || sifra1.isEmpty()){
            rpwField.getStyleClass().add("poljeNijeIspravno");
            test = false;
        }
        if(test){
            //Konektovanje na bazu
            try{
                FileReader reader = new FileReader("C:\\Users\\User\\IdeaProjects\\projekatrpr\\src\\main\\resources\\baza.properties");
                Properties property = new Properties();
                property.load(reader);
                this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Baza?sessionVariables=WAIT_TIMEOUT=28800",property.getProperty("username"),property.getProperty("password"));
            }catch(Exception e){
                System.out.println("Nije uspostavljena konekcija sa bazom.");
                e.printStackTrace();
            }
            //Ubacivanje podataka u bazu u tabelu Korisnici koja nije povezana sa tabelama iz sistema narudzbi
            String insert = "INSERT INTO Korisnici(username,password) VALUES(?,?)";
            try{
                PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1,user);
                stmt.setString(2,sifra);
                stmt.executeUpdate();
            } catch(Exception e){
                e.printStackTrace();
            }
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
        test = true;
    }
}
