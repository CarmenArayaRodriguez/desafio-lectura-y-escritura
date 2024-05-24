import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        // Se crea la colección de palabras que se escribirá en el archivo.
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Perro");
        lista.add("Gato");
        lista.add("Juan");
        lista.add("Daniel");
        lista.add("Juan");
        lista.add("Gato");
        lista.add("Perro");
        lista.add("Camila");
        lista.add("Daniel");
        lista.add("Camila");

        // Se llama al método para crear y escribir en el archivo.
        crearArchivo("src/directorio", "archivo.txt", lista);
        // Se llama al método para buscar un texto específico en el archivo y contar cuántas veces aparece.
        buscarTexto("src/directorio/archivo.txt", "Juan");
    }

    public static void crearArchivo(String directorio, String archivo, ArrayList<String> palabras) {
        if (!archivo.endsWith(".txt")) {
            // Se asegura de que el nombre del archivo termine con .txt
            archivo += ".txt";
        }
        // Se crea un objeto File para el directorio.
        File carpeta = new File(directorio);
        // Se crea un objeto File para el archivo.
        File miArchivo = new File(directorio + "/" + archivo);

        // Se verifica que el directorio no existe y si no existe, lo crea.
        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.printf("Directorio '%s' creado.\n", carpeta.getName());
            } else {
                System.out.printf("Error al crear el directorio '%s'.\n", carpeta.getName());
            }
        }else {
            System.out.printf("El directorio '%s' ya existe.\n", carpeta.getName());
        }

        // Se verifica si el archivo existe.
        if (miArchivo.exists()) {
            System.out.printf("El archivo '%s' ya existe y no será sobrescrito.\n", archivo);
        } else {
            // Se intenta escribir en el archivo.
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(miArchivo))) {
                // Se usa un iterador para recorrer la lista de datos.
                Iterator<String> iterador = palabras.iterator();
                while (iterador.hasNext()) { // Verifica si hay más elementos en la colección
                    // Se escribe cada elemento de la lista en una nueva línea del archivo.
                    escritor.write(iterador.next()); // Retorna el siguiente elemento y avanza
                    escritor.newLine();
                }
                System.out.printf("Archivo '%s' creado y escrito exitosamente en el directorio '%s'.\n", archivo,carpeta.getName());
            } catch (IOException e) {
                System.out.println("Excepción al escribir en el archivo: ");
                e.printStackTrace();
            }
        }
    }

    public static void buscarTexto(String nombreArchivo, String texto) {
        // Se crea un objeto File para el archivo.
        File miArchivo = new File(nombreArchivo);
        // Se verifica si el archivo existe.
        if (!miArchivo.exists()) {
            System.out.printf("El archivo '%s' ingresado no existe.", nombreArchivo);
            System.out.println("Verifica la ruta del archivo y vuelve a intentarlo.");
        } else {
            // SE intenta leer el archivo.
            try (BufferedReader lector = new BufferedReader(new FileReader(miArchivo))) {
                String linea;
                int contador = 0;
                // Se lee el archivo línea por línea.
                while ((linea = lector.readLine()) != null) {
                    // Se verifica si la línea contiene el texto que se está buscando.
                    if (linea.contains(texto)) {
                        contador++;
                    }
                }
                // Se muestra cuántas veces aparece el texto en el archivo.
                System.out.printf("Cantidad de repeticiones del texto: '%s' -> %d", texto, contador);
            } catch (IOException e) {
                System.out.println("Excepción al leer el archivo: ");
                e.printStackTrace();
            }
        }
    }
}