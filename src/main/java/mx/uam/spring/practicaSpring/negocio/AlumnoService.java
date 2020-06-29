package mx.uam.spring.practicaSpring.negocio;

import java.util.List;

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
		
		Alumno alumno=alumnoRepository.findByMatricula(nuevoAlumno.getMatricula());
		
		if( alumno == null) {
			return alumnoRepository.save(nuevoAlumno);
			 
		}else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return List de los alumnos registrados
	 */
	public List<Alumno> retrieveAll(){
		return alumnoRepository.find();
	}
	
	/**
	 * 
	 * @param matricula
	 * @return Alumno buscado , null si no existe el alumno
	 */
	public Alumno retrieve(Integer matricula) {
		
		Alumno alumno=alumnoRepository.findByMatricula(matricula);
		if(alumno != null) {
			return alumno;
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
		Alumno alumno = alumnoRepository.update(actualizaAlumno, matricula);
		if(alumno != null) {
			return alumno;
		}else {
			return null;		
		}

	}
	
	/**
	 * 
	 * @param matricula
	 * @return alumno eliminado, null si no existe el alumno
	 */
	public Alumno delete(Integer matricula) {
		Alumno alumno=alumnoRepository.findByMatricula(matricula);
		if(alumno != null) {
			return alumnoRepository.delete(matricula);
		}else {
			return null;
		}
		
	}
}
