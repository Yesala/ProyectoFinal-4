package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Administrador;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Artista;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Genero;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Lista;

import java.sql.Connection;;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TArtista(nombre,apellido1,apellido2,alias,paisNacimiento,fechaNacimiento,fechaDefuncion,edad,genero,referencias) " +
            "values (?,?,?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_ARTISTA = "select * from TArtista";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryARTISTA;

    public ArtistaDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryARTISTA = cnx.prepareStatement(TEMPLATE_QRY_ARTISTA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveArtista(Artista nuevo) throws SQLException{
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombre());
            this.cmdInsertar.setString(2, nuevo.getApellido1());
            this.cmdInsertar.setString(3, nuevo.getApellido2());
            this.cmdInsertar.setString(4, nuevo.getAlias());
            this.cmdInsertar.setString(5, nuevo.getPaisNacimiento());
            this.cmdInsertar.setDate(6, convertToSqlDate(nuevo.getFechaNacimiento()));
            this.cmdInsertar.setDate(7, nuevo.getFechaDefuncion() == null ? null : convertToSqlDate(nuevo.getFechaDefuncion()));
            this.cmdInsertar.setInt(8, nuevo.getEdad());
            this.cmdInsertar.setString(9, nuevo.getGenero().getNombreGenero());
            this.cmdInsertar.setString(10, nuevo.getReferencias());
            this.cmdInsertar.execute();
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir){
        return new java.sql.Date(fechaAConvertir.getTime());
    }

    public List<Artista> findArtista() {
        ArrayList<Artista> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryARTISTA.executeQuery();
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
            Genero unGenero = new Genero();
            try {
                unArtista.setNombre(resultado.getString("nombre"));
                unArtista.setApellido1(resultado.getString("apellido1"));
                unArtista.setApellido2(resultado.getString("apellido2"));
                unArtista.setAlias(resultado.getString("alias"));
                unArtista.setPaisNacimiento(resultado.getString("paisNacimiento"));
                unArtista.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
                unArtista.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
                unArtista.setFechaDefuncion(resultado.getDate("fechaDefuncion"));
                unArtista.setEdad(resultado.getInt("edad"));
                unGenero.setNombreGenero(resultado.getString("genero"));
                unArtista.setGenero(unGenero);
                unArtista.setReferencias(resultado.getString("referencias"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unArtista);
        }
        return result;
    }
}

