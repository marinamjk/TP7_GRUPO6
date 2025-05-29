package daoImplementacion;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.SegurosDao;
import entidades.Seguros;
import entidades.TipoSeguro;

public class SegurosImplementacion implements SegurosDao {

	private static final String insert = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)";

	@Override
	public boolean insertarSeguro(Seguros seguro) {
		try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			PreparedStatement statement = conexion.prepareStatement(insert);
			statement.setString(1, seguro.getDescripcion());
			statement.setInt(2, seguro.getIdTipo());
			statement.setDouble(3, seguro.getCostoContratacion());
			statement.setDouble(4, seguro.getCostoAsegurado());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Seguros> listarSeguros() {
		List<Seguros> lista = new ArrayList<Seguros>();
	    Connection conexion = Conexion.getConexion().getSQLConexion();

	    String query = "SELECT s.idSeguro, s.descripcion, s.idTipo, s.costoContratacion, s.costoAsegurado, t.descripcion AS tipoDescripcion " +
	               "FROM seguros s INNER JOIN tiposeguros t ON s.idTipo = t.idTipo";

	    try {
	        Statement stmt = conexion.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        System.out.println("Pasó por acá");
	        
	        while (rs.next()) {
	            Seguros seguro = new Seguros();
	            seguro.setIdSeguro(rs.getInt("idSeguro"));
	            seguro.setDescripcion(manejarCaracterEspecial(rs.getString("descripcion")));
	            seguro.setIdTipo(rs.getInt("idTipo"));
	            seguro.setCostoContratacion(rs.getDouble("costoContratacion"));
	            seguro.setCostoAsegurado(rs.getDouble("costoAsegurado"));

	            System.out.println("Seguro: " + seguro.getDescripcion()); // DEBUG

	            lista.add(seguro);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
	
	public String manejarCaracterEspecial(String texto)
	{
		try {
			texto = new String(texto.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return texto;
	}

}
