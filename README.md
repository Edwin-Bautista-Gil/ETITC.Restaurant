# Planteamiento del problema:

En la Escuela Tecnológica Instituto Técnico Central (ETITC), la gestión de los almuerzos y el control
de los alimentos en la cooperativa presenta varios desafíos que afectan tanto la eficiencia
operativa como el bienestar de los estudiantes. Actualmente, no existe un sistema adecuado para
controlar la asistencia de los estudiantes que reciben el almuerzo ni para gestionar el inventario de
alimentos de forma óptima. Esta situación genera varios problemas, como el desperdicio de
alimentos debido a una mala planificación, la entrega desigual de almuerzos, y una sobrecarga de
trabajo administrativo que podría ser mitigada con el uso de tecnología. Como resultado, los
recursos disponibles no se aprovechan de manera eficiente, lo que afecta tanto el rendimiento de
los estudiantes como la sostenibilidad del servicio.

# Justificación:

La implementación de este proyecto es esencial debido a los problemas que actualmente afectan
tanto la eficiencia en la distribución de los almuerzos como la gestión de los recursos en la
cooperativa. A continuación, se detallan los principales motivos que justifican la necesidad de este
proyecto:

**Desperdicio de alimentos:** La falta de us sitema adecuado de control de inventarios y de asistencia contribuyente a un alto nivel de desperdicio de alimentos. Sin una supervisión precisa de cuántos estudiantes asistirán y recibirán almuerzos diariamente, se prepararn cantidades que pueden no coincidir con la demanda real, resultando en desperdicio o escasez. Reducir el desperdicio es fundamental no solo desde el punto de vista económico, sino tambien por el impacto ambiental y social que implica una gestión ineficiente de los recursos.

**Falta de control en la distribución de almuerzos:** El actual proceso de entrega de almuerzos carece de un sistema estructurado que garantice que todos los estudiantes que asisten y tienen derecho al almuerzo reciban su porción correspondiente. Un registro preciso de la asistencia diaria permitirá asegurar que los almoerzos se distribuyan de manera equitativa, minimizando situaciones en las que estudiantes se queden sin recibir alimento o se prepare en exceso.

**Ineficiencia en la gestión de inventarios**
La carencia de un sistema de inventarios eficiente impacta la capacidad de la cooperativa para gestionar adecuadamente los recursos alimentarios. Esto genera compras innecesarias o insuficientes de insumos, afectando los costos operativos y la planificación de las compras. Un sistema de inventario automatizado facilitará el seguimiento de los productos disponibles, optimizando las compras y evitando que los alimentos se desperdicien por caducidad o mal uso.

**- Mejora de la Nutrición y el Rendimiento Estudiantil:** Una adecuada alimentación es fundamental
para el bienestar y rendimiento académico de los estudiantes. Al implementar un sistema de
control más preciso, se asegurará que todos los estudiantes que asisten a clases reciban una
comida equilibrada y a tiempo, lo que puede mejorar su concentración, su salud general y su
participación en el entorno educativo.

**- Modernización y Eficiencia Administrativa:** El uso de tecnología para la gestión de inventarios y el
registro de asistencia no solo aumentará la eficiencia de los procesos, sino que también liberará al
personal encargado de tareas manuales repetitivas, permitiéndoles centrarse en otras
responsabilidades. Además, este enfoque facilitará una toma de decisiones más informada y
basada en datos, lo que contribuirá a una mejora continua en el manejo de los recursos y el
servicio alimentario.


# Objetivo general:

Implementar un sistema integral de gestión que optimice la distribución de almuerzos de los estudiantes de la ETITC mediante el control automatizado de la asistencia y un manejo eficiente de los inventarios de alimentos en la cooperativa escolar, con el fin de reducir el desperdicio de recursos, garantizar la entrega equitativa de los almuerzos y mejorar la eficiencia administrativa de servicio alimentario.

# Objetivo especificos:

**-Desarrollar un sistema de registro de almuerzos diarios.**

**-Controlar el acceso de cada estudiante al almuerzo diario.**

**-Automatizar el monitoreo de asistencia.**

**-Generar reportes sobre el consumo de almuerzo.**

**-Asegurar la trazabilidad y transparencia del servicio.**

**-Ofrecer acceso a los estudiantes a su historia de almuerzos.**

**-Generar estadisticas para mostrar el consumo de almuerzos.**

# Codigo:

**Clase apiimagen:**

```java
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
```

**Clase DataBase:**

```java
package prueba1;
import java.sql.*;
public class DataBase {

    private static final String URL = "jdbc:mysql://localhost:3306/prueba1";
    private static final String USER = "root";
    private static final String PASSWORD = "1011089154brayan";
    private static String table_name;

    public DataBase(String table_name) {
        DataBase.table_name = table_name;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void CreateTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + table_name + " ("
                + "Valor INT PRIMARY KEY, "
                + "Nombre VARCHAR(100) NOT NULL, "
                + "Tipo VARCHAR(100) NOT NULL, "
                + "Cantidad INT NOT NULL, "
                + "Descripcion VARCHAR(200) NOT NULL, "
                + "Codigonum VARCHAR(50) NOT NULL, "
                + "Imagen LONGBLOB NOT NULL"
                + ");";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Tabla '%s' creada o ya existe.".formatted(table_name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(int valor, String nombre, String tipo, int cantidad, String descripcion, String codigonum, byte[] imageData) {
        String query = "INSERT INTO %s (Valor, Nombre, Tipo, Cantidad, Descripcion, Codigonum, Imagen) VALUES (?, ?, ?, ?, ?, ?, ?)".formatted(table_name);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, valor);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, tipo);
            preparedStatement.setInt(4, cantidad);
            preparedStatement.setString(5, descripcion);
            preparedStatement.setString(6, codigonum);
            preparedStatement.setBytes(7, imageData);

            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Datos insertados correctamente. Filas afectadas: " + rowsInserted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*En esta clase DataBase se genera la tabla con el mysql y con la conexion se envia la esturcutra de la tabla
poniendo nuestros datos de mysql*/
```

