package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Album;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Artista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TAlbum(nombreAlbum,fechaLanzamiento,imagen,nombreArtista) " +
            "values (?,?,?,?)";
    private final String TEMPLATE_QRY_ALBUM = "select * from TAlbum";


    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryALBUM;

    public AlbumDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryALBUM = cnx.prepareStatement(TEMPLATE_QRY_ALBUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAlbum(Album nuevo) throws SQLException{
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombreAlbum());
            this.cmdInsertar.setDate(2, convertToSqlDate(nuevo.getFechaLanzamiento()));
            this.cmdInsertar.setString(3, nuevo.getImagen());
            this.cmdInsertar.setString(4, nuevo.getArtista().getNombre());
            this.cmdInsertar.execute();
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir){
        return new java.sql.Date(fechaAConvertir.getTime());
    }


    public List<Album> findAlbum() {
        ArrayList<Album> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryALBUM.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Album unAlbum = new Album();
            Artista unArtista = new Artista();
            try {
                unAlbum.setNombreAlbum(resultado.getString("nombreAlbum"));
                unAlbum.setFechaLanzamiento(resultado.getDate("fechaLanzamiento"));
                unAlbum.setImagen(resultado.getString("imagen"));
                unArtista.setNombre(resultado.getString("nombreArtista"));
                unAlbum.setArtista(unArtista);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unAlbum);
        }
        return result;
    }
}