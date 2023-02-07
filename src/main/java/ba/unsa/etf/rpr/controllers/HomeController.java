package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.JelaManager;
import ba.unsa.etf.rpr.business.RestoraniManager;
import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.List;
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HomeController {
    public Button drButton;
    public Button ddButton;
    public Button djButton;
    public TableView<Jela> tableViewJela;
    public TextField searchField;
    public ListView<Restorani> lvRestorani;
    private ObservableList<Restorani> listaRestorana = FXCollections.observableArrayList();
    private ObservableList<Jela> listaJela = FXCollections.observableArrayList();
    private RestoraniManager restoraniManager;
    private JelaManager jelaManager;
    public TableColumn<Jela, Integer> kolonaCijena;
    public TableColumn<Jela, String> kolonaJelo;
    public TableColumn<Jela, String> opisKolona;
    public Button naruciButton;


    public HomeController() {
        restoraniManager = new RestoraniManager();
        jelaManager = new JelaManager();
    }

    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}