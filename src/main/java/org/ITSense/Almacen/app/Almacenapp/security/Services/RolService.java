package org.ITSense.Almacen.app.Almacenapp.security.Services;

import org.ITSense.Almacen.app.Almacenapp.security.Entities.Rol;
import org.ITSense.Almacen.app.Almacenapp.security.Enums.RolNombre;
import org.ITSense.Almacen.app.Almacenapp.security.Repositories.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {
    private final RolRepository rolRepository;
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
