package sv.com.institucion.controladores;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import lombok.Getter;
import lombok.Setter;
import sv.com.institucion.dao.DepartamentoDao;
import sv.com.institucion.dao.DireccionDao;
import sv.com.institucion.dao.EnfermedadDao;
import sv.com.institucion.dao.EspecialidadDao;
import sv.com.institucion.dao.EstadoCivilDao;
import sv.com.institucion.dao.EstadoInscripcionDao;
import sv.com.institucion.dao.EstudianteDao;
import sv.com.institucion.dao.ExpedienteDao;
import sv.com.institucion.dao.ExpedienteMedicoDao;
import sv.com.institucion.dao.InscripcionDao;
import sv.com.institucion.dao.InstitucionProcedenciaDao;
import sv.com.institucion.dao.MedioTransporteDao;
import sv.com.institucion.dao.MunicipioDao;
import sv.com.institucion.dao.NivelDao;
import sv.com.institucion.dao.ParentescoDao;
import sv.com.institucion.dao.PersonaDao;
import sv.com.institucion.dao.ResponsableDao;
import sv.com.institucion.dao.ResponsableEstudianteDao;
import sv.com.institucion.entidades.Departamento;
import sv.com.institucion.entidades.Direccion;
import sv.com.institucion.entidades.Enfermedad;
import sv.com.institucion.entidades.Especialidad;
import sv.com.institucion.entidades.EstadoCivil;
import sv.com.institucion.entidades.Estadoinscripcion;
import sv.com.institucion.entidades.Estudiante;
import sv.com.institucion.entidades.Expediente;
import sv.com.institucion.entidades.ExpedienteMedico;
import sv.com.institucion.entidades.Inscripcion;
import sv.com.institucion.entidades.Institucionprocedencia;
import sv.com.institucion.entidades.MedioTransporte;
import sv.com.institucion.entidades.Modalidad;
import sv.com.institucion.entidades.Municipio;
import sv.com.institucion.entidades.Nivel;
import sv.com.institucion.entidades.NivelEspecialidad;
import sv.com.institucion.entidades.Parentesco;
import sv.com.institucion.entidades.Persona;
import sv.com.institucion.entidades.Responsable;
import sv.com.institucion.entidades.ResponsableEstudiante;
import sv.com.institucion.entidades.Usuario;
import sv.com.institucion.entidades.UsuarioRol;
import sv.com.institucion.utils.CustomUtils;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class InscripcionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger logger = Logger.getLogger(InscripcionController.class);
	
	//ENTIDADES
	private Persona personaResponsable;
	private Persona personaEstudiante;
	private Parentesco parentesco;
	private Responsable responsable;
	private EstadoCivil estadoCivilResponsable;
	private EstadoCivil estadoCivilEstudiante;
	private MedioTransporte medioTransporteResponsable;
	private MedioTransporte medioTransporteEstudiante;
	private Direccion direccion;
	private Estudiante estudiante;
	private ResponsableEstudiante responsableEstudiante;
	private Institucionprocedencia institucionProcedencia;
	private Nivel nivel;
	private Especialidad especialidad;
	private NivelEspecialidad nivelEspecialidad;
	private Enfermedad enfermedad;
	private ExpedienteMedico expendienteMedico;
	private Inscripcion inscripcion;
	private Modalidad modalidad;
	private Estadoinscripcion estadoInscripcion;
	private Expediente expediente;
	private Usuario usuarioEstudiante;
	private UsuarioRol usuarioRolEstudiante;
	private String departamento;
	private Municipio municipio;
	private FacesMessage message;
	private Date ahora;
	
	//LISTAS
	private List<Enfermedad> enfermedades;
	private List<Estadoinscripcion> estadoInscripciones;
	private List<Modalidad> modalidades;
	private List<Nivel> niveles;
	private List<Especialidad> especialidades;
	private List<Institucionprocedencia> institucionesProcedencia;
	private List<Parentesco> parentescos;
	private List<MedioTransporte> mediosTransporte;
	private List<EstadoCivil> estadosCivil;
	private List<Departamento> departamentos;
	private List<Municipio> municipios;
	private List<Inscripcion> inscripciones;
	
	//MAPAS
	private Map<String,List<Municipio>> data;
	
	//DAO
	private DepartamentoDao departamentoDao;
	private MedioTransporteDao medioTransporteDao;
	private ParentescoDao parentescoDao;
	private EspecialidadDao especialidadDao;
	private EnfermedadDao enfermedadDao;
	private NivelDao nivelDao;
	private InstitucionProcedenciaDao institucionProcedenciaDao;
	private PersonaDao personaDao;
	private ResponsableDao responsableDao;
	private DireccionDao direccionDao;
	private ExpedienteMedicoDao expedienteMedicoDao;
	private EstudianteDao estudianteDao;
	private ResponsableEstudianteDao responsableEstudianteDao;
	private InscripcionDao inscripcionDao;
	private EstadoInscripcionDao estadoInscripcionDao;
	private ExpedienteDao expedienteDao;
	private MunicipioDao municipioDao;
	private EstadoCivilDao estadoCivilDao;
	private Usuario usuario;
	
	
	@PostConstruct
	public void init(){
		inicializarDao();
		inscripciones = new ArrayList<Inscripcion>();
		inscripciones = inscripcionDao.obtenerTodos();
	}
	
	
 public void preparedSave(){
	 try {
		incializarValores();
		cargarListas();
		RequestContext.getCurrentInstance().execute("PF('dlgInscripcion').show(); PF('wvins').loadStep('personal', true);");
	} catch (Exception e) {
		logger.info("Ha ocurrido un error al preGuardar la informacion de InscripcionController: "+e.toString());
	}
 }	
	
  public void save(){
	    inicializarDao();
	try {
		Boolean respuestaResponsable = almacenarInformacionResponsable();
		Boolean respuestaEstudiante = almacenarInformacionPersonal();
		Boolean respuestaInscripcion = almacenarInformacionInscripcion();
		if(respuestaResponsable && respuestaEstudiante && respuestaInscripcion)
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro almacenado");
		else
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ha ocurrido un error al intentar inscribir al estudiante");
	} catch (Exception e) {
		e.printStackTrace();
		logger.info("Ha ocurrido un error al guardar la informacion de InscripcionController: "+e.toString());
		message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo almacenar");
	}finally{
		init();
        FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
        RequestContext.getCurrentInstance().update("messageGlobal");
	}
	
  }
  
	public void verDetalles(Integer idInscripcion) {
		incializarValores();
		inicializarDao();
		try {
			inscripcion = inscripcionDao.obtenerPorId(idInscripcion);
			responsableEstudiante = responsableEstudianteDao.obtenerResponsableEstudiantePorAlumno(inscripcion.getEstudiante().getIdEstudiante());
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Informacion Cargada");
			RequestContext.getCurrentInstance().execute("PF('dlgDetalles').show();");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Ha ocurrido un error al ver detalles de InscripcionController: " + e.toString());
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo cargar la informacion");
		} finally {
			FacesContext.getCurrentInstance().addMessage("messageGlobal", message);
			RequestContext.getCurrentInstance().update("formDetalles");
			RequestContext.getCurrentInstance().update("messageGlobal");
		}
	}
  
  
  private Boolean almacenarInformacionResponsable() throws Exception{
	  	try {
			direccion.setMunicipio(municipioDao.obtenerPorId(municipio.getIdmunicipio()));
			direccion = direccionDao.crearEntidad(direccion);
			personaResponsable.setDireccion(direccion);
	  		personaResponsable.setEstadoCivil(estadoCivilDao.obtenerPorId(estadoCivilResponsable.getIdEstadoCivil()));
	  		personaResponsable.setMedioTransporte(medioTransporteDao.obtenerPorId(medioTransporteEstudiante.getIdMediot()));
	  		personaResponsable.setUsuarioModifica("SYSTEM");
	  		personaResponsable = personaDao.crearEntidad(personaResponsable);
	  		responsable.setPersona(personaResponsable);
	  		responsable.setParentesco(parentescoDao.obtenerPorId(parentesco.getIdparentesco()));
	  		responsableDao.crearEntidad(responsable);
	  		return true;
		} catch (Exception e) {
			throw  new Exception(e);		
		}  
  }
  
  private Boolean almacenarInformacionPersonal() throws Exception{
	  try {
		personaEstudiante.setDireccion(direccion);
  		personaEstudiante.setEstadoCivil(estadoCivilDao.obtenerPorId(estadoCivilEstudiante.getIdEstadoCivil()));
  		personaEstudiante.setMedioTransporte(medioTransporteDao.obtenerPorId(medioTransporteEstudiante.getIdMediot()));
  		personaEstudiante.setUsuarioModifica("SYSTEM");
  		personaEstudiante = personaDao.crearEntidad(personaEstudiante); 
  		estudiante.setPersona(personaEstudiante);
  		estudiante.setInstitucionprocedencia(institucionProcedenciaDao.obtenerPorId(institucionProcedencia.getIdinsitucionprocedencia()));
  		nivelEspecialidad.setNivel(nivelDao.obtenerPorId(nivel.getIdNivel()));
  		nivelEspecialidad.setEspecialidad(especialidadDao.obtenerPorId(especialidad.getIdespecialidad()));
  		estudiante.setNivelEspecialidad(nivelEspecialidad);
  		estudiante.setNie(CustomUtils.obtenerNie(personaEstudiante.getNombres(), personaEstudiante.getApellidos(), personaEstudiante.getIdPersona()));
  		logger.info("Repite Grado: "+estudiante.getRepiteGrado());
  		estudiante = estudianteDao.crearEntidad(estudiante);  		
  		expendienteMedico.setEstudiante(estudiante);
  		expendienteMedico.setEnfermedad(enfermedadDao.obtenerPorId(enfermedad.getIdenfermedad()));
  		expendienteMedico = expedienteMedicoDao.crearEntidad(expendienteMedico);
  		responsableEstudiante.setEstudiante(estudiante);
  		responsableEstudiante.setResponsable(responsable);
  		responsableEstudiante = responsableEstudianteDao.crearEntidad(responsableEstudiante);
  		expediente.setAnioEscolar(CustomUtils.obtenerAnioActual());
  		expediente.setFechacreacion(new Date());
  		expediente.setEstado("A");
  		expediente.setEstudiante(estudiante);
  		expediente.setDescripcion("Expediente Creado del alumno: "+personaEstudiante.getNombres()+" "+personaEstudiante.getApellidos());
  		expediente = expedienteDao.crearEntidad(expediente);
		return true;
	} catch (Exception e) {
		throw  new Exception(e);	
	}
  }
  
  private Boolean almacenarInformacionInscripcion() throws Exception{
	  	try {
	  		estadoInscripcion = estadoInscripcionDao.obtenerPorId(1);
			inscripcion.setEstudiante(estudiante);
			inscripcion.setEstadoinscripcion(estadoInscripcion);
			inscripcion.setFecha(new Timestamp(new Date().getTime()));
			inscripcion.setModalidad(modalidad);
			inscripcion.setUsuariomod("SYSTEM");
			inscripcion.setAnioEscolar(CustomUtils.obtenerAnioActual());
			inscripcion = inscripcionDao.crearEntidad(inscripcion);
	  		return true;
		} catch (Exception e) {
			throw  new Exception(e);		
		}  
}

	
	public void cargarListas(){
		try {
			departamentos = departamentoDao.obtenerTodos();
			for (Departamento departamento : departamentos) {
				data.put(departamento.getNombre(), departamento.getMunicipios());
			}
			mediosTransporte = medioTransporteDao.obtenerTodos();
			parentescos = parentescoDao.obtenerTodos();
			especialidades = especialidadDao.obtenerTodos();
			enfermedades = enfermedadDao.obtenerTodos();
			niveles = nivelDao.obtenerTodos();
			institucionesProcedencia = institucionProcedenciaDao.obtenerTodos();		
			usuario = CustomUtils.obtenerUsuarioSesion();
			logger.info("Listado de inscripciones"+inscripciones.size());
		} catch (Exception e) {
			logger.info("Ha ocurrido un error al cargar las listas: "+e.toString());
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedieron crear listas de prerenderizado");
		}
	}
	
	public String onFlowProcess(FlowEvent event) {

		return event.getNewStep();

	}
	
	
	public void onDepartamentoChange() {
		if (departamento != null && !departamento.equals(""))
			municipios = data.get(departamento);
		else
			municipios = new ArrayList<Municipio>();
	}

	private void incializarValores(){
		personaResponsable = new Persona();
		personaEstudiante = new Persona();
		parentesco = new Parentesco();
		responsable = new Responsable();
		estadoCivilResponsable = new EstadoCivil();
		estadoCivilEstudiante = new EstadoCivil();
		medioTransporteResponsable = new MedioTransporte();
		medioTransporteEstudiante = new MedioTransporte();
		direccion = new Direccion();
		estudiante = new Estudiante();
		responsableEstudiante = new ResponsableEstudiante();
		institucionProcedencia = new Institucionprocedencia();
		nivel = new Nivel();
		especialidad = new Especialidad();
		nivelEspecialidad = new NivelEspecialidad();
		enfermedad = new Enfermedad();
		expendienteMedico = new ExpedienteMedico();
		inscripcion = new Inscripcion();
		modalidad = new Modalidad();
		estadoInscripcion = new Estadoinscripcion();
		expediente = new Expediente();
		usuarioEstudiante = new Usuario();
		usuarioRolEstudiante = new UsuarioRol();
		municipio = new Municipio();
		usuario = new Usuario();
		ahora = new Date();
		enfermedades = new ArrayList<Enfermedad>();
		estadoInscripciones = new ArrayList<Estadoinscripcion>();
		modalidades = new ArrayList<Modalidad>();
		niveles = new ArrayList<Nivel>();
		especialidades = new ArrayList<Especialidad>();
		institucionesProcedencia = new ArrayList<Institucionprocedencia>();
		parentescos = new ArrayList<Parentesco>();		
		mediosTransporte = new ArrayList<MedioTransporte>();
		estadosCivil = new ArrayList<EstadoCivil>();
		departamentos = new ArrayList<Departamento>();
		municipios = new ArrayList<Municipio>();
		data = new HashMap<String, List<Municipio>>();
	}
	
	private void inicializarDao(){
		departamentoDao = new DepartamentoDao();
		medioTransporteDao = new MedioTransporteDao();
		parentescoDao = new ParentescoDao();
		especialidadDao = new EspecialidadDao();
		enfermedadDao = new EnfermedadDao();
		nivelDao = new NivelDao();
		institucionProcedenciaDao = new InstitucionProcedenciaDao();
		personaDao = new PersonaDao();
		responsableDao = new ResponsableDao();
		direccionDao = new DireccionDao();
		expedienteMedicoDao = new ExpedienteMedicoDao();
		estudianteDao = new EstudianteDao();
		responsableEstudianteDao = new ResponsableEstudianteDao();
		inscripcionDao = new InscripcionDao();
		estadoInscripcionDao = new EstadoInscripcionDao();
		municipioDao = new MunicipioDao();
		estadoCivilDao = new EstadoCivilDao();
		expedienteDao = new ExpedienteDao();
	}
}
