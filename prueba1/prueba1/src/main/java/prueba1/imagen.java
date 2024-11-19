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