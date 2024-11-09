package org.montengro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

/**
 * Clase de utilidad para la gestión de la serialización y deserialización de objetos a/desde archivos JSON.
 */
public class CursoJsonManager {

  private static final ObjectMapper objectMapper = inicializarObjectMapper();
  private static final String DIRECTORIO = "Archivos/";

  /**
   * Inicializa y configura el ObjectMapper.
   *
   * @return Un ObjectMapper configurado.
   */
  private static ObjectMapper inicializarObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  /**
   * Serializa un objeto a un archivo JSON.
   *
   * @param objeto  Objeto a serializar.
   * @param archivo Nombre del archivo donde se guardará el JSON.
   */
  public static void serializarAJson(Object objeto, String archivo) {
    try {
      objectMapper.writeValue(new File(DIRECTORIO + archivo), objeto);
      System.out.println("Se han exportado los datos a " + archivo + " en " + DIRECTORIO);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Deserializa un objeto desde un archivo JSON.
   *
   * @param <T>     Tipo del objeto a deserializar.
   * @param archivo Nombre del archivo JSON.
   * @param clase   Clase del objeto a deserializar.
   * @return El objeto deserializado.
   */
  public static <T> T deserializarDeJson(String archivo, Class<T> clase) {
    try {
      T objeto = objectMapper.readValue(new File(DIRECTORIO + archivo), clase);
      System.out.println("Datos leídos de " + archivo + " en " + DIRECTORIO);
      return objeto;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
