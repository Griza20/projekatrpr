package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * JavaFX main class that shows first scene of application Sistem-narucivanja v1.0
 */
public class AppFX extends Application {
    /**
     * Main runnable method
     * @param primarystage
     * @throws Exception
     */
    public void start(Stage primarystage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login-layout.fxml")));
        primarystage.setTitle("Login Screen");
        primarystage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primarystage.setResizable(false);
        primarystage.show();
    }
}
