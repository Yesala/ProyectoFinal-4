package cr.ac.ucenfotec.ProyectoFinal.dao;

import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.Administrador;
import cr.ac.ucenfotec.ProyectoFinal.bl.entidades.UsuarioFinal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into TAdministrador(nombre,apellido1,apellido2,nombreUsuario,contrasenna,foto,correoElectronico) " +
            "values (?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_ADMINISTRATIVO = "select * from TAdministrador";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryADMINISTRATIVO;

    public AdministradorDAO(Connection connection) {
        this.cnx = connection;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryADMINISTRATIVO = cnx.prepareStatement(TEMPLATE_QRY_ADMINISTRATIVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAdministrador(Administrador nuevo) throws SQLException{
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombre());
            this.cmdInsertar.setString(2, nuevo.getApellido1());
            this.cmdInsertar.setString(3, nuevo.getApellido2());
            this.cmdInsertar.setString(4, nuevo.getNombreUsuario());
            this.cmdInsertar.setString(5, nuevo.getContrasenna());
            this.cmdInsertar.setString(6, nuevo.getFoto());
            this.cmdInsertar.setString(7, nuevo.getCorreoElectronico());
            this.cmdInsertar.execute();
        }
    }

    public List<Administrador> findAdministrador() {
        ArrayList<Administrador> result = new ArrayList<>();
        ResultSet resultado = null;
        try {
            resultado = this.queryADMINISTRATIVO.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultado.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Administrador unAdministrador = new Administrador();
            try {
                unAdministrador.setNombre(resultado.getString("nombre"));
                unAdministrador.setApellido1(resultado.getString("apellido1"));
                unAdministrador.setApellido2(resultado.getString("apellido2"));
                unAdministrador.setNombreUsuario(resultado.getString("nombreUsuario"));
                unAdministrador.setContrasenna(resultado.getString("contrasenna"));
                unAdministrador.setFoto(resultado.getString("foto"));
                unAdministrador.setCorreoElectronico(resultado.getString("correoElectronico"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result.add(unAdministrador);
        }
        return result;
    }

}
