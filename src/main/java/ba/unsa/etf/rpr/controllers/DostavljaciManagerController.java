package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DostavljaciManager;
import ba.unsa.etf.rpr.domain.Dostavljaci;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Controller class for manipulation with editing or deleting an existing deliverer
 */
public class DostavljaciManagerController {
    public TextField fldIme, fldPrezime, fldBroj;
    public DatePicker datumPicker;
    public ChoiceBox<Dostavljaci> choiceDostavljaci;
    private DostavljacModel dostavljacModel;
    private DostavljaciManager dostavljaciManager;
    private ObservableList<Dostavljaci> dostavljaci;
    public Button izlazButton;

    /**
     * Constructor which gives value to atribute dostavljaci which is important for choicebox initialization
     */
    public DostavljaciManagerController() {
        dostavljaciManager = new DostavljaciManager();
        dostavljacModel = new DostavljacModel();
        try {
            dostavljaci = FXCollections.observableArrayList(dostavljaciManager.getAll());
        } catch (OrderException e) {
            System.out.println("Problem sa getAll metodom dostavljaciManager");
            throw new RuntimeException(e);
        }

    }

    /**
     * Method for bidirectional binding and unbinding
     */
    @FXML
    void initialize() {
        choiceDostavljaci.setItems(dostavljaci);
        choiceDostavljaci.getSelectionModel().selectedItemProperty().addListener((obs, oldDostavljac, newDostavljac) -> {
            if(oldDostavljac != null) {
                fldIme.textProperty().unbindBidirectional(dostavljacModel.ime);
                fldPrezime.textProperty().unbindBidirectional(dostavljacModel.prezime);
                fldBroj.textProperty().unbindBidirectional(dostavljacModel.broj);
                datumPicker.valueProperty().unbindBidirectional(dostavljacModel.datumRodjenja);
            }
            dostavljacModel.fromDostavljac(newDostavljac);
            fldIme.textProperty().bindBidirectional(dostavljacModel.ime);
            fldPrezime.textProperty().bindBidirectional(dostavljacModel.prezime);
            fldBroj.textProperty().bindBidirectional(dostavljacModel.broj);
            datumPicker.valueProperty().bindBidirectional(dostavljacModel.datumRodjenja);
        });
    }

    /**
     * Editting an already existing deliverer through bidirectional binded fields
     * @param actionEvent
     */
    public void promijeniClick(ActionEvent actionEvent){
        try{
            Dostavljaci d = dostavljacModel.toDostavljac();
            dostavljaciManager.update(d);
            Stage scenaZaZatvoriti = (Stage) izlazButton.getScene().getWindow();
            scenaZaZatvoriti.close();
        }catch (OrderException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Deleting an already existing deliverer through bidirectional binded fields
     * @param actionEvent
     * @throws OrderException
     */
    public void brisiClick(ActionEvent actionEvent) throws OrderException {
        Dostavljaci d = dostavljacModel.toDostavljac();
        dostavljaciManager.delete(d.getId());
        Stage scenaZaZatvoriti = (Stage) izlazButton.getScene().getWindow();
        scenaZaZatvoriti.close();
    }

    /**
     * Closing the window for editing or deleting deliverer
     * @param actionEvent
     */
    public void izlazClick(ActionEvent actionEvent){
        Stage scenaZaZatvoriti = (Stage) izlazButton.getScene().getWindow();
        scenaZaZatvoriti.close();
    }

    /**
     * Model class for easier databinding
     */
    public class DostavljacModel{
        public Integer id;
        public int visina;
        public String spol;
        public Boolean vozacka;
        public SimpleStringProperty ime = new SimpleStringProperty();
        public SimpleStringProperty prezime = new SimpleStringProperty();
        public SimpleStringProperty broj = new SimpleStringProperty();
        public SimpleObjectProperty<LocalDate> datumRodjenja = new SimpleObjectProperty<>();
        public DostavljacModel(){}

        /**
         * Initializing values of property atributes
         * @param dostavljaci instance of bean class Dostavljaci
         */
        public void fromDostavljac(Dostavljaci dostavljaci){
            this.id = dostavljaci.getId();
            this.spol = dostavljaci.getSpol();
            this.vozacka = dostavljaci.getVozacka();
            this.visina = dostavljaci.getVisina();
            this.ime = new SimpleStringProperty(dostavljaci.getIme());
            this.prezime = new SimpleStringProperty(dostavljaci.getPrezime());
            this.broj = new SimpleStringProperty(dostavljaci.getBroj());
            this.datumRodjenja = new SimpleObjectProperty<>(dostavljaci.getDatumRodjenja().toLocalDate());
        }

        /**
         * Creating an instance of bean class Dostavljaci and initializing atributes with correct values
         * @return instance of bean class Dostavljaci
         */
        public Dostavljaci toDostavljac() {
            Dostavljaci d = new Dostavljaci();
            d.setId(dostavljacModel.id);
            d.setIme(ime.get());
            d.setPrezime(prezime.get());
            d.setBroj(broj.get());
            d.setSpol(dostavljacModel.spol);
            d.setVisina(dostavljacModel.visina);
            d.setVozacka(dostavljacModel.vozacka);
            d.setDatumRodjenja(Date.valueOf(datumRodjenja.getValue()));
            return d;
        }
    }
}
