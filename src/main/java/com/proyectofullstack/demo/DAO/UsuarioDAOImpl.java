package com.proyectofullstack.demo.DAO;

import com.proyectofullstack.demo.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO{

    @PersistenceContext
    private EntityManager entintyManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "From Usuario";
        return entintyManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entintyManager.find(Usuario.class, id);
        entintyManager.remove(usuario);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        //populate the user
        entintyManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        //populate the user
        String query = "From Usuario WHERE email = :email ";

        List<Usuario> lista = entintyManager.createQuery(query).
                setParameter("email", usuario.getEmail()).getResultList();

        if(lista.isEmpty()){
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        return (argon2.verify(passwordHashed, usuario.getPassword()))? lista.get(0) : null;

    }
}
