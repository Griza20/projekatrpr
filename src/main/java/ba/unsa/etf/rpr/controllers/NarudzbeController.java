package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.NarudzbeManager;
import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.domain.Restorani;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NarudzbeController {
    public Button izlazButton;
    public RadioButton kesButton, karticaButton;
    public TextField karticaField, codeField;
    public DatePicker dateField;
    private Double cijena;
    private SimpleStringProperty labela;
    private Jela j;
    private Restorani r;
    private static int brojac = 0;
    private NarudzbeManager narudzbeManager;

    private boolean validacija(){
        boolean v=true;
        if(karticaButton.isSelected()){
            if (karticaField.getText().isEmpty()) {
                v = false;
                karticaField.getStyleClass().add("poljeNijeIspravno");
            }
            if (codeField.getText().isEmpty()) {
                v = false;
                codeField.getStyleClass().add("poljeNijeIspravno");
            }
            if (dateField.getValue() == null) {
                v = false;
                dateField.getStyleClass().add("poljeNijeIspravno");
            }
        }
        return v;
    }

    @FXML
    void initialize(){
        karticaField.getStyleClass().removeAll("poljeNijeIspravno");
        codeField.getStyleClass().removeAll("poljeNijeIspravno");
        dateField.getStyleClass().removeAll("poljeNijeIspravno");
    }

    public NarudzbeController(Jela j, Restorani r){
        this.j=j;
        this.r=r;
        this.cijena=j.getCijena();
        labela = new SimpleStringProperty(cijena.toString()+ "0 KM");
        narudzbeManager = new NarudzbeManager();
    }
}
