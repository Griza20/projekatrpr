package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.JelaManager;
import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class JelaController {
    public Button izlazButton;
    public TextField opisField;
    public TextField nazivField;
    public Spinner<Double> cijenaSpinner;
    private JelaManager jelaManager;
    private int idResto;
    private boolean validacija(){
        boolean v=true;
        if(nazivField.getText().isEmpty()){
            v=false;
            nazivField.getStyleClass().add("poljeNijeIspravno");
        }
        if(opisField.getText().isEmpty()){
            v=false;
            opisField.getStyleClass().add("poljeNijeIspravno");
        }
        return v;
    }

    public JelaController(int idRestorana){
        jelaManager = new JelaManager();
        idResto=idRestorana;
    }

    @FXML
    void initialize(){
        nazivField.getStyleClass().removeAll("poljeNijeIspravno");
        opisField.getStyleClass().removeAll("poljeNijeIspravno");
    }
}
