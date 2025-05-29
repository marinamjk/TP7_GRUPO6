package daoImplementacion;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.TipoSegurosDao;
import entidades.TipoSeguro;

public class TipoSeguroImplementacion implements TipoSegurosDao {
	
	private static final String select = "select idTipo, descripcion from tiposeguros";
	private static final String ultimoID = "Select max(idSeguro) as ultimoID from seguros";
	
	
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
				tipoSeguro.setDescripcion(manejarCaracterEspecial(resultSet.getString(2)));
				listaTipoSeguros.add(tipoSeguro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTipoSeguros;
	}
		

	public int obtenerProximoId() throws SQLException {
        int id = 1;
        ResultSet resultSet;
        
        try {
            Connection conexion = Conexion.getConexion().getSQLConexion();
            Statement statement = conexion.createStatement();
            resultSet = statement.executeQuery(ultimoID);

            if (resultSet.next()) {
                int ultimoID = resultSet.getInt("ultimoID");
                if (!resultSet.wasNull()) {
                    id = ultimoID + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
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
