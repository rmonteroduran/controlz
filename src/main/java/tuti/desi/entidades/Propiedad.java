package tuti.desi.entidades;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "propiedades")
public class Propiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;
    
    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;
    
    @Enumerated(EnumType.STRING)
    
    private TipoPropiedad tipo;

    private Integer cantidadAmbientes;
    private Double metrosCuadrados;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoDisponibilidad estadoDisponibilidad;

    private boolean eliminada = false; // Bandera para la baja lógica obligatoria

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Persona propietario;

    // Relación para guardar el historial de cambios de estado
    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistorialEstadoPropiedad> historialEstados = new ArrayList<>();

    // Constructor
    public Propiedad() {}

    public Propiedad(Long id, String direccion, Ciudad ciudad, TipoPropiedad tipo, int cantidadAmbientes, double metrosCuadrados, String descripcion, EstadoDisponibilidad EstadoDisponibilidad,Persona propietario)
    {
        this.id = id;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.tipo = tipo;
        this.cantidadAmbientes = cantidadAmbientes;
        this.metrosCuadrados = metrosCuadrados;
        this.descripcion = descripcion;
        this.estadoDisponibilidad = EstadoDisponibilidad;
        this.propietario = propietario;
    }

    // ==========================================
    // GETTERS Y SETTERS (Métodos de acceso)
    // ==========================================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Ciudad getCiudad() { return ciudad; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }

    public TipoPropiedad getTipo() { return tipo; }
    public void setTipo(TipoPropiedad tipo) { this.tipo = tipo; }

    public Integer getCantidadAmbientes() { return cantidadAmbientes; }
    public void setCantidadAmbientes(Integer cantidadAmbientes) { this.cantidadAmbientes = cantidadAmbientes; }

    public Double getMetrosCuadrados() { return metrosCuadrados; }
    public void setMetrosCuadrados(Double metrosCuadrados) { this.metrosCuadrados = metrosCuadrados; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public EstadoDisponibilidad getEstadoDisponibilidad() { return estadoDisponibilidad; }
    public void setEstadoDisponibilidad(EstadoDisponibilidad estadoDisponibilidad) { this.estadoDisponibilidad = estadoDisponibilidad; }

    public boolean isEliminada() { return eliminada; }
    public void setEliminada(boolean eliminada) { this.eliminada = eliminada; }

    public Persona getPropietario() { return propietario; }
    public void setPropietario(Persona propietario) { this.propietario = propietario; }

    public List<HistorialEstadoPropiedad> getHistorialEstados() { return historialEstados; }
    public void setHistorialEstados(List<HistorialEstadoPropiedad> historialEstados) { this.historialEstados = historialEstados; }
}