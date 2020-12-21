package cr.ac.ucenfotec.ProyectoFinal.bl.entidades;

public class CancionesLista {

    private Lista lista;
    private Cancion cancion;

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public CancionesLista() {
    }

    public CancionesLista(Lista lista, Cancion cancion) {
        this.lista = lista;
        this.cancion = cancion;
    }

    @Override
    public String toString() {
        return "CancionesLista{" +
                "lista=" + lista +
                ", cancion=" + cancion +
                '}';
    }
}
