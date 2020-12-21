package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionesListaDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TListaCancion(nombreLista,nombreCancion) " +
            "values (?,?)";
    private final String TEMPLATE_QRY_LISTACANCION = "select * from TListaCancion";


    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryLISTACANCION;

    public CancionesListaDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryLISTACANCION = cnx.prepareStatement(TEMPLATE_QRY_LISTACANCION);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCancionesLista(CancionesLista nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getLista().getNombreLista());
            this.cmdInsertar.setString(2, nuevo.getCancion().getNombreCancion());
            this.cmdInsertar.execute();
        }
    }

    public List<CancionesLista> findCancionesListas() {
        ArrayList<CancionesLista> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryLISTACANCION.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            CancionesLista unCancionesLista = new CancionesLista();
            Lista unLista = new Lista();
            Cancion unCancion = new Cancion();
            try {
                unLista.setNombreLista(resultado.getString("nombreLista"));
                unCancionesLista.setLista(unLista);
                unCancion.setNombreCancion(resultado.getString("nombreCancion"));
                unCancionesLista.setCancion(unCancion);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unCancionesLista);
        }
        return result;
    }
}
