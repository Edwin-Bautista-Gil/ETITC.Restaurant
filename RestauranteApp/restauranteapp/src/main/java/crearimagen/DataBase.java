// package crearimagen;
// import java.sql.*;
// public class DataBase {

//     private static final String URL = "jdbc:mysql://localhost:3306/menu";
//     private static final String USER = "root";
//     private static final String PASSWORD = "123456789";
//     private static String table_name;

//     public DataBase(String table_name) {
//         DataBase.table_name = table_name;
//     }

//     public static Connection getConnection() throws SQLException {
//         return DriverManager.getConnection(URL, USER, PASSWORD);
//     }

//     public void CreateTable() {
//         String query = "CREATE TABLE IF NOT EXISTS " + table_name + " ("
//                 + "Valor INT PRIMARY KEY, "
//                 + "Nombre VARCHAR(100) NOT NULL, "
//                 + "Tipo VARCHAR(100) NOT NULL, "
//                 + "Cantidad INT NOT NULL, "
//                 + "Descripcion VARCHAR(200) NOT NULL, "
//                 + "Codigonum VARCHAR(50) NOT NULL, "
//                 + "Imagen LONGBLOB NOT NULL"
//                 + ");";

//         try (Connection connection = getConnection();
//              Statement stmt = connection.createStatement()) {
//             stmt.executeUpdate(query);
//             System.out.println("Tabla '%s' creada o ya existe.".formatted(table_name));
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public void insertData(int valor, String nombre, String tipo, int cantidad, String descripcion, String codigonum, byte[] imageData) {
//         String query = "INSERT INTO %s (Valor, Nombre, Tipo, Cantidad, Descripcion, Codigonum, Imagen) VALUES (?, ?, ?, ?, ?, ?, ?)".formatted(table_name);

//         try (Connection connection = getConnection();
//              PreparedStatement preparedStatement = connection.prepareStatement(query)) {

//             preparedStatement.setInt(1, valor);
//             preparedStatement.setString(2, nombre);
//             preparedStatement.setString(3, tipo);
//             preparedStatement.setInt(4, cantidad);
//             preparedStatement.setString(5, descripcion);
//             preparedStatement.setString(6, codigonum);
//             preparedStatement.setBytes(7, imageData);

//             int rowsInserted = preparedStatement.executeUpdate();
//             System.out.println("Datos insertados correctamente. Filas afectadas: " + rowsInserted);
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }

// /*En esta clase DataBase se genera la tabla con el mysql y con la conexion se envia la esturcutra de la tabla
// poniendo nuestros datos de mysql*/