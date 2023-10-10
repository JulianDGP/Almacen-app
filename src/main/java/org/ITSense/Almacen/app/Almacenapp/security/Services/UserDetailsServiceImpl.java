package org.ITSense.Almacen.app.Almacenapp.security.Services;

import org.ITSense.Almacen.app.Almacenapp.security.Entities.Usuario;
import org.ITSense.Almacen.app.Almacenapp.security.Entities.UsuarioPrincipal;
import org.ITSense.Almacen.app.Almacenapp.security.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    //Inyeccion de dependencia por constructor:

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }
}
