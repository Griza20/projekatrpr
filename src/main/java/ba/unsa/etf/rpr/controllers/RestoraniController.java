package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RestoraniManager;
import ba.unsa.etf.rpr.dao.RestoraniDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class RestoraniController {
    public TextField lokacijaRestorana;
    public TextField nazivRestorana;
    public TextField imeVlasnika;
    public Button dodajRestoran;
    public Button izlaz;
    private Connection connection;
    RestoraniManager restoraniManager;

    public RestoraniController(){
        restoraniManager = new RestoraniManager();
    }

    @FXML
    void initialize(){
        imeVlasnika.getStyleClass().removeAll("poljeNijeIspravno");
        nazivRestorana.getStyleClass().removeAll("poljeNijeIspravno");
        lokacijaRestorana.getStyleClass().removeAll("poljeNijeIspravno");
    }
}
