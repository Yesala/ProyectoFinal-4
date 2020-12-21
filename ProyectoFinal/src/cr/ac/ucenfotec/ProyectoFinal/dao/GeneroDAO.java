package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Compositor;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TGenero(nombreGenero) " +
            "values (?)";
    private final String TEMPLATE_QRY_GENERO = "select * from TGenero";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryGENERO;

    public GeneroDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryGENERO = cnx.prepareStatement(TEMPLATE_QRY_GENERO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGenero(Genero nuevo) throws SQLException{
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombreGenero());
            this.cmdInsertar.execute();
        }
    }

    public List<Genero> findGenero() {
        ArrayList<Genero> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryGENERO.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Genero unGenero = new Genero();
            try {
                unGenero.setNombreGenero(resultado.getString("nombreGenero"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unGenero);
        }
        return result;
    }
}
