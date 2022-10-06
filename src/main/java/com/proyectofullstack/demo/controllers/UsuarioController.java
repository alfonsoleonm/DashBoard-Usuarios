package com.proyectofullstack.demo.controllers;

import com.proyectofullstack.demo.DAO.UsuarioDAO;
import com.proyectofullstack.demo.models.Usuario;
import com.proyectofullstack.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO; //este objeto estara compartido en memoria

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "usuarios/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Alfonso");
        usuario.setApellido("Leon");
        usuario.setEmail("aleonmacedo@hotmail.com");
        usuario.setTelefono("2124214124");
        usuario.setPassword("leon123");
        return usuario;
    }

    //lista de usuarios
    @RequestMapping(value = "usuarios")
    //el value de authorization es el del header en usuarios.js
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){
        if(!validarToken(token)){return null;}
        return usuarioDAO.getUsuarios();
    }

    //modificar un usuario

    //eliminar usuario
    @RequestMapping(value = "usuarios/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if(!validarToken(token)){return;}
        usuarioDAO.eliminar(id);
    }

    //buscar usuario

    //crear un usuario
    @RequestMapping(value = "usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());//1=cantidad de iteraciones, entre mas iteraciones mas segura, pero mas lenta

        usuario.setPassword(hash);
        usuarioDAO.registrarUsuario(usuario);
    }

    private boolean validarToken(String token){
        //verificamos que el token sea correcto
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId!=null;
    }

}
