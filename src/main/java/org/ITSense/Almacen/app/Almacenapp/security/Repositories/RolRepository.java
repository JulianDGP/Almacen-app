package org.ITSense.Almacen.app.Almacenapp.security.Repositories;

import org.ITSense.Almacen.app.Almacenapp.security.Entities.Rol;
import org.ITSense.Almacen.app.Almacenapp.security.Enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
