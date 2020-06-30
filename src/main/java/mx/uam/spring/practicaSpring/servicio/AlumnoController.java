package mx.uam.spring.practicaSpring.servicio;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.spring.practicaSpring.negocio.AlumnoService;
import mx.uam.spring.practicaSpring.negociomodelo.Alumno;

/**
 * Controlador para el API rest
 *
 * @author wuich
 *
 */
@RestController
@Slf4j
public class AlumnoController {
	
		@Autowired
		private AlumnoService alumnoService;

		@ApiOperation(
				value="Crear un alumno",
				notes="Para crear al alumno la matriucla debe de ser única"
				)
		@PostMapping( path= "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> create(@RequestBody @Valid Alumno nuevoAlumno) {
			log.info("Recibi llamado a create con "+nuevoAlumno);

			Alumno alumno=alumnoService.create(nuevoAlumno);
			
			if(alumno != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(alumno); 
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			
			
		}
	
		@ApiOperation(
				value="Recupera alumnos",
				notes="Recupera a todos alumnos creados"
				)		
		@GetMapping( path="/alumnos", produces =  MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> retrieveAll() {
			log.info("Recibi un llamado para recuperar a los alumnos");
			Iterable <Alumno> result= alumnoService.retrieveAll();
			
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}

		@ApiOperation(
				value="Recupera un alumno",
				notes="Recupera a un alumno por medio de su matricula"
				)
		@GetMapping( path="/alumnos/{matricula}", produces =  MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> retrieve(@PathVariable("matricula") @Valid Integer matricula) {
			log.info("Buscando al alumno con matricula "+matricula);
			
			Optional<Alumno> alumno=alumnoService.retrieve(matricula);
			
			if(alumno != null) {
				return ResponseEntity.status(HttpStatus.OK).body(alumno);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}

		@ApiOperation(
				value="Actualiza alumno",
				notes="Se puede actualizar el nombre y carrera de un alumno"
				)
		@PutMapping(path= "/alumnos/{matricula}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> update(@PathVariable("matricula") Integer matricula ,@RequestBody @Valid Alumno actualizaAlumno) {
			log.info("Llamado a actualizar el alumno "+matricula);
			Alumno alumno=alumnoService.put(actualizaAlumno, matricula);
			
			if(alumno !=null) {
				return ResponseEntity.status(HttpStatus.OK).body(alumno);
			}
			else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
		}
		
		@ApiOperation(
				value="Elimina un alumno",
				notes="Por medio de la matriucla se elimina a un alumno"
				)
		@DeleteMapping(path="/alumnos/{matricula}")
		public ResponseEntity<?> delete(@PathVariable("matricula") @Valid Integer matricula) {
			log.info("Eliminando al alumno con matricula "+matricula);
			boolean result= alumnoService.delete(matricula);
			
			if(result == true) {
				return ResponseEntity.status(HttpStatus.OK).body("Alumno eliminado correctamente");
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El alumno no esta registrado");
			}
		
		}
		
}		

