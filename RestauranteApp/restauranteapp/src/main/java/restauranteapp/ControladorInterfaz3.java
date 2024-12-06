package restauranteapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControladorInterfaz3 {
    private NavegadorEscenas navegador;

    @FXML
    private TextField Revisarusuario, Revisarcontraseña;

    @FXML
    private Button ingresarmenu, regresarinicio;

    // Botón para intentar ingresar al menú después de validar usuario y contraseña
    @FXML
    void Ingresarmenu(ActionEvent event) {
        // Obtiene los datos ingresados en los TextField
        String usuario = Revisarusuario.getText().trim();
        String contrasena = Revisarcontraseña.getText().trim();

        // Valida si el usuario existe en la base de datos
        if (validarUsuario(usuario, contrasena)) {
            mostrarAlerta("Éxito", "Inicio de sesión exitoso", Alert.AlertType.INFORMATION);
            navegador.cambiarEscena("Bienvenidos.fxml"); // Cambia a la escena del menú
        } else {
            mostrarAlerta("Error", "Credenciales incorrectas. Intente nuevamente.", Alert.AlertType.ERROR);
        }
    }

    
    @FXML
    void Regresainicio(ActionEvent event) {
        navegador.cambiarEscena("Bienvenidos.fxml"); 
    }

    private boolean validarUsuario(String usuario, String contrasena) {
        String url = "jdbc:mysql://localhost:3306/usuarios"; 
        String user = "root"; 
        String password = "1011089154brayan";  

        String consulta = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); 

        } catch (Exception e) {
            e.printStackTrace(); 
            mostrarAlerta("Error", "No se pudo conectar a la base de datos. Revise la configuración.", Alert.AlertType.ERROR);
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
