package co.edu.unicauca.asae.taller_entity_jpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.unicauca.asae.taller_entity_jpa.models.Cuestionario;
import co.edu.unicauca.asae.taller_entity_jpa.models.Departamento;
import co.edu.unicauca.asae.taller_entity_jpa.models.Docente;
import co.edu.unicauca.asae.taller_entity_jpa.models.Pregunta;
import co.edu.unicauca.asae.taller_entity_jpa.models.Respuesta;
import co.edu.unicauca.asae.taller_entity_jpa.models.Telefono;
import co.edu.unicauca.asae.taller_entity_jpa.models.Tipo_pregunta;
import co.edu.unicauca.asae.taller_entity_jpa.repositories.CuestionarioRepository;
import co.edu.unicauca.asae.taller_entity_jpa.repositories.DocenteRepository;
import co.edu.unicauca.asae.taller_entity_jpa.repositories.RespuestaRepository;
import co.edu.unicauca.asae.taller_entity_jpa.repositories.Tipo_preguntaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class TallerEntityJpaApplication implements CommandLineRunner{

	public static void main(String[] args) {
        SpringApplication.run(TallerEntityJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		//registrar_docente();    
		//crear_cuestionario();
		//registrar_respuestas_cuestionario();
		//listar_cuestionarios();
		//listar_respuestas_cuestionarios();
		consultarDocentes();
    }

	//Inyeccion de dependencias.
	@Autowired
	private DocenteRepository servicioBDDocenetes;

	@Autowired
	private CuestionarioRepository servicioBDCuestionario;
	
	@Autowired
	private Tipo_preguntaRepository servicioBDtipo_pregunta;
	
	@Autowired
	private RespuestaRepository servicioBDRespuesta;

	Scanner entrada = new Scanner(System.in);
	
	private void registrar_docente() {	

		List<Departamento> departamentos = new ArrayList<>();
		
		Docente objDocente = new Docente("Cedula", "1234", "Edwin",
										"Espinosa","edwinespj@unicauca.edu.co","planta");
		Telefono objTelefono = new Telefono("Celular", "3128989244");
		Departamento objDepartamento = new Departamento("Cauca", "Bonito");
		objDocente.setObjTelefono(objTelefono);
		objTelefono.setObjDocente(objDocente);
		departamentos.add(objDepartamento);
		objDocente.setDepartamentos(departamentos);
		this.servicioBDDocenetes.save(objDocente);
	}

	private void crear_cuestionario(){
		Tipo_pregunta objTipo_pregunta1 = new Tipo_pregunta("preguntas de seleccion multiple", 
		"se debe elegir una a o varias respuestas correctas.");
		Tipo_pregunta objTipo_pregunta2 = new Tipo_pregunta("preguntas abiertas", 
		"se debe responder la pregunta de manera clara y concisa");
		// Guarda el objeto Tipo_pregunta en la base de datos previamente.
		objTipo_pregunta1 = servicioBDtipo_pregunta.save(objTipo_pregunta1);
		objTipo_pregunta2 = servicioBDtipo_pregunta.save(objTipo_pregunta2);
		
		//cuestionario de preguntas de seleccion multiple cuestionario 1 
		Cuestionario objCuestionario1 = new Cuestionario("Evaluacion del desempeño docente", 
		"Ayuda a mantener altos estandares de calidad educativa compartiendo tu opinion.");
		List<Pregunta> lista_preguntas1 = new ArrayList<>();
		Pregunta objPregunta1 = new Pregunta("¿De 1 al 5 que tan preparado esta el Docente x?");
		Pregunta objPregunta2 = new Pregunta("¿De 1 a 5 que tan facil explica el Docente x?");
		objPregunta1.setObjCuestionario(objCuestionario1);
		objPregunta1.setObjTipoPregunta(objTipo_pregunta1);
		objPregunta2.setObjCuestionario(objCuestionario1);
		objPregunta2.setObjTipoPregunta(objTipo_pregunta1);
		lista_preguntas1.add(objPregunta1);
		lista_preguntas1.add(objPregunta2);
		objCuestionario1.setPreguntas(lista_preguntas1);
		this.servicioBDCuestionario.save(objCuestionario1);

		//cuestionario de preguntas de abiertas cuestionario 2 
		Cuestionario objCuestionario2 = new Cuestionario("Revision Docente", 
		"Evalua el desempeno de tus colegas en el aula");
		List<Pregunta> lista_preguntas2 = new ArrayList<>();
		Pregunta objPregunta3 = new Pregunta("¿Como desempena el rol de ensenanza el Docente x?");
		Pregunta objPregunta4 = new Pregunta("¿Cree usted que tiene dominio del publico el Docente x?");
		objPregunta3.setObjCuestionario(objCuestionario2);
		objPregunta3.setObjTipoPregunta(objTipo_pregunta2);
		objPregunta4.setObjCuestionario(objCuestionario2);
		objPregunta4.setObjTipoPregunta(objTipo_pregunta2);
		lista_preguntas2.add(objPregunta3);
		lista_preguntas2.add(objPregunta4);
		objCuestionario2.setPreguntas(lista_preguntas2);	
		
		this.servicioBDCuestionario.save(objCuestionario2);
	}
	
	public void registrar_respuestas_cuestionario() {	
		int num_cues=1,num_pre=1;
		
		Docente objDocente = this.servicioBDDocenetes.findById(1).orElse(null);
		//objDocente.setId_persona(1); //se establese el docente que va a responder el cuestionario.
		
		Iterable<Cuestionario> lista_cuestionarios = this.servicioBDCuestionario.findAll();
	
		System.out.println("Nombre del docente: "+ objDocente.getNombres());
		
		for (Cuestionario objCuestionario : lista_cuestionarios) {
			System.out.println("Cuestionario "+  num_cues );
			Iterable<Pregunta> preguntas = objCuestionario.getPreguntas();
			for (Pregunta objPregunta : preguntas){
				Respuesta objRespuesta = new Respuesta(); 
				System.out.println("Pregunta " + num_pre + " : ");
				System.out.println("Tipo "+objPregunta.getObjTipoPregunta().getNombre());
				System.out.println("Enunciado "+objPregunta.getEnunciado());
				System.out.println("Ingrese su respuesta: ");
				String res = entrada.next();
				objRespuesta.setDescripcion(res);
				objDocente.setId_persona(1);
				objRespuesta.setObjDocente(objDocente);
				objRespuesta.setObjPregunta(objPregunta);
				num_pre++;
				this.servicioBDRespuesta.save(objRespuesta);
			}
			num_pre=1;
			num_cues++;
		}
	}
	
	private void listar_cuestionarios(){
		Iterable<Cuestionario> lista_cuestionarios = this.servicioBDCuestionario.findAll();
		int numeroPregunta = 1, numero_cuestionario=1;
		for (Cuestionario objCuestionario : lista_cuestionarios) {
			System.out.println("**********************************************");
			System.out.println("CUESTIONARIO NUMERO "+ numero_cuestionario +" : ");
			System.out.println("Titulo del cuestionario: " + objCuestionario.getTitulo());
			Iterable<Pregunta> preguntas = objCuestionario.getPreguntas();
			for (Pregunta objPregunta : preguntas){
				System.out.println("----------------------------------------------");
				System.out.println("Pregunta "+ numeroPregunta);
				System.out.println("Tipo "+objPregunta.getObjTipoPregunta().getNombre());
				System.out.println("Enunciado "+objPregunta.getEnunciado());
				System.out.println("----------------------------------------------");
				numeroPregunta++;
			}
			numeroPregunta=1;
			System.out.println("**********************************************");
			numero_cuestionario++;
		}
	}

	private void listar_respuestas_cuestionarios(){
		Iterable<Cuestionario> lista_cuestionarios = this.servicioBDCuestionario.findAll();
		int numero_pregunta = 1, numero_cuestionario=1;
		
		for (Cuestionario objCuestionario : lista_cuestionarios) {
			System.out.println("**********************************************");
			System.out.println("CUESTIONARIO NUMERO "+ numero_cuestionario +" : ");
			System.out.println("Titulo del cuestionario: " + objCuestionario.getTitulo());
			Iterable<Pregunta> preguntas = objCuestionario.getPreguntas();
			for (Pregunta objPregunta : preguntas){
				System.out.println("----------------------------------------------");
				System.out.println("Pregunta "+ numero_pregunta);
				System.out.println("Tipo "+objPregunta.getObjTipoPregunta().getNombre());
				System.out.println("Enunciado "+objPregunta.getEnunciado());
				Iterable<Respuesta> respuestas = objPregunta.getRespuestas();
				for (Respuesta objRespuesta : respuestas){
					System.out.println("Nombre del docente: " + objRespuesta.getObjDocente().getNombres());
					System.out.println("respuesta: "+ objRespuesta.getDescripcion());
				}
				System.out.println("----------------------------------------------");
				numero_pregunta++;
			}
			numero_pregunta=1;
			System.out.println("**********************************************");
			numero_cuestionario ++;
		}
		
	}

	private void consultarDocentes() {
		Iterable<Docente> listaDocentes = this.servicioBDDocenetes.findAll();
		for (Docente objDocente : listaDocentes) {
			System.out.println("Id: " + objDocente.getId_persona());
			System.out.println("Nombres: " + objDocente.getNombres());
			System.out.println("Apellidos: " + objDocente.getApellidos());
			System.out.println("Correo: " + objDocente.getCorreo());
			System.out.println("Vinculacion: " + objDocente.getVinvulacion());
			System.out.println("Numero: " + objDocente.getObjTelefono().getNumero());
			Iterable<Departamento> departamentos = objDocente.getDepartamentos();
			for (Departamento objDepartamento : departamentos){
				System.out.println("Nombre departamento: "+objDepartamento.getNombre_dep());
			}
			System.out.println(" ---- ---- -----");
		}
	}
	

}
