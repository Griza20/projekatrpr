package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RestoraniManager;
import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RestoraniManagerController {
    public TextField vlasnikField, lokacijaField, nazivField;
    public ChoiceBox<Restorani> restoChoice;
    private RestoranModel restoranModel;
    private RestoraniManager restoraniManager;
    private ObservableList<Restorani> restorani;
    public Button izlazButton;
    public RestoraniManagerController() {
        restoraniManager = new RestoraniManager();
        restoranModel = new RestoranModel();
        try {
            restorani = FXCollections.observableArrayList(restoraniManager.getAll());
        } catch (OrderException e) {
            System.out.println("Problem sa getAll metodom restoraniManager");
            throw new RuntimeException(e);
        }

    }

    public class RestoranModel{
        public Integer id;
        public SimpleStringProperty vlasnik = new SimpleStringProperty();
        public SimpleStringProperty lokacija = new SimpleStringProperty();
        public SimpleStringProperty naziv = new SimpleStringProperty();
        public RestoranModel(){}
        public void fromRestoran(Restorani restorani){
            this.id = restorani.getId();
            this.naziv = new SimpleStringProperty(restorani.getNaziv());
            this.vlasnik = new SimpleStringProperty(restorani.getVlasnik());
            this.lokacija = new SimpleStringProperty(restorani.getLokacija());
        }
        public Restorani toRestoran() {
            Restorani r = new Restorani();
            r.setId(restoranModel.id);
            r.setVlasnik(vlasnik.get());
            r.setLokacija(lokacija.get());
            r.setNaziv(naziv.get());
            return r;
        }
    }
}
