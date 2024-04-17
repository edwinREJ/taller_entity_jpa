package co.edu.unicauca.asae.taller_entity_jpa.models;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Getter
@Setter
@Table (name = "Telefono")

public class Telefono {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private int id_telefono;
    private String tipo_telefono;
    private String numero;

    

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Docente objDocente;

    public Telefono() {
    }

    public Telefono(String tipo_telefono, String numero){
        this.tipo_telefono = tipo_telefono;
        this.numero = numero;
    }
    
}
