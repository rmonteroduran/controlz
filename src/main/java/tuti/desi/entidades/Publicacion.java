package tuti.desi.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double precioMensual;

    private String condiciones;

    private String descripcion;

    private LocalDate fechaPublicacion;

    private boolean eliminada = false;

    @Enumerated(EnumType.STRING)
    private EstadoPublicacion estadoPublicacion = EstadoPublicacion.ACTIVA;

    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;
    
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visita> visitas = new ArrayList<>();

    // Historial de estados
    @OneToMany(mappedBy = "publicacion",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<HistorialEstadoPublicacion> historialEstados = new ArrayList<>();

    public Publicacion() {
    }

    public Publicacion(Long id,
                       Double precioMensual,
                       String condiciones,
                       String descripcion,
                       LocalDate fechaPublicacion,
                       boolean eliminada,
                       Propiedad propiedad,
                       EstadoPublicacion estadoPublicacion) {

        this.id = id;
        this.precioMensual = precioMensual;
        this.condiciones = condiciones;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.eliminada = eliminada;
        this.propiedad = propiedad;
        this.estadoPublicacion = estadoPublicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(Double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public boolean isEliminada() {
        return eliminada;
    }

    public void setEliminada(boolean eliminada) {
        this.eliminada = eliminada;
    }

    public EstadoPublicacion getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadoPublicacion estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }
    
    public List<Visita> getVisitas() {
        return visitas;
    }
    
    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    public List<HistorialEstadoPublicacion> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(List<HistorialEstadoPublicacion> historialEstados) {
        this.historialEstados = historialEstados;
    }

}