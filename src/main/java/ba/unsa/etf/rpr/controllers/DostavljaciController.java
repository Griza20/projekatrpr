package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DostavljaciManager;
import ba.unsa.etf.rpr.domain.Dostavljaci;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;

public class DostavljaciController {
    public Button dodajButton;
    public DatePicker datumField;
    public TextField brojField;
    public TextField prezimeField;
    public TextField imeField;
    public Button izlazButton;
    public RadioButton zenskoButton, muskoButton;
    public Slider visinaSlider;
    public CheckBox vozackaBox;

    DostavljaciManager dostavljaciManager;

    public DostavljaciController(){ dostavljaciManager = new DostavljaciManager(); }
}
