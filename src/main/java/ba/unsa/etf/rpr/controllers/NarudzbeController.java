package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.NarudzbeManager;
import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.domain.Narudzbe;
import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalTime;

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

    public SimpleStringProperty labelaProperty(){
        return labela;
    }

    public String getLabela(){
        return labela.get();
    }

    public void naruciClick(ActionEvent actionEvent) throws OrderException {
        karticaField.getStyleClass().removeAll("poljeNijeIspravno");
        codeField.getStyleClass().removeAll("poljeNijeIspravno");
        dateField.getStyleClass().removeAll("poljeNijeIspravno");
        if(this.validacija()) {
            Narudzbe n = new Narudzbe();
            n.setId(brojac++);
            n.setVrijemeNarucivanja(LocalTime.now().toString());
            n.setNarudzba(j.getJelo());
            n.setIdRestorana(r.getId());
            n.setIdDostavljaca(1);
            narudzbeManager.add(n);
            Stage scenaZaZatvoriti = (Stage) izlazButton.getScene().getWindow();
            scenaZaZatvoriti.close();
        }
    }
}
