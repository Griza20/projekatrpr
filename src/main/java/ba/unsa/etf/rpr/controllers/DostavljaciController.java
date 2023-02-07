package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DostavljaciManager;
import ba.unsa.etf.rpr.domain.Dostavljaci;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;

public class DostavljaciController {
    public Button dodajButton;
    public DatePicker datumField;
    public TextField brojField;
    public TextField prezimeField;
    public TextField imeField;
    public Button izlazButton;
    public RadioButton zenskoButton, muskoButton;
    public Slider visinaSlider;
    public CheckBox vozackaBox;

    DostavljaciManager dostavljaciManager;

    public DostavljaciController(){ dostavljaciManager = new DostavljaciManager(); }

    private boolean validacija(){
        boolean v=true;
        if(imeField.getText().isEmpty()){
            v=false;
            imeField.getStyleClass().add("poljeNijeIspravno");
        }
        if(prezimeField.getText().isEmpty()){
            v=false;
            prezimeField.getStyleClass().add("poljeNijeIspravno");
        }
        if(brojField.getText().isEmpty()){
            v=false;
            brojField.getStyleClass().add("poljeNijeIspravno");
        }
        if(datumField.getValue()==null || datumField.getValue().isAfter(LocalDate.now())){
            v=false;
            datumField.getStyleClass().add("poljeNijeIspravno");
        }
        if(!muskoButton.isSelected() && !zenskoButton.isSelected()){
            v=false;
        }
        return v;
    }

    @FXML
    void initialize(){
        imeField.getStyleClass().removeAll("poljeNijeIspravno");
        prezimeField.getStyleClass().removeAll("poljeNijeIspravno");
        brojField.getStyleClass().removeAll("poljeNijeIspravno");
        datumField.getStyleClass().removeAll("poljeNijeIspravno");
    }

    public void clickDodaj(ActionEvent actionEvent) throws OrderException {
        imeField.getStyleClass().removeAll("poljeNijeIspravno");
        prezimeField.getStyleClass().removeAll("poljeNijeIspravno");
        brojField.getStyleClass().removeAll("poljeNijeIspravno");
        datumField.getStyleClass().removeAll("poljeNijeIspravno");
        if(this.validacija()){
            Dostavljaci d = new Dostavljaci();
            d.setIme(imeField.getText());
            d.setPrezime(prezimeField.getText());
            d.setBroj(brojField.getText());
            d.setDatumRodjenja(Date.valueOf(datumField.getValue()));
            d.setVozacka(vozackaBox.isSelected());
            d.setVisina((int)visinaSlider.getValue());
            if(muskoButton.isSelected())d.setSpol("M");
            else d.setSpol("Z");
            dostavljaciManager.add(d);
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }

    public void clickIzlaz(ActionEvent actionEvent) {
        Stage scenaZaZatvoriti = (Stage) izlazButton.getScene().getWindow();
        scenaZaZatvoriti.close();
    }
}
