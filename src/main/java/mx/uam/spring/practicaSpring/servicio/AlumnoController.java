package mx.uam.spring.practicaSpring.servicio;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.uam.spring.practicaSpring.negocio.modelo.Alumno;

/**
 * Controlador para el API rest
 *
 * @author wuich
 *
 */
@RestController
@Slf4j
public class AlumnoController {
	
		private Map<Integer, Alumno> alumnoRepository= new HashMap<>();

		@PostMapping( path= "/alumnos/create", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> create(@RequestBody Alumno nuevoAlumno) {
			log.info("Recibi llamado a create con "+nuevoAlumno);
			alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);//Simulación de base de datos
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		@GetMapping( path="/alumnos", produces =  MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> retrieveAll() {
			log.info("Recibi un llamado para recuperar a los alumnos");
			return ResponseEntity.status(HttpStatus.OK).body(alumnoRepository.values());
		}
		
		@GetMapping( path="/alumnos/{matricula}", produces =  MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> retrieve(@PathVariable("matricula") Integer matricula) {
			log.info("Buscando al alumno con matricula "+matricula);
			Alumno a= alumnoRepository.get(matricula);
			if(a !=null) {
				return ResponseEntity.status(HttpStatus.OK).body(a);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
		}
		@PutMapping(path= "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> update(@RequestBody Alumno actualizaAlumno) {
			log.info("Llamado a actualizar el alumno "+actualizaAlumno.getMatricula());
			Alumno a= alumnoRepository.get(actualizaAlumno.getMatricula());
			if(a !=null) {
			alumnoRepository.replace(actualizaAlumno.getMatricula(), actualizaAlumno);
			return ResponseEntity.status(HttpStatus.OK).body(a);
			}
			else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
		}
		
		@DeleteMapping(path="/alumnos/{matricula}")
		public ResponseEntity<?> delete(@PathVariable("matricula") Integer matricula) {
			log.info("Eliminando al alumno con matricula "+matricula);
			Alumno a= alumnoRepository.get(matricula);
			if(a !=null) {
				alumnoRepository.remove(matricula);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		
}
