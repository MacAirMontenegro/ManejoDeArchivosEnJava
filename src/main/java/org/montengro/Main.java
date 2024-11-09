package org.montengro;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que contiene el punto de entrada del programa.
 */
public class Main {
  /**
   * Método principal que ejecuta el programa.
   *
   * @param args Argumentos de línea de comandos.
   */
  public static void main(String[] args) {
    crearDirectorio("Archivos");

    Alumno alumno = new Alumno("Cristian", "Montenegro", 33, LocalDateTime.now());
    Alumno alumno2 = new Alumno("Alonso", "De La Cruz", 33, LocalDateTime.now());
    Curso curso = new Curso("DAM2", "Desarollo de Aplicaciones Multiplataforma", new ArrayList<>());
    agregarAlumnoACurso(curso, alumno);
    agregarAlumnoACurso(curso, alumno2);

    List<Curso> cursos = new ArrayList<>();
    cursos.add(curso);
    cursos.add(new Curso("DAM", "DAM", new ArrayList<>()));

    imprimirCurso(curso);
    imprimirCursos(cursos);

    CursoBinarioManager.escribirArchivoBinario("curso.dat", curso);
    CursoBinarioManager.escribirArchivoBinario("cursos.dat", (ArrayList<Curso>) cursos);

    CursoBinarioManager.leerArchivoBinario("curso.dat", Curso.class);
    CursoBinarioManager.leerArchivoBinario("cursos.dat", ArrayList.class);

    CursoJsonManager.serializarAJson(curso, "curso.json");
    CursoJsonManager.serializarAJson(cursos, "cursos.json");

    Curso cursoLeidoJson = CursoJsonManager.deserializarDeJson("curso.json", Curso.class);
    Curso[] cursosLeidosJson = CursoJsonManager.deserializarDeJson("cursos.json", Curso[].class);

    imprimirCurso(cursoLeidoJson);
    imprimirCursos(List.of(cursosLeidosJson));

    CursoXmlManager.serializarAXml(curso, "curso.xml");
    CursoXmlManager.serializarAXml(cursos, "cursos.xml");

    Curso cursoLeidoXml = CursoXmlManager.deserializarDeXml("curso.xml", Curso.class);
    Curso[] cursosLeidosXml = CursoXmlManager.deserializarDeXml("cursos.xml", Curso[].class);

    imprimirCurso(cursoLeidoXml);
    imprimirCursos(List.of(cursosLeidosXml));
  }

  /**
   * Crea un directorio con el nombre especificado.
   *
   * @param nombreDirectorio Nombre del directorio a crear.
   */
  private static void crearDirectorio(String nombreDirectorio) {
    File directorio = new File(nombreDirectorio);
    if (!directorio.exists()) {
      if (directorio.mkdirs()) {
        System.out.println("Directorio " + nombreDirectorio + " creado.");
      } else {
        System.out.println("No se pudo crear el directorio " + nombreDirectorio + ".");
      }
    } else {
      System.out.println("Directorio " + nombreDirectorio + " ya existe.");
    }
  }

  /**
   * Imprime la información de una lista de cursos.
   *
   * @param cursos Lista de cursos a imprimir.
   */
  private static void imprimirCursos(List<Curso> cursos) {
    cursos.forEach(curso -> {
      System.out.println("- " + curso.getCodigo() + " " + curso.getTitulo());
      curso.getAlumnos().forEach(a -> {
        System.out.println("  - " + a.getNombre() + " " + a.getApellido() + " " + a.getEdad());
      });
    });
  }

  /**
   * Imprime la información de un curso.
   *
   * @param curso Curso a imprimir.
   */
  private static void imprimirCurso(Curso curso) {
    System.out.println(curso.getCodigo() + " " + curso.getTitulo());
    curso.getAlumnos().forEach(a -> {
      System.out.println("- " + a.getNombre() + " " + a.getApellido() + " " + a.getEdad());
    });
  }

  /**
   * Valida un curso.
   *
   * @param curso Curso a validar.
   * @return true si el curso es válido, false en caso contrario.
   */
  private static boolean validarCurso(Curso curso) {
    return curso != null && curso.getCodigo() != null && curso.getTitulo() != null;
  }

  /**
   * Agrega un alumno a un curso.
   *
   * @param curso  Curso al que se desea agregar el alumno.
   * @param alumno Alumno a agregar.
   */
  private static void agregarAlumnoACurso(Curso curso, Alumno alumno) {
    if (validarCurso(curso) && alumno != null) {
      curso.getAlumnos().add(alumno);
    } else {
      System.out.println("Datos del curso o del alumno son inválidos.");
    }
  }

  /**
   * Elimina un alumno de un curso.
   *
   * @param curso  Curso del que se desea eliminar el alumno.
   * @param alumno Alumno a eliminar.
   */
  private static void eliminarAlumnoDeCurso(Curso curso, Alumno alumno) {
    if (validarCurso(curso) && curso.getAlumnos().contains(alumno)) {
      curso.getAlumnos().remove(alumno);
    } else {
      System.out.println("El alumno no está registrado en este curso.");
    }
  }

  /**
   * Muestra la información de una lista de alumnos.
   *
   * @param alumnos Lista de alumnos a mostrar.
   */
  private static void mostrarAlumnos(List<Alumno> alumnos) {
    if (alumnos != null) {
      alumnos.forEach(alumno -> {
        System.out.println(alumno.getNombre() + " " + alumno.getApellido() + ", Edad: " + alumno.getEdad());
      });
    } else {
      System.out.println("No hay alumnos para mostrar.");
    }
  }

  /**
   * Genera un informe de cursos y sus alumnos.
   *
   * @param cursos Lista de cursos.
   */
  private static void generarInformeDeCursos(List<Curso> cursos) {
    if (cursos != null) {
      cursos.forEach(curso -> {
        System.out.println("Curso: " + curso.getCodigo() + " - " + curso.getTitulo());
        mostrarAlumnos(curso.getAlumnos());
        System.out.println("----");
      });
    } else {
      System.out.println("No hay cursos para mostrar.");
    }
  }
}
