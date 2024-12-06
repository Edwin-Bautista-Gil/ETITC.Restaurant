// package crearimagen;

// import java.io.BufferedReader;
// import java.io.DataOutputStream;
// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.Base64;
// import java.util.Scanner;
// import org.JSONArray;
// import org.JSONObject;


// import com.mysql.cj.xdevapi.JsonArray;

// public class apiimagen {
//     public static void main(String[] args) {
//         String apiKey = "FPSX39aa128885c641ff93cd1628b0f7e177";
//         String apiUrl = "https://api.freepik.com/v1/ai/text-to-image";
//         Scanner scanner = new Scanner(System.in);

//         String imagenesDir = "imagenes";

//         // Crear la carpeta "imagenes" si no existe
//         File directorio = new File(imagenesDir);
//         if (!directorio.exists()) {
//             if (directorio.mkdir()) {
//                 System.out.println("Carpeta 'imagenes' creada exitosamente.");
//             } else {
//                 System.out.println("Error al crear la carpeta 'imagenes'. Verifique los permisos.");
//                 return;
//             }
//         }

//         // Crear conexión con la base de datos
//         DataBase db = new DataBase("imagenes_tabla");
//         db.CreateTable();

//         try {
//             System.out.print("Ingrese la cantidad de imágenes que desea generar: ");
//             int numImages = Integer.parseInt(scanner.nextLine());

//             for (int i = 1; i <= numImages; i++) {
//                 System.out.print("Ingrese la descripción (prompt) para la imagen " + i + ": ");
//                 String prompt = scanner.nextLine();

//                 // Crear conexión
//                 URL obj = new URL(apiUrl);
//                 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//                 con.setRequestMethod("POST");
//                 con.setRequestProperty("Content-Type", "application/json");
//                 con.setRequestProperty("x-freepik-api-key", apiKey);
//                 con.setDoOutput(true);

//                 // Construir JSON de solicitud
//                 String jsonInputString = "{"
//                         + "\"prompt\": \"" + prompt + "\","
//                         + "\"negative_prompt\": \"b&w, earth, cartoon, ugly\","
//                         + "\"guidance_scale\": 2,"
//                         + "\"seed\": 42,"
//                         + "\"num_images\": 1,"
//                         + "\"image\": {\"size\": \"square\"},"
//                         + "\"styling\": {"
//                         + "\"style\": \"photo\","
//                         + "\"color\": \"pastel\","
//                         + "\"lightning\": \"warm\","
//                         + "\"framing\": \"portrait\""
//                         + "}"
//                         + "}";

//                 try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//                     wr.writeBytes(jsonInputString);
//                     wr.flush();
//                 }

//                 // Leer respuesta
//                 int responseCode = con.getResponseCode();
//                 if (responseCode == HttpURLConnection.HTTP_OK) {
//                     BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                     String inputLine;
//                     StringBuilder response = new StringBuilder();

//                     while ((inputLine = in.readLine()) != null) {
//                         response.append(inputLine);
//                     }
//                     in.close();

//                     String jsonResponse = response.toString();
//                     System.out.println("Respuesta JSON para imagen " + i + ": " + jsonResponse);

//                     // Extraer imagen en formato Base64
//                     String imageBase64 = extractImageBase64(jsonResponse);

//                     if (imageBase64 != null) {
//                         // Decodificar y guardar imagen en la carpeta "imagenes"
//                         String filePath = imagenesDir + "/imagen_" + i + ".png";
//                         saveImage(imageBase64, filePath);

//                         // Guardar imagen en la base de datos
//                         byte[] imageData = Base64.getDecoder().decode(imageBase64);
//                         db.insertData(
//                                 i, // Valor
//                                 "Imagen " + i, // Nombre
//                                 prompt, // Tipo (usamos el prompt como tipo)
//                                 1, // Cantidad
//                                 "Imagen generada a partir del prompt: " + prompt, // Descripción
//                                 "IMG_" + i, // Codigonum
//                                 imageData // Imagen
//                         );

//                         System.out.println("Imagen " + i + " guardada en la carpeta 'imagenes' y en la base de datos.");
//                     } else {
//                         System.out.println("No se pudo extraer la imagen de la respuesta para imagen " + i + ".");
//                     }
//                 } else {
//                     System.out.println("Error en la solicitud para imagen " + i + ": Código de respuesta " + responseCode);
//                 }
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     private static <JSONObject> String extractImageBase64(String jsonResponse) {
//         JSONObject jsonObject = new JSONObject(jsonResponse);
//         try {
//             JsonArray dataArray = jsonObject.getJSONArray("data");
//             if (dataArray.length() > 0) {
//                 JSONObject firstDataObject = dataArray.getJSONObject(0);
//                 return firstDataObject.getString("base64");
//             }
//         } catch (Exception e) {
//             System.out.println("Error al extraer la imagen: " + e.getMessage());
//         }
//         return null;
//     }

//     private static void saveImage(String base64Image, String filePath) {
//         try {
//             byte[] imageData = Base64.getDecoder().decode(base64Image);
//             try (FileOutputStream fos = new FileOutputStream(filePath)) {
//                 fos.write(imageData);
//                 fos.flush();
//             }
//         } catch (IOException e) {
//             System.out.println("Error al guardar la imagen: " + e.getMessage());
//         }
//     }
// }
