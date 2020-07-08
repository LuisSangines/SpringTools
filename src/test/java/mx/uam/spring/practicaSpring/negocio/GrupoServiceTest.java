package mx.uam.spring.practicaSpring.negocio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.spring.practicaSpring.datos.GrupoRepository;
import mx.uam.spring.practicaSpring.negociomodelo.Alumno;
import mx.uam.spring.practicaSpring.negociomodelo.Grupo;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GrupoServiceTest {

	@Mock
	private GrupoRepository grupoRepositoryMock;
	
	@Mock
	private AlumnoService alumnoServiceMock;
	
	@InjectMocks
	private GrupoService grupoService;
	
	@Test
	public void testSuccesfulAddStudentToGroup (){
		
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");

		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Stubbing para el alumnoService
		when(alumnoServiceMock.retrieve(12345678)).thenReturn(alumno);
		
		// Stubbing para grupoRepository
		when(grupoRepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
		
		
		boolean result = grupoService.addStudentToGroup(1, 12345678);
		
		assertEquals(true,result);
		
		assertEquals(grupo.getAlumnos().get(0),alumno);
		
	}
	
	@Test
	public void testUnsuccesfulAddStudentToGroup (){
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Stubbing para el alumnoService
		when(alumnoServiceMock.retrieve(12345678)).thenReturn(alumno);
		
		// Stubbing para grupoRepository
		when(grupoRepositoryMock.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		
		
		boolean result = grupoService.addStudentToGroup(1, 12345678);
		
		assertEquals(false,result);
		
		
	}
	
	@Test
	public void testSuccesCreate() {
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		
		// Simula lo que haría el grupoRepository real cuando le pasan un grupo 
		when(grupoRepositoryMock.save(grupo)).thenReturn(grupo);
		
		// Se ejecuta a la unidad que quiero probar
		grupo = grupoService.create(grupo);
		
		//Se comprueba el resultado
		assertNotNull(grupo);
		
	}
	
	
	@Test
	public void testSuccesDelete() {
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		
		// Simula lo que haría el grupoRepository real cuando le pasan un id 
		// que ya ha sido guardado
		when(grupoRepositoryMock.findById(anyInt())).thenReturn(Optional.ofNullable(grupo));
		
		//Se prueba el método
		boolean result = grupoService.delete(1);
		
		//Se comprueba el resultado
		assertEquals(true,result);
	}
	
	@Test
	public void testUnsuccesDelete() {
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		
		// Simula lo que haría el grupoRepository real cuando le pasan un id 
		// que ya ha sido guardado
		when(grupoRepositoryMock.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		
		//Se prueba el método
		boolean result = grupoService.delete(1);
		
		//Se comprueba el resultado
		assertEquals(false,result);
	}
	
	@Test
	public void testSuccesUpdate() {
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		
		// Simula lo que haría el grupoRepository real cuando le pasan un id 
		// que ya ha sido guardado
		when(grupoRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(grupo));
		
		//Sumalamos el parametro a actualizar
		grupo.setClave("TST02");
		
		// Simula lo que haría el grupoRepository real cuando le pasan un id 
		// que ya ha sido guardado
		when(grupoRepositoryMock.save(grupo)).thenReturn(grupo);
		
		//Se prueba el método
		grupo = grupoService.put(grupo, 1);
		
		//Se comprueba el resultado
		assertNotNull(grupo);
		
	}
	
	@Test
	public void testUnsuccesUpdate() {
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		
		// Simula lo que haría el grupoRepository real cuando le pasan un id 
		// que ya ha sido guardado
		when(grupoRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(null));
		
		//Sumalamos el parametro a actualizar
		grupo.setClave("TST02");
		
		//Se prueba el método
		grupo = grupoService.put(grupo, 1);
		
		//Se comprueba el resultado
		assertEquals(null,grupo);
		
	}
	
	@Test
	public void testSuccesRetriveAll() {
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		Grupo grupo1 = new Grupo();
		grupo1.setId(1);
		grupo1.setClave("TST01");
		Grupo grupo2 = new Grupo();
		grupo2.setId(2);
		grupo2.setClave("SIS01");
		
		grupos.add(grupo1);
		grupos.add(grupo2);
		
		// Simula lo que haría el grupoRepository real cuando le pasan un id 
		// que ya ha sido guardado
		when(grupoRepositoryMock.findAll()).thenReturn(grupos);
		
		//Se prueba el método
		grupos = (ArrayList<Grupo>) grupoService.retrieveAll();
		
		//Se comprueba el resultado
		assertNotNull(grupos);
		
	}
	
	@Test
	public void testSuccesRetrive() {

		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("SIS01");
		
		// Simula lo que haría el grupoRepository real cuando le pasan un id 
		// que ya ha sido guardado
		when(grupoRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(grupo));
		
		//Se prueba el método
		grupo = grupoService.retrieve(1);
		
		//Se comprueba el resultado
		assertNotNull(grupo);
	}
	
}
