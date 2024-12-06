package restauranteapp;

import java.sql.*;

public class UserModel {
    private static final String URL = "jdbc:mysql://localhost:3306/usuarios";
    private static final String USER = "root";
    private static final String PASSWORD = "1011089154brayan";

    public boolean registrarUsuario(String nombres, String apellidos, String usuario, String contraseña, String documento) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO usuarios (nombres, apellidos, usuario, contraseña, documento) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombres);
            statement.setString(2, apellidos);
            statement.setString(3, usuario);
            statement.setString(4, contraseña);
            statement.setString(5, documento);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validarUsuario(String usuario, String contraseña) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usuario);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
