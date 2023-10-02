/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import model.Usuario;
import dao.UsuarioDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GuardarUsuarioServlet", urlPatterns = {"/GuardarUsuarioServlet"})
public class GuardarUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String rut = request.getParameter("rut");
        String email = request.getParameter("email");

        // Crear un objeto Usuario con los datos del formulario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEdad(edad);
        usuario.setRut(rut);
        usuario.setEmail(email);

        // Conectar a la base de datos y guardar el usuario
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_de_datos", "tu_usuario", "tu_contraseña");
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            usuarioDAO.crearUsuario(usuario);
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar cualquier excepción aquí
            e.printStackTrace();
        }

        // Redirigir a una página de éxito o mostrar un mensaje
        response.sendRedirect("exito.jsp"); // Cambia "exito.jsp" al nombre de tu página de éxito
    }
}