**Clase Imagen:**

```java
package prueba1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class imagen {
    public static void saveImage(String base64Image, String fileName) {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(imageBytes);
            System.out.println("Imagen guardada como " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*La clase imagen se usa para guardar imágenes en formato Base64 como archivos físicos en el sistema y 
decodifica el String Base64(base64Image) a un arreglo de bytes y escribe esos bytes en un archivo con 
el nombre especificado (fileName)*/
```

**Dependencias pom:**

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>prueba1</groupId>
    <artifactId>prueba1</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.1.0</version>
        </dependency>

        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20210307</version>
        </dependency>

        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.13</version>
        </dependency>
    </dependencies>
</project>
```
# Explicacion del código:

# -Codigo apiimagen:
Este código se conecta a la API de Freepik para generar una imagen a partir de una descripción textual y luego almacenar los datos de esa imagen en una base de datos.

**Generación de la imagen:** El código apiimagen es el que genera una imagen usando la API de Freepik, que toma una descripción textual como entrada.

**Extracción de la imagen:** Cuando tenemos la respuesta JSON de la API, la imagen se extrae en formato Base64.
Almacenamiento en la base de datos: Con la imagen en Base64, el código crea una tabla en la base de datos usando el método CreateTable de la clase DataBase, y luego inserta los datos (como el nombre, tipo, cantidad, etc.) junto con la imagen en la base de datos usando el método insertData.

**Almacenamiento local:** La imagen también puede ser almacenada localmente en el disco usando el método saveImage de la clase Imagen. Este paso no es invocado directamente en el código apiimagen, pero es útil para guardar la imagen en tu computadora.
Propiedades de la clase:

**URL:** La URL de conexión a la base de datos MySQL (en este caso, está apuntando a una base de datos llamada prueba1 en un servidor local).

*USER y PASSWORD:* Las credenciales de acceso a la base de datos.
*table_name:* El nombre de la tabla que se va a manejar.
*Constructor:* Recibe un nombre de tabla y lo asigna a la variable estática table_name.
*Método getConnection:* Establece y devuelve una conexión a la base de datos usando el DriverManager.getConnection con los parámetros URL, USER, y PASSWORD.
*Método CreateTable:*Este método crea una tabla en la base de datos con el nombre especificado en el constructor, si la tabla no existe.

*La tabla tiene las siguientes columnas:*

-Valor: un valor entero que es la clave primaria.
-Nombre, Tipo, Descripcion, Codigonum: columnas de texto que contienen información de los productos o elementos a almacenar.
-Cantidad: un entero que representa la cantidad del producto.
-Imagen: un campo LONGBLOB para almacenar imágenes en formato binario.

*Método insertData:* Inserta datos en la tabla creada, como el valor, nombre, tipo, cantidad, descripción, código y la imagen (en formato binario) usando un PreparedStatement.

**Descripción de la conexión HTTP:**

La aplicación realiza una solicitud HTTP POST a la API de Freepik (https://api.freepik.com/v1/ai/text-to-image), usando una clave de API (la dan al registrarnos en Freepik).
En el cuerpo de la solicitud JSON, se especifica un prompt (una descripción de lo que se quiere en la imagen) y algunos parámetros de estilo (color, iluminación, encuadre, etc.).

*Solicitud y respuesta de la API:*

-La respuesta de la API es un JSON que contiene, entre otras cosas, la imagen generada en formato Base64. 
-La respuesta es leída y procesada para extraer el string de la imagen en formato Base64.

*Procesamiento de la imagen:* El código extrae la imagen en Base64 desde la respuesta JSON.
Luego, esa imagen Base64 se convierte a un arreglo de bytes (para almacenarla en la base de datos).

*Almacenamiento en base de datos:* Con los datos extraídos de la API, se crea un objeto DataBase y se utiliza el método CreateTable para asegurarse de que la tabla esté creada.
Se inserta la imagen y otros datos (como nombre, tipo, cantidad, descripción y código) en la base de datos utilizando el método insertData.

# -Codigo imagen:
Este código tiene la responsabilidad de guardar una imagen en el disco local a partir de una cadena de texto Base64.

*Método saveImage:*

-Toma una cadena Base64 (que representa una imagen) y el nombre del archivo como parámetros.
-Decodifica la cadena Base64 en un arreglo de bytes.
-Luego, guarda esos bytes en un archivo de imagen en el sistema de archivos local, usando un FileOutputStream.

# Resumen general:

*El sistema en su conjunto permite:*

- Generar imágenes automáticamente a partir de descripciones textuales usando la API de Freepik.
- Almacenar las imágenes y datos asociados en una base de datos MySQL.
- Guardar las imágenes localmente en el disco.

El flujo principal es la integración entre la API externa (Freepik) y la base de datos local para almacenar y gestionar imágenes generadas y asociarlas a productos o elementos que luego podrían ser consultados y/o modificados.

# Referencias:
- https://www.comedoresblanco.es/estrategia-reducir-desperdicio-alimentos-escolares/
- https://www.scielo.cl/scielo.php?pid=S0717-75182011000400009&script=sci_arttext&tlng=pt
- https://repository.udistrital.edu.co/server/api/core/bitstreams/b4273d59-394b-4727-8d20-0f4ed4f7160a/content
- https://chatgpt.com/
- https://gemini.google.com/app?hl=es
