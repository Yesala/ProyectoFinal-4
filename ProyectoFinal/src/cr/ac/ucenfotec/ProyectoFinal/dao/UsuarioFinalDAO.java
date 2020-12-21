package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.UsuarioFinal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioFinalDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TUsuario(nombre,apellido1,apellido2,identificacion,paisProcedencia,edad,correoElectronico,foto,nombreUsuarioFinal,contrasenna) " +
            "values (?,?,?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_USUARIOFINAL = "select * from TUsuario";


    private final Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryUSUARIOFINAL;

    public UsuarioFinalDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryUSUARIOFINAL = cnx.prepareStatement(TEMPLATE_QRY_USUARIOFINAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUsuario(UsuarioFinal nuevo) throws SQLException{
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombre());
            this.cmdInsertar.setString(2, nuevo.getApellido1());
            this.cmdInsertar.setString(3, nuevo.getApellido2());
            this.cmdInsertar.setString(4, nuevo.getIdentificacion());
            this.cmdInsertar.setString(5, nuevo.getPaisProcedencia());
            this.cmdInsertar.setInt(6, nuevo.getEdad());
            this.cmdInsertar.setString(7, nuevo.getCorreoElectronico());
            this.cmdInsertar.setString(8, nuevo.getFoto());
            this.cmdInsertar.setString(9, nuevo.getNombreUsuarioFinal());
            this.cmdInsertar.setString(10, nuevo.getContrasenna());
            this.cmdInsertar.execute();
        }
    }

    public List<UsuarioFinal> findUsuariosFinales() {
        ArrayList<UsuarioFinal> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryUSUARIOFINAL.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            UsuarioFinal unUsuario = new UsuarioFinal();
            try {
                unUsuario.setNombre(resultado.getString("nombre"));
                unUsuario.setApellido1(resultado.getString("apellido1"));
                unUsuario.setApellido2(resultado.getString("apellido2"));
                unUsuario.setIdentificacion(resultado.getString("identificacion"));
                unUsuario.setPaisProcedencia(resultado.getString("paisProcedencia"));
                unUsuario.setEdad(resultado.getInt("edad"));
                unUsuario.setCorreoElectronico(resultado.getString("correoElectronico"));
                unUsuario.setFoto(resultado.getString("foto"));
                unUsuario.setNombreUsuarioFinal(resultado.getString("nombreUsuarioFinal"));
                unUsuario.setContrasenna(resultado.getString("contrasenna"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unUsuario);
        }
        return result;
    }


}

