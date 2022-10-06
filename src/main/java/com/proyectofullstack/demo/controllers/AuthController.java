package com.proyectofullstack.demo.controllers;

import com.proyectofullstack.demo.DAO.UsuarioDAO;
import com.proyectofullstack.demo.models.Usuario;
import com.proyectofullstack.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDAO usuarioDAO; //este objeto estara compartido en memoria

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
        Usuario usuarioLoggeado = usuarioDAO.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLoggeado!=null){
            String tokenJWT = jwtUtil.create(String.valueOf(usuarioLoggeado.getId()), usuarioLoggeado.getEmail());
            return tokenJWT;
        }else{
            return "FAIL";
        }
    }
}
