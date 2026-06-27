package tuti.desi.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "incidentes")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaIncidente categoria;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioridadIncidente prioridad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoIncidente estado;

    private boolean eliminado = false;

    @Column(name = "fecha_resolucion")
    private LocalDateTime fechaResolucion;

    @Column(name = "observaciones_resolucion", columnDefinition = "TEXT")
    private String observacionesResolucion;

    @Column(name = "costo_resolucion")
    private BigDecimal costoResolucion;

    @Column(name = "responsable_tecnico")
    private String responsableTecnico;

    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;

    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistorialEstadoIncidente> historialEstados = new ArrayList<>();

    // Constructores
    public Incidente() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public CategoriaIncidente getCategoria() { return categoria; }
    public void setCategoria(CategoriaIncidente categoria) { this.categoria = categoria; }

    public LocalDateTime getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; }

    public PrioridadIncidente getPrioridad() { return prioridad; }
    public void setPrioridad(PrioridadIncidente prioridad) { this.prioridad = prioridad; }

    public EstadoIncidente getEstado() { return estado; }
    public void setEstado(EstadoIncidente estado) { this.estado = estado; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    public LocalDateTime getFechaResolucion() { return fechaResolucion; }
    public void setFechaResolucion(LocalDateTime fechaResolucion) { this.fechaResolucion = fechaResolucion; }

    public String getObservacionesResolucion() { return observacionesResolucion; }
    public void setObservacionesResolucion(String observacionesResolucion) { this.observacionesResolucion = observacionesResolucion; }

    public BigDecimal getCostoResolucion() { return costoResolucion; }
    public void setCostoResolucion(BigDecimal costoResolucion) { this.costoResolucion = costoResolucion; }

    public String getResponsableTecnico() { return responsableTecnico; }
    public void setResponsableTecnico(String responsableTecnico) { this.responsableTecnico = responsableTecnico; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }

    public List<HistorialEstadoIncidente> getHistorialEstados() { return historialEstados; }
    public void setHistorialEstados(List<HistorialEstadoIncidente> historialEstados) { this.historialEstados = historialEstados; }
}

