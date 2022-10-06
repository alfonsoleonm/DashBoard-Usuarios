package com.proyectofullstack.demo.DAO;

import com.proyectofullstack.demo.models.Usuario;

import java.util.List;

public interface UsuarioDAO {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrarUsuario(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
