package restauranteapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Bienvenidos.fxml"));
        VBox root = loader.load();

        Scene scene = new Scene(root);

        NavegadorEscenas navegador = new NavegadorEscenas(primaryStage);

        ControladorInterfaz1 controlador = loader.getController();
        controlador.setNavegador(navegador);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Restaurante App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
