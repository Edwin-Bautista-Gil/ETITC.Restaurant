package restauranteapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorInterfaz1 {
    private NavegadorEscenas navegador;

    @FXML
    private Button ingresar;
    
    @FXML
    private Button registrar;

    @SuppressWarnings("exports")
    @FXML
    public void Ingresar(ActionEvent event) {
        navegador.cambiarEscena("ingresar.fxml");
    }

    @SuppressWarnings("exports")
    @FXML
    public void Registrar(ActionEvent event) {
        navegador.cambiarEscena("registrarse.fxml");
    }

    public void setNavegador(NavegadorEscenas navegador) {
        this.navegador = navegador;
    }
}
