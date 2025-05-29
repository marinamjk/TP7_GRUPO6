package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementacion.SegurosImplementacion;
import daoImplementacion.TipoSeguroImplementacion;
import entidades.Seguros;
import entidades.TipoSeguro;

/**
 * Servlet implementation class ListarSegurosServlet
 */
@WebServlet("/ListarSegurosServlet")
public class ListarSegurosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListarSegurosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TipoSeguroImplementacion tipoDao   = new TipoSeguroImplementacion();
        SegurosImplementacion   seguroDao = new SegurosImplementacion();

        ArrayList<TipoSeguro> listaTiposSeguros = tipoDao.listarTiposDeSeguros();
        List<Seguros> listaSeguros              = seguroDao.listarSeguros();

        String filtro = request.getParameter("filtroTipoSeguros");  // viene del <select>
        if (filtro != null && !filtro.isEmpty()) {
            int idTipo = Integer.parseInt(filtro);
            listaSeguros.removeIf(s -> s.getIdTipo() != idTipo);
        }

        request.setAttribute("listaTiposSeguros", listaTiposSeguros);
        request.setAttribute("listaSeguros",      listaSeguros);

        request.getRequestDispatcher("/ListarSeguros.jsp")
               .forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
