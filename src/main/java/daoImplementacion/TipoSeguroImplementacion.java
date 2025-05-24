package daoImplementacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.TipoSegurosDao;
import entidades.TipoSeguro;

public class TipoSeguroImplementacion implements TipoSegurosDao {
	
	private static final String select = "select idTipo, descripcion from tiposeguros";
	
	
	@Override
	public ArrayList<TipoSeguro> listarTiposDeSeguros() {
		ResultSet resultSet;
		ArrayList<TipoSeguro> listaTipoSeguros = new ArrayList<TipoSeguro>();

		try {

			Connection conexion = Conexion.getConexion().getSQLConexion();
			Statement statement = conexion.createStatement();
			resultSet = statement.executeQuery(select);

			while (resultSet.next()) {
				TipoSeguro tipoSeguro = new TipoSeguro();
				tipoSeguro.setIdTipo(resultSet.getInt(1));
				tipoSeguro.setDescripcion(resultSet.getString(2));
				listaTipoSeguros.add(tipoSeguro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTipoSeguros;
	}
		

}
