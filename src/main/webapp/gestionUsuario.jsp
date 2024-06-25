<%@ page import="mx.edu.utez.pruebaf.dao.UserDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mx.edu.utez.pruebaf.model.User" %><%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 24/06/2024
  Time: 06:35 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Estado</th>
                <th colspan="2">Acciones</th>
            </tr>
        </thead>
        <thbody>
            <%
            UserDao dao = new UserDao();
            ArrayList <User> lista = dao.getAll();
            for(User u : lista){ %>
            <tr>
                <td><%=u.getId()%></tr>
                <td><%=u.getNombre()%></td>
                <td><%=u.getCorreo()%></td>
                <td><%=u.isEstado() ? "Activo":"Inactivo"%></td>
                <td><a>Actualizar</a></td>
                <td><a>Eliminar</a></td>
            <% }
            %>
            </tr>
        </thbody>
    </table>
</body>
</html>
