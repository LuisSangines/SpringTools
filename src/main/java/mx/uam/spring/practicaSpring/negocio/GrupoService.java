package mx.uam.spring.practicaSpring.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mx.uam.spring.practicaSpring.datos.GrupoRepository;
import mx.uam.spring.practicaSpring.negociomodelo.Alumno;
import mx.uam.spring.practicaSpring.negociomodelo.Grupo;

@Service
@Log4j2
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private AlumnoService alumnoService;
	/**
	 * Método que crea un grupo
	 * 
	 * @param nuevogrupo
	 * @return
	 */
	public Grupo create(Grupo nuevogrupo) {
		return grupoRepository.save(nuevogrupo);
	}
	
	/**
	 * Método que recupera a todos los grupos registrados
	 * 
	 * @return Lista de los grupos registrados
	 */
	public Iterable<Grupo> retrieveAll(){
		return grupoRepository.findAll();
	}
	
	/**
	 * Método que recupera un grupo registrado
	 * 
	 * @param id
	 * @return grupo
	 */
	public Grupo retrieve(Integer id){
		Optional<Grupo> grupoopt= grupoRepository.findById(id);
		return grupoopt.get();
	}
	
	/**
	 * 
	 * Método que permite agregar un alumno a un grupo
	 * 
	 * @param groupId el id del grupo
	 * @param matricula la matricula del alumno
	 * @return true si se agregó correctamente, false si no
	 */
	public boolean addStudentToGroup(Integer groupId, Integer matricula) {
		
		log.info("Agregando alumno con matricula "+matricula+" al grupo "+groupId);
		
		// 1.- Recuperamos primero al alumno
		Alumno alumno = alumnoService.retrieve(matricula);
		
		// 2.- Recuperamos el grupo
		Optional <Grupo> grupoOpt = grupoRepository.findById(groupId);
		
		// 3.- Verificamos que el alumno y el grupo existan
		if(!grupoOpt.isPresent() || alumno == null) {
			
			log.info("No se encontró alumno o grupo");
			
			return false;
		}
		
		// 4.- Agrego el alumno al grupo
		Grupo grupo = grupoOpt.get();
		grupo.addAlumno(alumno);
		
		// 5.- Persistir el cambio
		grupoRepository.save(grupo);
		log.info("Se guardo el alumno al grupo");
		
		return true;
	}
	
	public boolean deleteStudentToGroup(Integer groupId, Integer matricula) {
		
		log.info("Elimando alumno con matricula "+matricula+" del grupo "+groupId);
		
		// 1.- Recuperamos primero al alumno
		Alumno alumno = alumnoService.retrieve(matricula);
		
		// 2.- Recuperamos el grupo
		Optional <Grupo> grupoOpt = grupoRepository.findById(groupId);
		
		// 3.- Verificamos que el alumno y el grupo existan
		if(!grupoOpt.isPresent() || alumno == null) {		
			log.info("No se encontró alumno o grupo");
			return false;
		}
		// 4.- Eliminamos al alumno del grupo
		Grupo grupo = grupoOpt.get();
		grupo.deleteAlumno(alumno);
		
		// 5.- Persistir el cambio
		grupoRepository.save(grupo);
		
		return true;
	}
	/**
	 * Método que actualiza un grupo
	 * 
	 * @param actualizaGrupo
	 * @param id
	 * @return Grupo actualizado , null si no existe el grupo
	 */
	public Grupo put(Grupo actualizaGrupo,Integer id) {
		Optional<Grupo> grupo = grupoRepository.findById(id);
		
		if(grupo.isPresent() == true) {
			return grupoRepository.save(actualizaGrupo);
		}else {
			return null;		
		}
	}
	
	/**
	 * Método que elimina un grupo
	 * 
	 * @param id
	 * @return true si el grupo fue eliminado ,false si no existe el grupo
	 */
	public boolean delete(Integer id) {
		Optional<Grupo> grupo=grupoRepository.findById(id);
		if(grupo.isPresent() == true) {
			grupoRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
		
	}
}
