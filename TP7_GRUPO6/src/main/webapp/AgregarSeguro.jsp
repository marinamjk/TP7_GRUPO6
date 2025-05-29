<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="negocioImplementacion.TipoSeguroNegocioImplementacion" %>
<%@ page import="entidades.TipoSeguro" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Seguro</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
 <jsp:include page="menu.jsp" />
 <h2>Agregar Seguro</h2>
 
 <form action="AgregarSeguroServlet" method="post">
 <label>Id seguro</label>
 	<%
    Object idAttr = request.getAttribute("proxID");
    String idString = (idAttr != null) ? idAttr.toString() : "1";
%>
<input type="text" readonly name="txtId" value="<%= idString %>" />
 <br>
 <label>Descripción</label><input type="text" name="txtDescripcion"><br>
 <label>Tipo de seguro</label><select name="cbTipoSeguro">
 <%
 TipoSeguroNegocioImplementacion tsi= new TipoSeguroNegocioImplementacion();
  ArrayList<TipoSeguro> listaTipoSeguros= tsi.listarTiposSeguros();
  if(listaTipoSeguros!=null){
  	for(TipoSeguro tipoSeguro: listaTipoSeguros ){
%>
	<option value="<%= tipoSeguro.getIdTipo() %>"><%= tipoSeguro.getDescripcion() %></option>
<%
	} 
  }
%>
</select>
 <br>
 <label>Costo contratación</label><input type="number" name="txtCostoContratacion"><br>
 <label>Costo máximo asegurado</label><input type="number" name="txtCostoMaximo"><br>
 
 <input type="submit" class="btn-agregar" value="Aceptar">
 </form>
 
 
 <% String mensaje = (String) request.getAttribute("mensaje");
   String error = (String) request.getAttribute("error");

   if (mensaje != null) {
%>
   <p style="color:green;"><%= mensaje %></p>
<% } else if (error != null) { %>
   <p style="color:red;"><%= error %></p>
<% } %>
</body>
</html>