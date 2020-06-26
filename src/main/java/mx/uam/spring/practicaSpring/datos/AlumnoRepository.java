package mx.uam.spring.practicaSpring.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import mx.uam.spring.practicaSpring.negociomodelo.Alumno;

@Component
public class AlumnoRepository {
	
	//Base de datos
	private Map<Integer, Alumno> alumnoRepository= new HashMap<>();
	
	/**
	 * Se guarda el alumno en la base de datos
	 * 
	 * @param alumno
	 */
	public Alumno save(Alumno nuevoAlumno) {
		 alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);//Simulación de base de datos
		 return nuevoAlumno;
	}
	
	/**
	 * Se busca al alumno mediante su matricula
	 * 
	 * @param matricula
	 * @return alumno buscado por su matricula
	 */
	public Alumno findByMatricula(Integer matricula) {
		return alumnoRepository.get(matricula);
	}
	
	/**
	 * Se busca a todos los alumnos registrados
	 * 
	 * @return Una arraylist de los alumnos
	 */
	public List<Alumno> find(){
		return new ArrayList<> (alumnoRepository.values());
	} 
	
	/**
	 * Se actualiza a un alumno
	 * 
	 * @param actualizaAlumno
	 * @param matricula
	 * @return alumno con los datos actualizados 
	 */
	public Alumno update(Alumno actualizaAlumno,Integer matricula) {
		alumnoRepository.replace(matricula, actualizaAlumno);
		return alumnoRepository.get(matricula);
	}
	
	/**
	 * 
	 * @param matricula
	 * @return alumno eliminado
	 */
	public Alumno delete(Integer matricula) {
		return alumnoRepository.remove(matricula);
	}
}
