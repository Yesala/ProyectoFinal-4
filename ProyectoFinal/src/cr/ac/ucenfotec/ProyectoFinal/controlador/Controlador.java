package cr.ac.ucenfotec.ProyectoFinal.controlador;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.*;
import cr.ac.ucenfotec.ProyectoFinal.gestor.Gestor;
import cr.ac.ucenfotec.ProyectoFinal.iu.IU;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controlador {

    private IU iu = new IU();
    private Gestor gestor = new Gestor();

    public void ejecutarPrograma() {
        int opcion = 0;
        do {
            iu.mostrarMenu();
            opcion = iu.leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 17);

    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crearAdministrador();
                break;
            case 2:
                crearUsuario();
                break;
            case 3:
                crearCancion();
                break;
            case 4:
                crearLista();
                break;
            case 5:
                agruparAlbum();
                break;
            case 6:
                agregarArtistas();
                break;
            case 7:
                agregarCompositores();
                break;
            case 8:
                agregarGeneros();
            case 9:
                listarUsuarios();
                break;
            case 10:
                listarListas();
                break;
            case 11:
                listarAlbum();
                break;
            case 12:
                listarArtistas();
                break;
            case 13:
                listarCompositor();
                break;
            case 14:
                listarGenero();
            case 15:
                listarAdministrador();
                break;
            case 16:
                listarCanciones();
            case 17:
                iu.imprimir("Gracias por usar nuestra APP");
        }
    }

    private void crearAdministrador() {
        iu.imprimir("Nombre del Usuario Administrador");
        String nombre = iu.leerString();
        iu.imprimir("Primer apellido");
        String apellido1 = iu.leerString();
        iu.imprimir("Segundo apellido");
        String apellido2 = iu.leerString();
        iu.imprimir("Crear nombre de usuario");
        String nombreUsuarioFinal = iu.leerString();
        iu.imprimir("Crear contraseña");
        String contrasenna = iu.leerString();
        iu.imprimir("Adjunte una fotografía");
        String foto = iu.leerString();
        iu.imprimir("Ingrese su correo electrónico");
        String correoElectronico = iu.leerString();
        gestor.crearAdministrador(nombre,apellido1,apellido2,nombreUsuarioFinal,contrasenna,foto,correoElectronico);

    }

    private void crearUsuario() {
        iu.imprimir("Nombre del usuario");
        String nombre = iu.leerString();
        iu.imprimir("Primer apellido");
        String apellido1 = iu.leerString();
        iu.imprimir("Segundo apellido");
        String apellido2 = iu.leerString();
        iu.imprimir("Ingrese su numero de identificacion");
        String identificacion = iu.leerString();
        iu.imprimir("País de procedencia");
        String paisProcedencia = iu.leerString();
        iu.imprimir("Ingrese su edad");
        int edad = iu.leerEntero();
        iu.imprimir("Ingrese su correo electrónico");
        String correoElectronico = iu.leerString();
        iu.imprimir("Adjunte una fotografía");
        String foto = iu.leerString();
        iu.imprimir("Crear nombre de usuario");
        String nombreUsuarioFinal = iu.leerString();
        iu.imprimir("Crear contraseña");
        String contrasenna = iu.leerString();
        gestor.crearUsuario(nombre,apellido1,apellido2,identificacion,paisProcedencia,edad,correoElectronico,foto,nombreUsuarioFinal,contrasenna);
    }

    private void crearCancion() {
        iu.imprimir("Nombre de la canción");
        String nombreCancion = iu.leerString();
        iu.imprimir("Nombre del artista");
        String alias = iu.leerString();
        gestor.crearCancion(nombreCancion,alias);
    }

    private void crearLista(){
        iu.imprimir("Ingrese el nombre de la lista: ");
        String nombreLista = iu.leerString();
        iu.imprimir("Fecha de creacion");
        String fechaLista = iu.leerString();
        gestor.crearLista(nombreLista,fechaLista);
    }

    private void agruparAlbum(){
        iu.imprimir("Ingrese el nombre del Album: ");
        String nombreAlbum = iu.leerString();
        iu.imprimir("Ingrese la fecha de lanzamiento: ");
        String fechaLanzam = iu.leerString();
        iu.imprimir("Agregue una fotografía del album");
        String imagen = iu.leerString();
        iu.imprimir("Nombre del artista");
        String artista = iu.leerString();
        gestor.agregarAlbum(nombreAlbum,fechaLanzam,imagen,artista);
    }

    private void agregarArtistas() {
        iu.imprimir("Nombre del artista");
        String nombre = iu.leerString();
        iu.imprimir("Primer apellido");
        String apellido1 = iu.leerString();
        iu.imprimir("Segundo apellido");
        String apellido2 = iu.leerString();
        iu.imprimir("Alias o nombre Artístico");
        String alias = iu.leerString();
        iu.imprimir("País de nacimiento");
        String paisNacimiento = iu.leerString();
        iu.imprimir("Fecha de nacimiento");
        String fechaNacimiento = iu.leerString();
        iu.imprimir("Fecha de defuncion");
        String fechaDefuncion = iu.leerString();
        iu.imprimir("Edad");
        int edad = iu.leerEntero();
        iu.imprimir("Genero musical");
        String nombreGenero = iu.leerString();
        iu.imprimir("Referencias");
        String referencia = iu.leerString();
        gestor.agregarArtista(nombre,apellido1,apellido2,alias,paisNacimiento,fechaNacimiento, null,edad,nombreGenero,referencia);
    }

    private void agregarCompositores() {
        iu.imprimir("Nombre del artista");
        String nombre = iu.leerString();
        iu.imprimir("Primer apellido");
        String apellido1 = iu.leerString();
        iu.imprimir("Segundo apellido");
        String apellido2 = iu.leerString();
        iu.imprimir("País de nacimiento");
        String paisNacimiento = iu.leerString();
        iu.imprimir("Edad");
        int edad = iu.leerEntero();
        iu.imprimir("Genero(s) musical");
        String nombreGenero = iu.leerString();
        gestor.agregarCompositor(nombre,apellido1,apellido2,paisNacimiento,edad,nombreGenero);
    }

    private void agregarGeneros() {
        iu.imprimir("Nombre del género musical");
        String nombreGenero = iu.leerString();
        gestor.agregarGenero(nombreGenero);
    }

    private void listarAdministrador() {
        List<Administrador> unAdministrador = gestor.findAdministrador();
        for(Administrador unoAdministrador : unAdministrador){
            iu.imprimir(unoAdministrador.toString());
        }
    }

    private void listarUsuarios() {
        List<UsuarioFinal> unUsuarioFinal = gestor.findAllUsuarioFinal();
        for(UsuarioFinal unoUsuarioFinal : unUsuarioFinal){
            iu.imprimir(unoUsuarioFinal.toString());
        }
    }

    private void listarCanciones() {
        List<Cancion> unCancion = gestor.findCanciones();
        for(Cancion unaCancion : unCancion){
            iu.imprimir(unaCancion.toString());
        }
    }

    private void listarListas() {
        List<Lista> unLista = gestor.findListas();
        for(Lista unaLista : unLista){
            iu.imprimir((unaLista.toString()));
        }
    }

    private void listarAlbum() {
        List<Album> unAlbum = gestor.findAlbum();
            for(Album unoAlbum : unAlbum){
                iu.imprimir(unoAlbum.toString());
            }
        }

    private void listarGenero() {
        List<Genero> unGenero = gestor.findGenero();
            for(Genero unoGenero : unGenero){
                iu.imprimir(unoGenero.toString());
            }
        }

    private void listarCompositor() {
        List<Compositor> unCompositor = gestor.findCompositor();
            for(Compositor unoCompositor : unCompositor){
                iu.imprimir(unoCompositor.toString());
            }
        }

    private void listarArtistas() {
        List<Artista> unArtista = gestor.findArtista();
            for(Artista unoArtista : unArtista){
                iu.imprimir(unoArtista.toString());
            }
        }

    }