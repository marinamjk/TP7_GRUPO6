package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementacion.TipoSeguroImplementacion;
import entidades.TipoSeguro;

/**
 * Servlet implementation class ListarSegurosServlet
 */
@WebServlet("/ListarTiposSegurosServlet")
public class ListarTiposSegurosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarTiposSegurosServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	TipoSeguroImplementacion dao = new TipoSeguroImplementacion(); 

             try {
                 ArrayList<TipoSeguro> listaTiposSeguros = dao.listarTiposDeSeguros();
                 request.setAttribute("listaTiposSeguros", listaTiposSeguros);
             } catch (Exception e) {
                 e.printStackTrace();
                 request.setAttribute("error", "Error al buscar los tipos de seguros.");
             }

             RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
             rd.forward(request, response);
             return; 

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
