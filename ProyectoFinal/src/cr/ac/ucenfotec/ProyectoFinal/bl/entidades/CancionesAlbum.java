package cr.ac.ucenfotec.ProyectoFinal.bl.entidades;

public class CancionesAlbum {

    private Album album;
    private Cancion cancion;


    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public CancionesAlbum() {
    }

    public CancionesAlbum(Album album, Cancion cancion) {
        this.album = album;
        this.cancion = cancion;
    }

    @Override
    public String toString() {
        return "CancionesAlbum{" +
                "album=" + album +
                ", cancion=" + cancion +
                '}';
    }
}
