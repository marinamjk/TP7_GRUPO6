import java.util.ArrayList;

import entidades.TipoSeguro;
import negocioImplementacion.TipoSeguroNegocioImplementacion;

public class Principal {

	public static void main(String[] args) {
		 TipoSeguroNegocioImplementacion tsi= new TipoSeguroNegocioImplementacion();
		  ArrayList<TipoSeguro> listaTipoSeguros= tsi.listarTiposSeguros();
		  for(TipoSeguro ts: listaTipoSeguros) {
			  System.out.println(ts.toString());
		  }
	}

}
