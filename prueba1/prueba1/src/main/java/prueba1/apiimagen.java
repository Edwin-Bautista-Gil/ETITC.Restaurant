package prueba1;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

public class apiimagen {
    public static void main(String[] args) {
        String apiKey = "FPSX39aa128885c641ff93cd1628b0f7e177";
        String apiUrl = "https://api.freepik.com/v1/ai/text-to-image";

        try {
            URL obj = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("x-freepik-api-key", apiKey);
            con.setDoOutput(true);

            String jsonInputString = "{"
                    + "\"prompt\": \"sopa de pollo con arroz\","
                    + "\"negative_prompt\": \"b&w, earth, cartoon, ugly\","
                    + "\"guidance_scale\": 2,"
                    + "\"seed\": 42,"
                    + "\"num_images\": 1,"
                    + "\"image\": {\"size\": \"square\"},"
                    + "\"styling\": {"
                    + "\"style\": \"photo\","
                    + "\"color\": \"pastel\","
                    + "\"lightning\": \"warm\","
                    + "\"framing\": \"portrait\""
                    + "}"
                    + "}";

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(jsonInputString);
                wr.flush();
            }

            // Leer respuesta
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
                System.out.println("Respuesta JSON: " + jsonResponse);

                // Extraer imagen en formato Base64
                String imageBase64 = extractImageBase64(jsonResponse);

                if (imageBase64 != null) {
                    byte[] imageData = Base64.getDecoder().decode(imageBase64);

                    DataBase db = new DataBase("proyecto");
                    db.CreateTable();

                    
                    db.insertData(14000, "Sopa de Pollo", "Comida", 1, "Sopa caliente con arroz", "SP001", imageData);
                    db.insertData(16000, "Hamburguesa combo", "Comida", 1, "Hamburguesa con lechuga, tomate,queso, maiz tierno y papas fritas", "SP002", imageData);
                    db.insertData(10000, "Perro caliente sencillo", "Comida", 2, "Perro caliente, salchicha, papas fosforito, queso", "SP003", imageData);
                    db.insertData(6000, "Sandwich", "Comida", 3, "pan tostado, queso, jamon, lechuga", "SP004", imageData);
                    db.insertData(20000, "Bandeja paisa", "Comida", 1, "arroz, frijoles, aguacate, patacon, chorizo, chicharron, huevo, carne molida y limonada", "SP001", imageData);
                    System.out.println("Datos e imagen almacenados en la base de datos correctamente.");
                } else {
                    System.out.println("No se pudo extraer la imagen de la respuesta.");
                }
            } else {
                System.out.println("Error en la solicitud: Código de respuesta " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractImageBase64(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        try {
            JSONArray dataArray = jsonObject.getJSONArray("data");
            if (dataArray.length() > 0) {
                JSONObject firstDataObject = dataArray.getJSONObject(0);
                return firstDataObject.getString("base64");
            }
        } catch (Exception e) {
            System.out.println("Error al extraer la imagen: " + e.getMessage());
        }
        return null;
    }
}

/*Este código genera una imagen usando la API de Freepik, la convierte de Base64 a binario, y almacena
 la imagen junto con información de alimentos (precio, nombre, descripción, etc.) en una tabla MySQL.
  Usa la clase DataBase para crear la tabla */