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

/**
 * Controller class for manipulation with adding new meal
 */
public class JelaController {
    public Button izlazButton;
    public TextField opisField;
    public TextField nazivField;
    public Spinner<Double> cijenaSpinner;
    private JelaManager jelaManager;
    private int idResto;

    /**
     * Method that validates text fields
     * @return true if fields aren't empty, else false
     */
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

    /**
     * This method takes care about initial colors of fields
     */
    @FXML
    void initialize(){
        nazivField.getStyleClass().removeAll("poljeNijeIspravno");
        opisField.getStyleClass().removeAll("poljeNijeIspravno");
    }

    /**
     * Adds a new meal in database if validation check is true
     * @param actionEvent
     * @throws OrderException
     */
    public void dodajClick(ActionEvent actionEvent) throws OrderException {
        nazivField.getStyleClass().removeAll("poljeNijeIspravno");
        opisField.getStyleClass().removeAll("poljeNijeIspravno");
        if(this.validacija()){
            Jela j = new Jela();
            j.setJelo(nazivField.getText());
            j.setOpis(opisField.getText());
            j.setCijena(cijenaSpinner.getValue());
            j.setIdRestorana(idResto);
            jelaManager.add(j);
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Closes the current window for adding a new meal
     * @param actionEvent
     */
    public void izlazClick(ActionEvent actionEvent) {
        Stage scenaZaZatvoriti = (Stage) izlazButton.getScene().getWindow();
        scenaZaZatvoriti.close();
    }
}
