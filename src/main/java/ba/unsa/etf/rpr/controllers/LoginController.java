package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * Controller class for manipulation with login
 */
public class LoginController {
    public Button loginButton;
    public GridPane loginScreen;
    public TextField userField = new TextField();
    public PasswordField pwField = new PasswordField();

    private Connection connection;

    /**
     * This method takes care about initial colors of fields
     */
    @FXML
    public void initialize() {
        userField.getStyleClass().removeAll("poljeJeIspravno");
        userField.getStyleClass().removeAll("poljeNijeIspravno");
        userField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (userField.getText().trim().isEmpty()) {
                    userField.getStyleClass().removeAll("poljeJeIspravno");
                    userField.getStyleClass().add("poljeNijeIspravno");
                } else {
                    userField.getStyleClass().removeAll("poljeNijeIspravno");
                    userField.getStyleClass().add("poljeJeIspravno");
                }
            }
        });
    }

    /**
     * Validates the user from database and logs into application
     * @param actionEvent
     */
    public void loginClick(ActionEvent actionEvent) {
        //Konektovanje na bazu
        try{
            FileReader reader = new FileReader("C:\\Users\\User\\IdeaProjects\\projekatrpr\\src\\main\\resources\\baza.properties");
            Properties property = new Properties();
            property.load(reader);
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Baza?sessionVariables=WAIT_TIMEOUT=28800",property.getProperty("username"),property.getProperty("password"));
        }catch(Exception e){
            System.out.println("Nije uspostavljena konekcija sa bazom.");
            e.printStackTrace();
        }
        String query = "SELECT username, password FROM Korisnici";
        List<String> results = new ArrayList<String>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            boolean logintest = false;
            String us = userField.getText(), pass = pwField.getText();
            while (rs.next()){
                String user = rs.getString("username");
                String pw = rs.getString("password");
                if(user.equals(us) && pw.equals(pass)){
                    logintest = true;
                    break;
                }
            }
            rs.close();
            if(logintest){
                try {
                    final Stage loginStage = (Stage) loginScreen.getScene().getWindow();
                    Stage myStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home-layout.fxml"));
                    loader.load();
                    HomeController homeController = loader.getController();
                    myStage.setTitle("Sistem naručivanja v1.0");
                    myStage.setScene(new Scene((Parent) loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    myStage.setResizable(false);
                    myStage.show();
                    Stage scenaZaZatvoriti = (Stage) loginButton.getScene().getWindow();
                    scenaZaZatvoriti.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                Alert poruka = new Alert(Alert.AlertType.INFORMATION);
                poruka.setTitle("Poruka");
                poruka.setContentText("Pogrešan unos podataka.");
                poruka.showAndWait();
                userField.requestFocus();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Opening new window for registration of a new user
     * @param actionEvent
     */
    public void registerClick(ActionEvent actionEvent) {
        try {
            final Stage loginStage = (Stage) loginScreen.getScene().getWindow();
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register-layout.fxml"));
            loader.load();
            RegisterController registerController = loader.getController();
            myStage.setTitle("Register Screen");
            myStage.setScene(new Scene((Parent) loader.getRoot(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.setResizable(false);
            myStage.show();
            loginStage.hide();
            myStage.setOnHiding(event -> {
                loginStage.show();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
