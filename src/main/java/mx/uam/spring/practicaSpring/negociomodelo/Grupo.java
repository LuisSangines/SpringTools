package mx.uam.spring.practicaSpring.negociomodelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity //Sirve para indicar que debe persister en BD
public class Grupo {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank
	private String clave;
	
	@Builder.Default
	@OneToMany(targetEntity = Alumno.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id") // No se crea tabla intermedia	
	private List<Alumno> alumnos=new ArrayList<Alumno>();
	
	public boolean addAlumno(Alumno alumno) {
		return alumnos.add(alumno);
	}
	
	public boolean deleteAlumno(Alumno alumno) {
		return alumnos.remove(alumno);
	}


}
