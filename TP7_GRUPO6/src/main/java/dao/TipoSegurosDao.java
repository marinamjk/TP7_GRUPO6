package dao;

import java.util.ArrayList;

import entidades.TipoSeguro;

public interface TipoSegurosDao {
	public ArrayList<TipoSeguro> listarTiposDeSeguros();
	public String manejarCaracterEspecial(String texto);
}
