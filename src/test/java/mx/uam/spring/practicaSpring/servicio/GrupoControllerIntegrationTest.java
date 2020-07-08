package mx.uam.spring.practicaSpring.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import mx.uam.spring.practicaSpring.datos.AlumnoRepository;
import mx.uam.spring.practicaSpring.datos.GrupoRepository;
import mx.uam.spring.practicaSpring.negociomodelo.Alumno;
import mx.uam.spring.practicaSpring.negociomodelo.Grupo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class GrupoControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@BeforeEach
	public void prepare() {
		log.info("Preramos el alumno y grupo que vamos a ocupar ");
		// Creo un alumno un alumno
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(1234);
		alumno.setNombre("PerryElOrnitorringo");
		alumnoRepository.save(alumno);		
		//Creo un grupo
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		grupoRepository.save(grupo);
	}
	
	@Test
	public void testAddStudentToGroup200() {
		//Matricula del alumno
		Integer matricula = 1234;
		//Id del grupo
		Integer id = 1;

		// Creo el encabezado
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type",MediaType.APPLICATION_JSON.toString());
		
		// Creo la petición con la matricula del alumno como body y el encabezado
		HttpEntity <Integer> request = new HttpEntity <> (matricula, headers);
		
		ResponseEntity <Integer> responseEntity = restTemplate.exchange("/grupos/"+id+"/alumnos?matricula="+matricula, HttpMethod.POST, request, Integer.class);
		
		// Corroboro que el endpoint me regresa el estatus esperado
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		// Corroboro que en la base de datos se actualizo el grupo con el nuevo alumno
		Optional <Grupo> optGrupo = grupoRepository.findById(id);
		Optional <Alumno> optAlumno = alumnoRepository.findById(matricula);
		
		boolean result=optGrupo.get().getAlumnos().contains(optAlumno.get());
		
		assertEquals(true,result);
		
	}
	
	@Test
	public void testAddStudentToGroup400() {
		
		//Matricula del alumno
		Integer matricula = 1234;
		//Id del grupo
		Integer id = 2;

		// Creo el encabezado
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type",MediaType.APPLICATION_JSON.toString());
		
		// Creo la petición con la matricula del alumno como body y el encabezado
		HttpEntity <Integer> request = new HttpEntity <> (matricula, headers);
		
		ResponseEntity <Integer> responseEntity = restTemplate.exchange("/grupos/"+id+"/alumnos?matricula="+matricula, HttpMethod.POST, request, Integer.class);
		
		// Corroboro que el endpoint me regresa el estatus esperado
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

	}
}
