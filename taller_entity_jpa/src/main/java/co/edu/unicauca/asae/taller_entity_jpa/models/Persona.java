package co.edu.unicauca.asae.taller_entity_jpa.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_persona;
    private String tipo_id;
    private String numero_id;
    private String nombres;
    private String apellidos;

    public Persona(String tipo_id, String numero_id , String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo_id = tipo_id;
        this.numero_id = numero_id;
    }

}
