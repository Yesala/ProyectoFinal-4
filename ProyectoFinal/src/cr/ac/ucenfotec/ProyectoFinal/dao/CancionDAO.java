package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Artista;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Cancion;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TCancion(nombreCancion,nombreArtista) " +
            "values (?,?)";
    private final String TEMPLATE_QRY_CANCION = "select * from TCancion";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryCANCION;

    public CancionDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryCANCION = cnx.prepareStatement(TEMPLATE_QRY_CANCION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCancion(Cancion nuevo) throws SQLException{
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombreCancion());
            this.cmdInsertar.setString(2, nuevo.getArtista().getNombre());
            this.cmdInsertar.execute();
        }
    }

    public List<Cancion> findCancion() {
        ArrayList<Cancion> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryCANCION.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Artista unArtista = new Artista();
            Cancion unCancion = new Cancion();
            try {
                unCancion.setNombreCancion(resultado.getString("nombreCancion"));
                unArtista.setNombre(resultado.getString("nombreArtista"));
                unCancion.setArtista(unArtista);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unCancion);
        }
        return result;
    }

}

