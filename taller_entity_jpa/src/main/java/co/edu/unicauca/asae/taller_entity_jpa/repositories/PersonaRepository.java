package co.edu.unicauca.asae.taller_entity_jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.taller_entity_jpa.models.Persona;



public interface PersonaRepository extends CrudRepository<Persona, Integer> {

}
