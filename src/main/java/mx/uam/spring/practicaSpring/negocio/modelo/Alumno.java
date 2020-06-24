package mx.uam.spring.practicaSpring.negocio.modelo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Alumno {
	
	private int matricula;
	
	private String nombre;
	
	private String carrera;

}
