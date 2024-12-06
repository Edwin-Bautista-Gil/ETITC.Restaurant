package restauranteapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavegadorEscenas {
    private Stage stage;

    public NavegadorEscenas(Stage stage) {
        this.stage = stage;
    }

    public void cambiarEscena(String archivoFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(archivoFXML));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar la escena: " + archivoFXML);
        }
    }
}
