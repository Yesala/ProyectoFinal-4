package cr.ac.ucenfotec.ProyectoFinal.bl.entidades;

/**
 * @author Yensy Salazar Jiménez
 * Clase Genero que contiene los atributos asociados a un género musical.
 * Atributos: idGenero, nombreGenero.
 */

public class Genero {

    private String nombreGenero;

    /**
     * Métodos de acceso get y set de los atributos de la clase Genero
     */

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    /**
     * Constructor vacío de la Clase Genero
     */

    public Genero() {
    }

    /**
     * Constructor con parámetros de la Clase Genero
     * @param nombreGenero Nombre de un género musical
     */

    public Genero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    /**
     * String que imprime los datos de los atributos de la clase Genero
     */

    @Override
    public String toString() {
        return nombreGenero;
    }
}
