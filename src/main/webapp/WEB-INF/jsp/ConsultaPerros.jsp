<%@page import="mx.com.qtx.entidades.*" %>
<%@page import="java.util.*" %>
<%@page import="mx.com.qtx.servicios.ErrorValidacion" %>
<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="java.net.URL"%>

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
	<h3>Perros registrados</h3>	


<%
	List<Perro> perros = (List<Perro>) request.getAttribute("perros");
	if(perros == null)
		perros = new ArrayList<Perro>();
%>
<% if (perros.size() > 0){ %>
	<table>
	<thead>
		<tr>
		<th>Id</th><th>nombre</th><th>raza</th><th>edad</th>
		</tr>
	</thead>
	<tbody>
	<%	for(Perro perroI : perros) { %>
		<tr> 
			<td><%=perroI.getId()%></td>
			<td><%=perroI.getNombre()%></td>
			<td><%=perroI.getRaza()%></td>
			<td><%=perroI.getEdad()%></td>
		</tr>
	<% } %>
	
	</tbody>
	</table>
<% } %>
<br>
<p>
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