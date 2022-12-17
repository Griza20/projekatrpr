package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.util.Objects;
import java.util.Properties;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {
    public Button loginButton;
    public GridPane loginScreen;
    public TextField userField = new TextField();
    public PasswordField pwField = new PasswordField();

    private Connection connection;

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
}
