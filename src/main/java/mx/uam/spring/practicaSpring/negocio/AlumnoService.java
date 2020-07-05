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
	 * 
	 * @return List de los alumnos registrados
	 */
	public Iterable<Alumno> retrieveAll(){
		return alumnoRepository.findAll();
	}
	
	/**
	 * 
	 * @param matricula
	 * @return Alumno buscado , null si no existe el alumno
	 */
	public Optional<Alumno> retrieve(Integer matricula) {
		
		Optional<Alumno> alumnoopt=alumnoRepository.findById(matricula);
		if(alumnoopt.isPresent() == true) {
			return alumnoopt;
		}else {
			return null;
		}
	}
	
	/**
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
