package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.entity.VistaRecetaIngredientes;
import com.daw.celiblog.enums.TipoComidaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    @Query(value = "SELECT * FROM receta", nativeQuery = true)
    List<Receta> findAll();

    @Query(value = "SELECT count(*) FROM receta", nativeQuery = true)
    int countAll();

    @Query(value = """
    SELECT DISTINCT r.*
    FROM receta r
    JOIN tag_receta t ON r.id_receta = t.id_receta
    WHERE UPPER(t.nombre) LIKE %:nombreTag%
    AND r.estado = 'APROBADO'
    """, nativeQuery = true)
    List<Receta> buscarRecetasPorNombreDeTag(@Param("nombreTag") String nombreTag);

    @Query(value = "SELECT * FROM receta WHERE estado =:estadoPublicacion", nativeQuery = true)
    List<Receta> getByEstadoPublicacion(@Param("estadoPublicacion") String estadoPublicacion);

    @Query(value = "SELECT * FROM vista_receta_ingredientes WHERE id_receta =:idReceta", nativeQuery = true)
    List<VistaRecetaIngredientes> getIngredientesByIdReceta(@Param("idReceta") Long idReceta);

    List<Receta> getByComensales(int numComensales);
    List<Receta> getByValoracion(int valoracion);
    List<Receta> getByTipoComida(TipoComidaEnum tipoComida);

    @Query(value = "SELECT * FROM receta WHERE id_usuario =:idUsuario", nativeQuery = true)
    List<Receta> getByIdUsuario(@Param("idUsuario")Long idUsuario);

}
