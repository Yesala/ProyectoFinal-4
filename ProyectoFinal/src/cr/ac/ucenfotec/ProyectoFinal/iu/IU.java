package cr.ac.ucenfotec.ProyectoFinal.iu;

import java.io.PrintStream;
import java.util.Scanner;

public class IU {

    private PrintStream output = new PrintStream(System.out);
    private Scanner input = new Scanner(System.in).useDelimiter("\n");

    public void mostrarMenu() {
        output.println("Bievenido a la Applicacion SING");
        output.println("Seleccione una opción:");
        output.println(" ");
        output.println("1. Crear Administrador");
        output.println("2. Crear Usuario");
        output.println("3. Agregar canción");
        output.println("4. Crear Lista");
        output.println("5. Crear Album");
        output.println("6. Agregar artista");
        output.println("7. Agregar compositor");
        output.println("8. Agregar género");
        output.println("9. Listar Usuarios");
        output.println("10. Listar Playlists");
        output.println("11. Listar Albums");
        output.println("12. Listar Artistas");
        output.println("13. Listar Compositor");
        output.println("14. Listar Generos Musicales");
        output.println("15. Listar Administrador");
        output.println("16. Listar Canciones");
        output.println("17. Salir");
    }

    public int leerOpcion() {
        output.println("Su opcion es: ");
        return input.nextInt();
    }

    public  void imprimir(String msg){
        output.println(msg);
    }

    public String leerString(){
        return input.next();
    }

    public int leerEntero(){
        return input.nextInt();
    }
}