package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementacion.SegurosImplementacion;
import daoImplementacion.TipoSeguroImplementacion;
import entidades.Seguros;
import validaciones.Validar;

@WebServlet("/AgregarSeguroServlet")
public class AgregarSeguroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TipoSeguroImplementacion tipoDao = new TipoSeguroImplementacion();
	private Validar validarTXT = new Validar();

	public AgregarSeguroServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		TipoSeguroImplementacion dao = new TipoSeguroImplementacion();

		try {
			request.setAttribute("proxID", dao.obtenerProximoId());

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error en la consulta a BD al buscar los tipos de seguros.");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (!validarTXT.campoVacio(request.getParameter("txtDescripcion"))) {
			request.setAttribute("error", "Debe completar el campo \"Descripcion\"");
			setProximoID(request);
			request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
			return;
		}
		
		if (validarTXT.soloContieneNumero(request.getParameter("txtDescripcion"))) {
			request.setAttribute("error", " El campo \"Descripcion\" no puede contener solo números");
			setProximoID(request);
			request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
			return;
		}

		if (!validarTXT.campoVacio(request.getParameter("txtCostoContratacion"))) {
			request.setAttribute("error", "Debe completar el campo \"Costo de contración\"");
			setProximoID(request);
			request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
			return;
		}

		if (!validarTXT.campoVacio(request.getParameter("txtCostoMaximo"))) {
			request.setAttribute("error", "Debe completar el campo \"Costo maximo asegurado\"");
			setProximoID(request);
			request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
			return;
		}
		
		String descripcion = (request.getParameter("txtDescripcion"));
		int idTipo = Integer.parseInt(request.getParameter("cbTipoSeguro"));
		double costoContratacion = Double.parseDouble(request.getParameter("txtCostoContratacion"));
		double costoMaximo = Double.parseDouble(request.getParameter("txtCostoMaximo"));

		Seguros seguro = new Seguros();
		seguro.setDescripcion(descripcion);
		seguro.setIdTipo(idTipo);
		seguro.setCostoContratacion(costoContratacion);
		seguro.setCostoAsegurado(costoMaximo);

		SegurosImplementacion dao = new SegurosImplementacion();

		if (dao.insertarSeguro(seguro)) {
			request.setAttribute("mensaje", "Seguro agregado correctamente.");
		} else {
			request.setAttribute("error", "Error al agregar seguro.");
		}

		setProximoID(request);

		request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
	}

	private void setProximoID(HttpServletRequest request) {
		try {
			request.setAttribute("proxID", tipoDao.obtenerProximoId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
