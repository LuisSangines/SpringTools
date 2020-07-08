package mx.uam.spring.practicaSpring.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.spring.practicaSpring.datos.AlumnoRepository;
import mx.uam.spring.practicaSpring.negociomodelo.Alumno;

@Service
public class AlumnoService {
	
	@Autowired
	private AlumnoRepository alumnoRepository;

	/**
	 * Método que crea nuevos alumnos 
	 * 
	 * @param nuevoAlumno
	 * @return El alumno registrado , null de lo contrario
	 */
	public Alumno create(Alumno nuevoAlumno) {
		//Regla de negocio : No se puede registrar 2 alumnos con la misma matricula
		
		Optional<Alumno> alumno=alumnoRepository.findById(nuevoAlumno.getMatricula());
		
		if(!alumno.isPresent()) {
			return alumnoRepository.save(nuevoAlumno);
			 
		}else {
			return null;
		}
	}
	
	/**
	 * Método que recupera a tolos los alumos registrados
	 * 
	 * @return List de los alumnos registrados
	 */
	public Iterable<Alumno> retrieveAll(){
		return alumnoRepository.findAll();
	}
	
	/**
	 * Método que recupera un alumno
	 * 
	 * @param matricula
	 * @return Alumno buscado , null si no existe el alumno
	 */
	public Alumno retrieve(Integer matricula) {
		
		Optional<Alumno> alumnoopt=alumnoRepository.findById(matricula);
		return alumnoopt.get();
	}
	
	/**
	 * Método que actualiza un alumno
	 * 
	 * @param actualizaAlumno
	 * @param matricula
	 * @return Alumno actualizado , null si no existe el alumno
	 */
	public Alumno put(Alumno actualizaAlumno,Integer matricula) {
		Optional<Alumno> alumno = alumnoRepository.findById(matricula);
		
		if(alumno.isPresent() == true) {
			return alumnoRepository.save(actualizaAlumno);
		}else {
			return null;		
		}

	}
	
	/**
	 * Método que elimina un alumno
	 * 
	 * @param matricula
	 * @return true si el alumno fue eliminado ,false si no
	 */
	public boolean delete(Integer matricula) {
		Optional<Alumno> alumno=alumnoRepository.findById(matricula);
		if(alumno.isPresent() == true) {
			alumnoRepository.deleteById(matricula);
			return true;
		}else {
			return false;
		}
		
	}
}
