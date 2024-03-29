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

/**
 * Controller class for manipulation with main window
 */
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

    /**
     * Opening new window from main home window
     * @param title title of new window
     * @param file resource file that has information about window preferences
     * @param controller controller for manipulation with new window
     */
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

    /**
     * Initializing values of javafx components on home screen
     */
    @FXML
    public void initialize(){
        try {
            listaRestorana.addAll(restoraniManager.getAll());
        } catch (OrderException e) {
            System.out.println("Something went wrong with restoraniManager");
            throw new RuntimeException(e);
        }
        kolonaCijena.setCellValueFactory(new PropertyValueFactory<Jela, Integer>("cijena"));
        kolonaJelo.setCellValueFactory(new PropertyValueFactory<Jela, String>("jelo"));
        opisKolona.setCellValueFactory(new PropertyValueFactory<Jela, String>("opis"));
        lvRestorani.setItems(listaRestorana);
        lvRestorani.getSelectionModel().selectedItemProperty().addListener((obs, prethodniRestoran, trenutniRestoran) -> {
            tableViewJela.getItems().clear();
            if(trenutniRestoran!=null) {
                try {
                    List<Jela> j = jelaManager.getAllMealsFromRestaurant(trenutniRestoran.getId());
                    listaJela.addAll(j);
                } catch (OrderException e) {
                    throw new RuntimeException(e);
                }
                tableViewJela.setItems(listaJela);
                tableViewJela.getSelectionModel().selectFirst();
            }
        });
        lvRestorani.getSelectionModel().selectFirst();
        tableViewJela.getSelectionModel().selectFirst();
        drButton.setFocusTraversable(false);
        ddButton.setFocusTraversable(false);
        djButton.setFocusTraversable(false);
    }

    public void drClick(ActionEvent actionEvent) throws IOException {
        openDialog("Dodavanje restorana", "/fxml/restaurant-layout.fxml", new RestoraniController());
    }

    public void ddClick(ActionEvent actionEvent) throws IOException {
        openDialog("Dodavanje dostavljaca", "/fxml/dostavljaci-layout.fxml", new DostavljaciController());
    }

    public void djClick(ActionEvent actionEvent) {
        openDialog("Dodavanje jela", "/fxml/jela-layout.fxml", new JelaController(lvRestorani.getSelectionModel().getSelectedItem().getId()));
    }

    public void naruciClick(ActionEvent actionEvent){
        openDialog("Narucivanje", "/fxml/narudzba-layout.fxml", new NarudzbeController(tableViewJela.getSelectionModel().getSelectedItem(),lvRestorani.getSelectionModel().getSelectedItem()));
    }

    public void dostavljaciClick(ActionEvent actionEvent) {
        openDialog("Dostavljaci Manager", "/fxml/dostavljacimanager-layout.fxml", new DostavljaciManagerController());
    }

    public void restoraniClick(ActionEvent actionEvent) {
        openDialog("Restorani Manager", "/fxml/restoranimanager-layout.fxml", new RestoraniManagerController());
    }

    public void jelaClick(ActionEvent actionEvent){
        openDialog("Jela Manager", "/fxml/jelamanager-layout.fxml", new JelaManagerController());
    }
    public void closeClick(ActionEvent actionEvent){
        Stage scenaZaZatvoriti = (Stage) naruciButton.getScene().getWindow();
        scenaZaZatvoriti.close();
    }

    public void aboutClick(ActionEvent actionEvent){
        openDialog("About Window", "/fxml/about-layout.fxml", null);
    }

    /**
     * Search bar on home window for finding a restaurants near specific location
     * @param actionEvent
     * @throws OrderException
     */
    public void search(ActionEvent actionEvent) throws OrderException {
        if(!searchField.getText().equals("")) {
            try {
                lvRestorani.setItems(FXCollections.observableList(restoraniManager.searchByLocation(searchField.getText())));
                lvRestorani.getSelectionModel().selectFirst();
                tableViewJela.getSelectionModel().selectFirst();
                lvRestorani.refresh();
            } catch (OrderException e) {
                new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            }
        }
        else{
            lvRestorani.getItems().clear();
            listaRestorana = FXCollections.observableArrayList(restoraniManager.getAll());
            lvRestorani.setItems(listaRestorana);
            lvRestorani.getSelectionModel().selectFirst();
            tableViewJela.getSelectionModel().selectFirst();
            lvRestorani.refresh();
        }
    }
}
