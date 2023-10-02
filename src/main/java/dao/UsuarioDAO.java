/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, apellido, edad, rut, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setInt(3, usuario.getEdad());
            statement.setString(4, usuario.getRut());
            statement.setString(5, usuario.getEmail());
            statement.executeUpdate();
        }
    }

    public void editarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET nombre=?, apellido=?, edad=?, rut=?, email=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setInt(3, usuario.getEdad());
            statement.setString(4, usuario.getRut());
            statement.setString(5, usuario.getEmail());
            statement.setInt(6, usuario.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setEdad(resultSet.getInt("edad"));
                usuario.setRut(resultSet.getString("rut"));
                usuario.setEmail(resultSet.getString("email"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}


