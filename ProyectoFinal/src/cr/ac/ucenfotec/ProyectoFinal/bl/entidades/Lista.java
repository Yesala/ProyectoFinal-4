package cr.ac.ucenfotec.ProyectoFinal.bl.entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Yensy Salazar Jiménez
 * Clase Lista que contiene los atributos asociados a una lista de canciones.
 * Atributos: nombreLista, fechaCreacion, ArrayList<Cancion>.
 */

public class Lista {

    private String nombreLista;
    private Date fechaCreacion;
    private ArrayList<CancionesLista> cancionesListas;

    /**
     * Métodos de acceso get y set de los atributos de la clase Lista
     */

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Constructor vacío de la Clase Lista
     */

    public Lista() {
    }

    /**
     * Constructor con parámetros de la Clase Lista
     * @param nombreLista Nombre de la lista que se esta creando
     * @param fechaCreacion Fecha de creación de la lista
     */

    public Lista(String nombreLista, Date fechaCreacion) {
        this.nombreLista = nombreLista;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * String que imprime los datos de los atributos de la clase Lista
     */

    @Override
    public String toString() {
        return nombreLista;
    }

    /*
    @Override
    public String toString() {
        return "Lista{" +
                "nombreLista='" + nombreLista + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
     */

}
