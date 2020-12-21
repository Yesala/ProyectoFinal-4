package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Lista;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.UsuarioFinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TLista(nombreLista,fechaCreacion) " +
            "values (?,?)";
    private final String TEMPLATE_QRY_LISTA = "select * from TLista";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryLISTA;

    public ListaDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryLISTA = cnx.prepareStatement(TEMPLATE_QRY_LISTA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveLista(Lista nuevo) throws SQLException{
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombreLista());
            this.cmdInsertar.setDate(2, convertToSqlDate(nuevo.getFechaCreacion()));
            this.cmdInsertar.execute();
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir){
        return new java.sql.Date(fechaAConvertir.getTime());
    }


    public List<Lista> findLista() {
        ArrayList<Lista> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryLISTA.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Lista unaLista = new Lista();
            try {
                unaLista.setNombreLista(resultado.getString("nombreLista"));
                unaLista.setFechaCreacion(resultado.getDate("fechaCreacion"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unaLista);
        }
        return result;
    }

}

