package mx.uam.spring.practicaSpring.servicio;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.spring.practicaSpring.negocio.GrupoService;
import mx.uam.spring.practicaSpring.negociomodelo.Grupo;

@RestController
@Slf4j
public class GrupoController {

	@Autowired
	private GrupoService grupoService;
	
	/**
	 * POST /grupos
	 * 
	 * @param nuevogrupo
	 * @return
	 */
	@ApiOperation(
			value="Crear un grupo",
			notes="Permite crear un nuevo grupo"
			)
	@PostMapping(path= "/grupos", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody @Valid Grupo nuevogrupo ){
		log.info("Recibi llamado a create un nuevo grupo"+nuevogrupo);
		
		Grupo grupo=grupoService.create(nuevogrupo);
		
		if(grupo != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/**
	 * PUT /grupos/{id}
	 * 
	 * @param id
	 * @param actualizaGrupo
	 * @return
	 */
	@ApiOperation(
			value="Actualiza grupo",
			notes="Se puede actualizar el nombre del grupo"
			)
	@PutMapping(path= "/grupos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("id") Integer id ,@RequestBody @Valid Grupo actualizaGrupo) {
		log.info("Llamado a actualizar un grupo "+id);
		Grupo grupo=grupoService.put(actualizaGrupo, id);
		
		if(grupo !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(grupo);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
	}
	
	/**
	 * DELETE /grupos/{id}
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(
			value="Elimina un grupo",
			notes="Por medio del id se elimina al grupo"
			)
	@DeleteMapping(path="/grupos/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") @Valid Integer id) {
		log.info("Eliminando al alumno con matricula "+id);
		boolean result= grupoService.delete(id);
		
		if(result == true) {
			return ResponseEntity.status(HttpStatus.OK).body("Grupo eliminado correctamente");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El grupo no esta registrado");
		}
	
	}
	
	/**
	 * GET /grupos/{matricula}
	 * 
	 * @param matricula
	 * @return
	 */
	@ApiOperation(
			value="Recupera un grupo",
			notes="Recupera un grupo por medio de su id"
			)
	@GetMapping( path="/grupos/{matricula}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retrieve(@PathVariable("matricula") @Valid Integer id) {
		log.info("Buscando al alumno con matricula "+id);
		
		Grupo grupo=grupoService.retrieve(id);
		
		if(grupo != null) {
			return ResponseEntity.status(HttpStatus.OK).body(grupo);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	/**
	 * GET /grupos
	 * 
	 * @return
	 */
	@ApiOperation(
			value="Recupera Grupos",
			notes="Recupera a todos los grupos creados"
			)		
	@GetMapping( path="/grupos", produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retrieveAll() {
		log.info("Recibi un llamado para recuperar los grupos");
		Iterable <Grupo> result= grupoService.retrieveAll();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * POST /grupos/{id}/alumnos?matricula=1234
	 * 
	 * @param id
	 * @param matricula
	 * @return
	 */
	@ApiOperation(
			value="Da de alta un alumno a un grupo",
			notes="Se registra al alumno en el grupo deseado, por medio de sus matriculas"
			)
	@PostMapping(path = "/grupos/{id}/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> addStudentToGroup(
			@PathVariable("id") Integer id,
			@RequestParam("matricula") Integer matricula) {
		log.info("Reicbi llamado a addStudentToGroup alumno: " +matricula+" al grupo: "+id);
		boolean result = grupoService.addStudentToGroup(id, matricula);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build(); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		
	
	}
	
	/**
	 * DELETE /grupos/{id}/alumnos
	 * 
	 * @param id
	 * @param matricula
	 * @return
	 */
	@ApiOperation(
			value="Elimina un alumno del grupo",
			notes="Se elimina al alumno del grupo deseado"
			)
	@DeleteMapping(path = "/grupos/{id}/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> deletetudentToGroup(
			@PathVariable("id") Integer id,
			@RequestParam("matricula") Integer matricula) {
		
		boolean result = grupoService.deleteStudentToGroup(id, matricula);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build(); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		
	
	}
	
}
