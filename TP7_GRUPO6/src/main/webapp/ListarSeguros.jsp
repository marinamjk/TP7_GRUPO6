<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.TipoSeguro,entidades.Seguros"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Seguros</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<jsp:include page="menu.jsp"/>

<%
    List<TipoSeguro> listaTipoSeguros = (List<TipoSeguro>) request.getAttribute("listaTiposSeguros");
    List<Seguros>    listaSeguros     = (List<Seguros>)    request.getAttribute("listaSeguros");
%>

<!-- Formulario de filtro -->
<form action="ListarSegurosServlet" method="post">
    <label>Búsqueda por tipo de seguro: </label>
    <select name="filtroTipoSeguros">
        <option value="">-- Todos --</option>
        <% for (TipoSeguro t : listaTipoSeguros) { %>
            <option value="<%=t.getIdTipo()%>"><%=t.getDescripcion()%></option>
        <% } %>
    </select>
    <input type="submit" value="Filtrar">
</form>

<!-- Tabla -->
<h3>Listado de Seguros</h3>
<table border="1">
    <tr>
        <th>ID</th><th>Descripción</th><th>Tipo</th>
        <th>Costo Contratación</th><th>Costo Asegurado</th>
    </tr>

    <% if (listaSeguros != null && !listaSeguros.isEmpty()) {
           for (Seguros s : listaSeguros) { %>
        <tr>
            <td><%=s.getIdSeguro()%></td>
            <td><%=s.getDescripcion()%></td>
            <td>
                <% for (TipoSeguro t : listaTipoSeguros)
                       if (t.getIdTipo() == s.getIdTipo()) { out.print(t.getDescripcion()); break; } %>
            </td>
            <td><%=s.getCostoContratacion()%></td>
            <td><%=s.getCostoAsegurado()%></td>
        </tr>
    <%     }
       } else { %>
        <tr><td colspan="5">No hay seguros cargados.</td></tr>
    <% } %>
</table>

</body>
</html>
