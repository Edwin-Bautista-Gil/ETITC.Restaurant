package restauranteapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class controladorregistrar {
    private NavegadorEscenas navegador;

    @FXML
    private TextField nombres, apellidos, documento, usuario;

    @FXML
    private PasswordField contraseña, contraseñaverifica;

    @FXML
    private Button validar, registrar, regresar;

    @FXML
    void Regresar(ActionEvent event) {
        navegador.cambiarEscena("Menu2.fxml");
    }

    @FXML
    void Validar(ActionEvent event) {
        if (!contraseña.getText().equals(contraseñaverifica.getText())) {
            mostrarAlerta("Error", "Las contraseñas no coinciden", Alert.AlertType.ERROR);
            return;
        }

        if (registrarUsuario(
                nombres.getText(),
                apellidos.getText(),
                usuario.getText(),
                contraseña.getText(),
                documento.getText())) {
            mostrarAlerta("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);
            navegador.cambiarEscena("Bienvenidos.fxml"); // Cambia a la escena después del registro
        } else {
            mostrarAlerta("Error", "No se pudo registrar el usuario", Alert.AlertType.ERROR);
        }
    }

    private boolean registrarUsuario(String nombres, String apellidos, String usuario, String contraseña, String documento) {
        String url = "jdbc:mysql://localhost:3306/prueba1"; 
        String user = "root"; 
        String password = "1011089154brayan"; 

        String consulta = "INSERT INTO proyecto (nombres, apellidos, usuario, contraseña, documento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setString(1, nombres);
            stmt.setString(2, apellidos);
            stmt.setString(3, usuario);
            stmt.setString(4, contraseña);
            stmt.setString(5, documento);

            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0; 

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setNavegador(NavegadorEscenas navegador) {
        this.navegador = navegador;
    }
}
