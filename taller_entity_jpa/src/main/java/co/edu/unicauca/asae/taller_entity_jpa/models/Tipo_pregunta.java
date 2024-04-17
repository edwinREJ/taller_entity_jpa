package co.edu.unicauca.asae.taller_entity_jpa.models;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Getter
@Setter
@Table (name = "TipoPregunta")

public class Tipo_pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tipo_pregunta;
    private String nombre;
    private String descripcion;
    
    @OneToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER,mappedBy = "objTipoPregunta")
    private List<Pregunta> preguntas;
    public Tipo_pregunta() {
    }
    public Tipo_pregunta(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.preguntas = new ArrayList<Pregunta>();
    }
   
}
