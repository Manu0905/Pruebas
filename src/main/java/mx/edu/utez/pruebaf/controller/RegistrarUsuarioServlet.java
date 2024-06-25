package mx.edu.utez.pruebaf.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pruebaf.dao.UserDao;
import mx.edu.utez.pruebaf.model.User;

import java.io.IOException;

@WebServlet (name = "RegistrarUsuarioServlet", value="/sign_in")
public class RegistrarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String correo = req.getParameter("correo");
        String contra1 = req.getParameter("contra1");
        String contra2 = req.getParameter("contra2");
        if(!contra1.equals(contra2)){
            resp.sendRedirect("registrarUsuario.jsp");
        }
        User u = new User();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setContra(contra1);

        UserDao dao = new UserDao();
        boolean insercion = dao.insert(u);

        if(insercion){
            //Mandar al usuario al inicio de sesion
            resp.sendRedirect("index.jsp");
        }else{
            //Mandar un mensaje de entrar y regresar al formulario de registro
            HttpSession sesion = req.getSession();
            sesion.setAttribute("mensaje", "No se puede registrar el usuario");
            resp.sendRedirect("index.jsp");
        }
    }

}
