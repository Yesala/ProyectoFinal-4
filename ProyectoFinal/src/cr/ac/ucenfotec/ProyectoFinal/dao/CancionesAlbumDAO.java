package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionesAlbumDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TAlbumCancion(nombreAlbum,nombreCancion) " +
            "values (?,?)";
    private final String TEMPLATE_QRY_ALBUMCANCION = "select * from TAlbumCancion";


    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryALBUMCANCION;


    public CancionesAlbumDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryALBUMCANCION = cnx.prepareStatement(TEMPLATE_QRY_ALBUMCANCION);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCancionesAlbum(CancionesAlbum nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getAlbum().getNombreAlbum());
            this.cmdInsertar.setString(2, nuevo.getCancion().getNombreCancion());
            this.cmdInsertar.execute();
        }
    }

    public List<CancionesAlbum> findCancionesAlbums() {
        ArrayList<CancionesAlbum> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryALBUMCANCION.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            CancionesAlbum unCancionesAlbum = new CancionesAlbum();
            Album unAlbum = new Album();
            Cancion unCancion = new Cancion();
            try {
                unAlbum.setNombreAlbum(resultado.getString("nombreAlbum"));
                unCancionesAlbum.setAlbum(unAlbum);
                unCancion.setNombreCancion(resultado.getString("nombreCancion"));
                unCancionesAlbum.setCancion(unCancion);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unCancionesAlbum);
        }
        return result;
    }

}
