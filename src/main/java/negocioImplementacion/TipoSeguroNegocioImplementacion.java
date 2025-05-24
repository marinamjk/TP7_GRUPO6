package negocioImplementacion;

import java.util.ArrayList;

import daoImplementacion.TipoSeguroImplementacion;
import entidades.TipoSeguro;
import negocio.TipoSeguroNegocio;

public class TipoSeguroNegocioImplementacion implements TipoSeguroNegocio {

	@Override
	public ArrayList<TipoSeguro> listarTiposSeguros() {
		TipoSeguroImplementacion tsi= new TipoSeguroImplementacion ();
		return tsi.listarTiposDeSeguros();
	}

}
