package cr.ac.ucenfotec.ProyectoFinal.gestor;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.*;
import cr.ac.ucenfotec.ProyectoFinal.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gestor {

    Connection connection;
    Configuracion conf;

    UsuarioFinalDAO usuarioFinalDAO;
    AdministradorDAO administradorDAO;
    ArtistaDAO artistaDAO;
    CompositorDAO compositorDAO;
    GeneroDAO generoDAO;
    CancionDAO cancionDAO;
    ListaDAO listaDAO;
    AlbumDAO albumDAO;
    CancionesAlbumDAO cancionesAlbumDAO;
    CancionesListaDAO cancionesListaDAO;

    public Gestor() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdmusica"
                    , "root", "Yesala1104");
            this.usuarioFinalDAO = new UsuarioFinalDAO(this.connection);
            this.administradorDAO = new AdministradorDAO(this.connection);
            this.artistaDAO = new ArtistaDAO(this.connection);
            this.cancionDAO = new CancionDAO(this.connection);
            this.compositorDAO = new CompositorDAO(this.connection);
            this.generoDAO = new GeneroDAO(this.connection);
            this.listaDAO = new ListaDAO(this.connection);
            this.albumDAO = new AlbumDAO(this.connection);
            this.cancionesListaDAO = new CancionesListaDAO(this.connection);
            this.cancionesAlbumDAO = new CancionesAlbumDAO(this.connection);
        } catch (Exception e) {
            System.out.println("Cant connect to db");
            System.out.println(e.getMessage());
        }
    }

    public void crearAdministrador(String nombre, String apellido1, String apellido2, String nombreUsuarioFinal, String contrasenna, String foto, String correoElectronico) {
        Administrador administrador = new Administrador(nombre,apellido1,apellido2,nombreUsuarioFinal,contrasenna,foto,correoElectronico);
        try {
            administradorDAO.saveAdministrador(administrador);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearUsuario(String nombre, String apellido1, String apellido2, String identificacion, String paisProcedencia, int edad, String correoElectronico, String foto, String nombreUsuarioFinal, String contrasenna) {
        UsuarioFinal usuarioFinal = new UsuarioFinal(nombre,apellido1,apellido2,identificacion,paisProcedencia,edad,correoElectronico,foto,nombreUsuarioFinal,contrasenna);
        try {
            usuarioFinalDAO.saveUsuario(usuarioFinal);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearLista(String nombreLista, String fechaCreac) {
        Date fechaCreacion = null;
        try {
            fechaCreacion = new SimpleDateFormat("yyyy-MM-dd").parse(fechaCreac);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Lista unalista = new Lista(nombreLista,fechaCreacion);
        try {
            listaDAO.saveLista(unalista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarAlbum(String nombreAlbum, String fechaLanzam, String imagen, String artista) {
        Artista nombreArtista = new Artista();
        nombreArtista.setNombre(artista);
        Date fechaLanzamiento = null;
        try {
            fechaLanzamiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaLanzam);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Album album = new Album(nombreAlbum, fechaLanzamiento, imagen, nombreArtista);
        try {
            albumDAO.saveAlbum(album);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearCancion(String nombreCancion, String alias) {
        Artista aliasNombre = new Artista();
        aliasNombre.setNombre(alias);
        Cancion cancion = new Cancion(nombreCancion,aliasNombre);
        try {
            cancionDAO.saveCancion(cancion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarArtista(String nombre, String apellido1, String apellido2, String alias, String paisNacimiento, String fecNac, String fecDef, int edad, String nombreGender, String referencia) {
        Date fechaNac = null;
        Date fechaDef = null;
        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecNac);
            fechaDef = fechaDef != null ? new SimpleDateFormat("yyyy-MM-dd").parse(fecDef): null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Genero nombreDelGenero = new Genero();
        nombreDelGenero.setNombreGenero(nombreGender);
        Artista artista = new Artista(nombre,apellido1,apellido2,alias,paisNacimiento,fechaNac,fechaDef,edad,nombreDelGenero,referencia);
        try {
            artistaDAO.saveArtista(artista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarCompositor(String nombre, String apellido1, String apellido2, String paisNacimiento, int edad, String nombreGender) {
        Genero nombreGenero = new Genero();
        nombreGenero.setNombreGenero(nombreGender);
        Compositor compositor = new Compositor(nombre, apellido1, apellido2, paisNacimiento,edad,nombreGenero);
        try {
            compositorDAO.saveCompositor(compositor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarGenero(String nombreGenero) {
        Genero genero = new Genero(nombreGenero);
        try {
            generoDAO.saveGenero(genero);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listaCancion1 (CancionesAlbum cancionAlbums) throws SQLException {
        cancionesAlbumDAO.saveCancionesAlbum(cancionAlbums);
    }

    public void listaCancion2(CancionesLista cancionesLista) throws SQLException {
        cancionesListaDAO.saveCancionesLista(cancionesLista);
    }

    public List<CancionesAlbum> findCancionesAlbum(){
        return cancionesAlbumDAO.findCancionesAlbums();
    }

    public List<CancionesLista> findCancionesLista(){
        return cancionesListaDAO.findCancionesListas();
    }

    public List<Cancion> findCanciones(){
        return cancionDAO.findCancion();
    }

    public List<UsuarioFinal> findAllUsuarioFinal() {
        return usuarioFinalDAO.findUsuariosFinales();
    }

    public List<Administrador> findAdministrador(){ return administradorDAO.findAdministrador();}

    public List<Lista> findListas() {
        return listaDAO.findLista();
    }

    public List<Album> findAlbum() {
        return albumDAO.findAlbum();
    }

    public List<Genero> findGenero() {
        return generoDAO.findGenero();
    }

    public List<Compositor> findCompositor() {
        return compositorDAO.findCompositor();
    }

    public List<Artista> findArtista() {
        return artistaDAO.findArtista();
    }


}


