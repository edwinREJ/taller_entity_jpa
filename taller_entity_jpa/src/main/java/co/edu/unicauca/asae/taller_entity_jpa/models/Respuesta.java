package co.edu.unicauca.asae.taller_entity_jpa.models;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Getter
@Setter
@Table(name = "Respuesta")

public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_respuesta;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="id_persona", nullable = false)
    private Docente objDocente;

    @ManyToOne
    @JoinColumn(name="id_pregunta", nullable = false)
    private Pregunta objPregunta;

    public Respuesta() {
        
    }
    public Respuesta(String descripcion) {
        this.descripcion = descripcion;
    }

}
