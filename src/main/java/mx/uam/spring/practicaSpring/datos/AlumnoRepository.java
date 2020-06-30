package mx.uam.spring.practicaSpring.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.spring.practicaSpring.negociomodelo.Alumno;

/**
 * Se encarga del almacenamiento y la recuperación de los alumnos
 * 
 * @author wuich
 *
 */
public interface AlumnoRepository extends CrudRepository <Alumno, Integer>{ 
}



