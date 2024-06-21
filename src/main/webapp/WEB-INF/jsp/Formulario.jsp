<%@page import="java.util.*" %>
<%@page import="mx.com.qtx.servicios.ErrorValidacion" %>
<%@page import="java.net.URL"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
            
<%! private Logger bitacora = LoggerFactory.getLogger(this.getClass()); %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perro</title>
<style>
body{
 padding: 40px;
}
h3{
	text-align: center;
}
form{
	margin-top: 20px;
	width: 60%;
	margin-left: 20%;
}
label{
	display:inline-block;
	width: 5rem;
}
input[type="text"]{
   border-radius: 3px;
   padding:3px;
}
table{
	margin-top: 20px;
	width: 60%;
	margin-left: 20%;
	margin-right: auto;
	border-collapse: collapse;
}
th,td{
	border: 1px solid hsl(120,90%,50%);
	padding: 4px;
}
tr:nth-child(even) {
	background-color: hsl(150,90%,90%)
}
table.debug th, table.debug td{
	border: 1px solid hsl(180,80%,30%);
	padding: 4px;
}
table.debug tr:nth-child(even) {
	background-color: hsl(150,30%,90%)
}
p{
	text-align: center;
}
span{
	color: blue;
	font-weight: bold;
}
</style>
</head>
<body>
<%
	Map<String,String> paramsPerro =(Map<String, String>) request.getAttribute("params");
	if(paramsPerro == null)
		paramsPerro= new HashMap<String,String>();
	String mensaje = (String) request.getAttribute("mensaje");
	mensaje = mensaje == null ? "" : mensaje;
	String idPerro = paramsPerro.getOrDefault("id", "");
	String edad = paramsPerro.getOrDefault("edad", "");
	String nombre = paramsPerro.getOrDefault("nombre", "");
	String raza = paramsPerro.getOrDefault("raza", "");
	List<ErrorValidacion> listErr = (List<ErrorValidacion>) request.getAttribute("errores");
%>
<h3>Alta de Perros</h3>
	<form action="./AltaPerro" method="post">
		<label for="idPerro">Id:</label>
		<input type="text" id="idPerro" name="id" value="<%=idPerro%>"><br><br>
		<label for="nombrePerro">Nombre:</label>
		<input type="text" id="nombrePerro" name="nombre" value="<%=nombre%>"><br><br>
		<label for="edadPerro">Edad:</label>
		<input type="text" id="edadPerro"  name="edad"  value="<%=edad%>"><br><br>
		<label for="razaPerro">Raza:</label>
		<input type="text" id="razaPerro"  name="raza" value="<%=raza%>"><br><br>
		<input type="hidden" name="vista" value="formAlta">
		<input type="hidden" name="operacion" value="registro">
		<input type="submit" value="Registrar">
	</form>
<% if (listErr != null && listErr.size() > 0){ %>
	<table>
	<thead>
		<tr>
		<th>Campo</th><th>Valor</th>
		</tr>
	</thead>
	<tbody>
	<%	for(ErrorValidacion errI : listErr) { %>
		<tr> 
			<td><%=errI.getCampo()%></td>
			<td><%=errI.getDescripcion()%></td>
		</tr>
	<% } %>
	
	</tbody>
	</table>
<% } %>
<br>
<p>
<span><%=mensaje%></span>
</p>
<a href="./regresar">Regresar a menu principal</a>

<% 
   String modo = (String)application.getAttribute("modo");
   bitacora.info("modo:" + modo);

   if (modo != null && modo.equalsIgnoreCase("debug")){ 
   %>
   <br>
   <hr>
   <br>
   <table class="debug">
   	<tr>
      	<td>Este JSP se está ejecutando en la fecha/hora siguiente:</td>
      	<td> <%= new java.util.Date() %></td>
	</tr>
   	<tr>
   		<td>La clase de este JSP es:</td>
   		<td><%=page.getClass().getName() %></td>
	</tr>
   	<tr>
   		<td>El Servlet generado está en:</td>
   		<td><%=page.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath() %></td>
   		<% 	
   			URL urlServlet = page.getClass().getProtectionDomain().getCodeSource().getLocation();
   		%>
	</tr>
   	<tr>
   		<td>Id de la sesión es:</td>
   		<td><%=session.getId() %></td>
	</tr>
    <tr>
   		<td>Método http:</td>
   		<td><%=request.getMethod() %></td>
	</tr>
     <tr>
   		<td>pageContext:</td>
   		<td><%=pageContext.getClass().getName() %></td>
	</tr>
     <tr>
   		<td>application:</td>
   		<td><%=application.getContextPath()%></td>
	</tr>
   </table>
<% } %>

</body>
</html>