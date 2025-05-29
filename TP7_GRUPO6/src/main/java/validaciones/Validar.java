package validaciones;

public class Validar {

	public boolean campoVacio(String campo) {
		if (campo.isEmpty() || campo == null)
			return false;

		return true;
	}
	
	public boolean soloContieneNumero(String campo)
	{
		if(campo.matches("\\d+"))
			return true;
		
		return false;
	}
}
