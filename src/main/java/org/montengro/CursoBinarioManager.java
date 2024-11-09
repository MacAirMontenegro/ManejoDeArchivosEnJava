package org.montengro;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase de utilidad para la gestión de archivos binarios de cursos.
 */
public class CursoBinarioManager {

  private static final String DIRECTORIO = "Archivos/";

  /**
   * Escribe un objeto en un archivo binario.
   *
   * @param archivo Nombre del archivo.
   * @param objeto  Objeto a ser escrito.
   */
  public static void escribirArchivoBinario(String archivo, Object objeto) {
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DIRECTORIO + archivo))) {
      outputStream.writeObject(objeto);
      System.out.println("Se ha escrito el archivo " + archivo + " en " + DIRECTORIO);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Lee un objeto de un archivo binario.
   *
   * @param <T>     Tipo del objeto a leer.
   * @param archivo Nombre del archivo.
   * @param clase   Clase del objeto a leer.
   * @return El objeto leído del archivo.
   */
  public static <T> T leerArchivoBinario(String archivo, Class<T> clase) {
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DIRECTORIO + archivo))) {
      T objeto = (T) inputStream.readObject();
      System.out.println("Datos leídos de " + archivo + " en " + DIRECTORIO);
      return objeto;
    } catch (FileNotFoundException e) {
      System.out.println("El archivo " + archivo + " no se encontró en " + DIRECTORIO + ". Verifica la ruta y el nombre del archivo.");
      e.printStackTrace();
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Ocurrió un error al leer el archivo " + archivo + " en " + DIRECTORIO);
      e.printStackTrace();
    }
    return null;
  }
}
