package mx.uam.spring.practicaSpring.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.spring.practicaSpring.datos.AlumnoRepository;
import mx.uam.spring.practicaSpring.negociomodelo.Alumno;

@ExtendWith(MockitoExtension.class) // Uso de Mockito
public class AlumnoServiceTest {

	@Mock
	private AlumnoRepository alumnoRepositoryMock; //Mock generado mediante mokito
	
	@InjectMocks
	private AlumnoService alumnoService;
	
	@Test
	public void testSuccesCreate() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que no ha sido guardado
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(null));
		
		when(alumnoRepositoryMock.save(alumno)).thenReturn(alumno);
		
		// Aqui ejecuto a la unidad que quiero probar
		alumno = alumnoService.create(alumno);
		
		// Aqui compruebo el resultado
		assertNotNull(alumno); // Probar que la referencia a alumno no es nula
	}
	
	@Test
	public void testUnsuccesfulCreate() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que ya ha sido guardado
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(alumno));
		
		// Aqui ejecuto a la unidad que quiero probar
		alumno = alumnoService.create(alumno);
		
		// Aqui compruebo el resultado
		assertNull(alumno); // Probar que la referencia a alumno es nula por que el alumno ya existía
			
		
	}
	
	@Test
	public void testSuccesDelete() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(789);
		alumno.setNombre("Pruebindelete");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que ya ha sido guardado
		when(alumnoRepositoryMock.findById(789)).thenReturn(Optional.ofNullable(alumno));
		
		//Se prueba el método
		boolean result = alumnoService.delete(789);
		//Se comprueba el resultado
		assertEquals(true,result);
	}
	
	@Test
	public void testUnsuccesDelete() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(789);
		alumno.setNombre("Pruebindelete");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que ya ha sido guardado
		when(alumnoRepositoryMock.findById(789)).thenReturn(Optional.ofNullable(null));
		
		//Se prueba el método
		boolean result = alumnoService.delete(789);
		//Se comprueba el resultado
		assertEquals(false,result);
	}
}
