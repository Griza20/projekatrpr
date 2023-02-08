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

/**
 * Controller class for manipulation with adding new order
 */
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

    /**
     * Method that validates text fields and date picker
     * @return true if fields aren't empty, else false
     */
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

    /**
     * This method takes care about initial colors of fields
     */
    @FXML
    void initialize(){
        karticaField.getStyleClass().removeAll("poljeNijeIspravno");
        codeField.getStyleClass().removeAll("poljeNijeIspravno");
        dateField.getStyleClass().removeAll("poljeNijeIspravno");
    }

    /**
     * Constructor which gets information which meal from which restaurant is ordered
     * @param j instance of bean class Jela
     * @param r instance of bean class Restorani
     */
    public NarudzbeController(Jela j, Restorani r){
        this.j=j;
        this.r=r;
        this.cijena=j.getCijena();
        labela = new SimpleStringProperty(cijena.toString()+ "0 KM");
        narudzbeManager = new NarudzbeManager();
    }

    /**
     * Methods for one-way binding with label on window
     * @return string property
     */
    public SimpleStringProperty labelaProperty(){
        return labela;
    }

    public String getLabela(){
        return labela.get();
    }

    /**
     * Adds a new order in database if validation check is true
     * @param actionEvent
     * @throws OrderException
     */
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

    /**
     * Closes the current window for adding a new order
     * @param actionEvent
     */
    public void prekidClick(ActionEvent actionEvent){
        Stage scenaZaZatvoriti = (Stage) izlazButton.getScene().getWindow();
        scenaZaZatvoriti.close();
    }
}
