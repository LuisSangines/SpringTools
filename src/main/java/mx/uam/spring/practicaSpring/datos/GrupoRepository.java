package mx.uam.spring.practicaSpring.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.spring.practicaSpring.negociomodelo.Grupo;

/**
 * Se encarga del almacenamiento y la recuperación de los grupos
 * 
 * @author wuich
 *
 */
public interface GrupoRepository extends CrudRepository <Grupo, Integer>{

}
