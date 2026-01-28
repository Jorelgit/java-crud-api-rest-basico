package com.jorel.apirest.repasopoobasico9practicaapirest.Repositories;

import com.jorel.apirest.repasopoobasico9practicaapirest.Entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

// El producto Repository extiende de JpasRepository y se le pasa el nombre de la entidad y el tipo de dato dle Id
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
