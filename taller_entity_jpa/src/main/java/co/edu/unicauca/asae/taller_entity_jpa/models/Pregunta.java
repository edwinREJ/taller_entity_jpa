package co.edu.unicauca.asae.taller_entity_jpa.models;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Getter
@Setter
@Table (name = "Preguntas")

public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pregunta;
    private String enunciado;
    

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.EAGER,mappedBy = "objPregunta")
    private List<Respuesta> respuestas;

    @ManyToOne
    @JoinColumn(name="id_cuestionario", nullable = false)
    private Cuestionario objCuestionario;

    @ManyToOne
    @JoinColumn(name="id_tipo_pregunta", nullable = false)
    private Tipo_pregunta objTipoPregunta;
   
    public Pregunta() {
    }

    public Pregunta(String enunciado) {
        this.enunciado = enunciado;
        this.respuestas = new ArrayList<Respuesta>();
    }
     
}
