package org.montengro;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

/**
 * Clase de utilidad para la gestión de la serialización y deserialización de objetos a/desde archivos XML.
 */
public class CursoXmlManager {

  private static final XmlMapper xmlMapper = inicializarXmlMapper();
  private static final String DIRECTORIO = "Archivos/";

  /**
   * Inicializa y configura el XmlMapper.
   *
   * @return Un XmlMapper configurado.
   */
  private static XmlMapper inicializarXmlMapper() {
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    xmlMapper.registerModule(new JavaTimeModule());
    return xmlMapper;
  }

  /**
   * Serializa un objeto a un archivo XML.
   *
   * @param objeto  Objeto a serializar.
   * @param archivo Nombre del archivo donde se guardará el XML.
   */
  public static void serializarAXml(Object objeto, String archivo) {
    try {
      xmlMapper.writeValue(new File(DIRECTORIO + archivo), objeto);
      System.out.println("Se han exportado los datos a " + archivo + " en " + DIRECTORIO);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Deserializa un objeto desde un archivo XML.
   *
   * @param <T>     Tipo del objeto a deserializar.
   * @param archivo Nombre del archivo XML.
   * @param clase   Clase del objeto a deserializar.
   * @return El objeto deserializado.
   */
  public static <T> T deserializarDeXml(String archivo, Class<T> clase) {
    try {
      T objeto = xmlMapper.readValue(new File(DIRECTORIO + archivo), clase);
      System.out.println("Datos leídos de " + archivo + " en " + DIRECTORIO);
      return objeto;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
