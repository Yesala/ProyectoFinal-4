package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Compositor;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompositorDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TCompositor(nombre,apellido1,apellido2,paisNacimiento,edad,genero) " +
            "values (?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_COMPOSITOR = "select * from TCompositor";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryCOMPOSITOR;

    public CompositorDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryCOMPOSITOR = cnx.prepareStatement(TEMPLATE_QRY_COMPOSITOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCompositor(Compositor nuevo) throws SQLException{
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombre());
            this.cmdInsertar.setString(2, nuevo.getApellido1());
            this.cmdInsertar.setString(3, nuevo.getApellido2());
            this.cmdInsertar.setString(4, nuevo.getPaisNacimiento());
            this.cmdInsertar.setInt(5, nuevo.getEdad());
            this.cmdInsertar.setString(6, nuevo.getGenero().getNombreGenero());
            this.cmdInsertar.execute();
        }
    }

    public List<Compositor> findCompositor() {
        ArrayList<Compositor> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryCOMPOSITOR.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Compositor unCompositor = new Compositor();
            Genero unGenero = new Genero();
            try {
                unCompositor.setNombre(resultado.getString("nombre"));
                unCompositor.setApellido1(resultado.getString("apellido1"));
                unCompositor.setApellido2(resultado.getString("apellido2"));
                unCompositor.setPaisNacimiento(resultado.getString("paisNacimiento"));
                unCompositor.setEdad(resultado.getInt("edad"));
                unGenero.setNombreGenero(resultado.getString("genero"));
                unCompositor.setGenero(unGenero);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unCompositor);
        }
        return result;
    }
}
