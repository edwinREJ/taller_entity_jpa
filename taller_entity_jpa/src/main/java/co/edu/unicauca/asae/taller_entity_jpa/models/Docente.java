package co.edu.unicauca.asae.taller_entity_jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;

@Entity
@Getter
@Setter
@Table (name = "Docentes")

public class Docente extends Persona {

    
    private String correo;
    private String vinvulacion;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "objDocente")
    private Telefono objTelefono;
  
    @OneToMany(mappedBy = "objDocente")
    private List<Respuesta> respuestas;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.EAGER)
    @JoinTable(name="Departameto_Docente",
               joinColumns = @JoinColumn(name="id_persona"),
               inverseJoinColumns = @JoinColumn(name="id_departamento"))
    private List<Departamento> departamentos;


    public Docente() {
        super();
    }

    public Docente(String tipo_id, String numero_id, String nombres,String apellidos,String correo, 
        String vinvulacion) {
        super(tipo_id,numero_id,nombres,apellidos);
        this.correo = correo;
        this.vinvulacion = vinvulacion;
    }

}
