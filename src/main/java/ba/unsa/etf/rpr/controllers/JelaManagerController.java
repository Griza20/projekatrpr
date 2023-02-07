package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.JelaManager;
import ba.unsa.etf.rpr.business.RestoraniManager;
import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.List;

public class JelaManagerController {
    public TextField jeloField, opisField;
    public ChoiceBox<Restorani> restoChoice;
    public ChoiceBox<Jela> jelaChoice;
    public Spinner<Double> cijenaField;
    private JeloModel jeloModel;
    private JelaManager jelaManager;
    private RestoraniManager restoraniManager;
    private ObservableList<Restorani> restorani;
    private ObservableList<Jela> listaJela = FXCollections.observableArrayList();
    public Button izlazButton;
    public JelaManagerController() {
        jelaManager = new JelaManager();
        restoraniManager = new RestoraniManager();
        jeloModel = new JeloModel();
        try {
            restorani = FXCollections.observableArrayList(restoraniManager.getAll());
        } catch (OrderException e) {
            System.out.println("Problem sa getAll metodom restoraniManager");
            throw new RuntimeException(e);
        }

    }

    @FXML
    void initialize() {
        restoChoice.setItems(restorani);
        restoChoice.getSelectionModel().selectedItemProperty().addListener((obs, oldRestoran, newRestoran) -> {
            try {
                jelaChoice.getItems().clear();
                List<Jela> ljela = jelaManager.getAllMealsFromRestaurant(newRestoran.getId());
                listaJela.addAll(ljela);
                jelaChoice.setItems(listaJela);
                jelaChoice.getSelectionModel().selectedItemProperty().addListener((o, oldJelo, newJelo) -> {
                    if(oldJelo != null){
                        jeloField.textProperty().unbindBidirectional(jeloModel.jelo);
                        opisField.textProperty().unbindBidirectional(jeloModel.opis);
                        cijenaField.getValueFactory().valueProperty().unbindBidirectional(jeloModel.cijena.asObject());
                    }
                    if(newJelo!=null) {
                        jeloModel.fromJelo(newJelo);
                        jeloField.textProperty().bindBidirectional(jeloModel.jelo);
                        opisField.textProperty().bindBidirectional(jeloModel.opis);
                        cijenaField.getValueFactory().valueProperty().bindBidirectional(jeloModel.cijena.asObject());
                    }
                });
                jeloModel.restoId=newRestoran.getId();
            } catch (OrderException e) {
                System.out.println("Problem sa getAllMealsFromRestaurant metodom jelaManager");
                throw new RuntimeException(e);
            }
        });
    }

    public class JeloModel{
        public Integer id, restoId;
        public SimpleStringProperty jelo = new SimpleStringProperty();
        public SimpleStringProperty opis = new SimpleStringProperty();
        public DoubleProperty cijena = new SimpleDoubleProperty();
        public JeloModel(){}
        public void fromJelo(Jela j){
            this.id = j.getId();
            this.jelo = new SimpleStringProperty(j.getJelo());
            this.opis = new SimpleStringProperty(j.getOpis());
            this.cijena = new SimpleDoubleProperty(j.getCijena());
        }
        public Jela toJelo() {
            Jela j = new Jela();
            j.setId(jeloModel.id);
            j.setJelo(jelo.get());
            j.setOpis(opis.get());
            j.setCijena(jeloModel.cijena.getValue());
            j.setIdRestorana(jeloModel.restoId);
            return j;
        }
    }
}
