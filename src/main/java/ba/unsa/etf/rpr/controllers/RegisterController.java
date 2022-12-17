package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class RegisterController {
    public TextField userField = new TextField();
    public PasswordField pwField = new PasswordField();
    public PasswordField rpwField = new PasswordField();
    private Connection connection;

    @FXML
    public void initialize() {
        userField.getStyleClass().removeAll("poljeNijeIspravno");
        pwField.getStyleClass().removeAll("poljeNijeIspravno");
        rpwField.getStyleClass().removeAll("poljeNijeIspravno");
    }
}
