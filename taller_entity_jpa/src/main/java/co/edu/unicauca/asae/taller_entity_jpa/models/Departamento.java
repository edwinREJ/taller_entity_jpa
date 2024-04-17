package co.edu.unicauca.asae.taller_entity_jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Departamentos")

public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id_departamento;
    private String nombre_dep;
    private String descripcion;

    
    public Departamento (){
    }

    public Departamento (String nombre_dep, String descripcion){
        this.nombre_dep = nombre_dep;
        this.descripcion = descripcion;
    }

}
