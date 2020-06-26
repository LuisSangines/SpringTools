package mx.uam.spring.practicaSpring.negociomodelo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Alumno {
	@NotNull
	private int matricula;
	@NotBlank
	private String nombre;
	@NotBlank
	private String carrera;

}
