package co.edu.unicauca.asae.taller_entity_jpa.models;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GenerationType;
import java.util.List;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
@Getter
@Setter
@Table(name = "Cuestionarios")
//Un cuestionario puede tener muhcas preguntas

public class Cuestionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cuestionario;
    private String titulo;
    private String descripcion;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.EAGER,mappedBy = "objCuestionario")
    private List<Pregunta> preguntas;
    
    public Cuestionario() {
        
    }

    public Cuestionario( String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;;
    } 
    
    
}
