package tuti.desi.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
@Table(name="contratos")
public class Contrato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HistorialEstadoContrato> historialEstados = new ArrayList<>();
    private List<Incidente> incidentes = new ArrayList<>();

	@ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;

    @ManyToOne
    @JoinColumn(name = "inquilino_id")
    private Persona inquilino;

    private LocalDate fechaInicio;
    private Integer duracionMeses;
    private Double importeMensual;
    private Integer diaVencimiento;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoContrato estado;

    private boolean eliminado = false;
    // Getters y Setters
    public Contrato() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Propiedad getPropiedad() { return propiedad; }
    public void setPropiedad(Propiedad propiedad) { this.propiedad = propiedad; }

    public Persona getInquilino() { return inquilino; }
    public void setInquilino(Persona inquilino) { this.inquilino = inquilino; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public Integer getDuracionMeses() { return duracionMeses; }
    public void setDuracionMeses(Integer duracionMeses) { this.duracionMeses = duracionMeses; }

    public Double getImporteMensual() { return importeMensual; }
    public void setImporteMensual(Double importeMensual) { this.importeMensual = importeMensual; }

    public Integer getDiaVencimiento() { return diaVencimiento; }
    public void setDiaVencimiento(Integer diaVencimiento) { this.diaVencimiento = diaVencimiento; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public List<Incidente> getIncidentes() {
        return incidentes;
    }
    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public EstadoContrato getEstado() { return estado; }
    public void setEstado(EstadoContrato estado) { this.estado = estado; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    public void registrarCambioEstado(EstadoContrato nuevoEstado) {
        this.estado = nuevoEstado; // Cambia el estado actual del contrato
        
        // Crea el renglón del historial automáticamente con la fecha/hora actual
        HistorialEstadoContrato historial = new HistorialEstadoContrato(nuevoEstado, LocalDateTime.now(), this);
        
        // Lo agrega a la lista para que se guarde en la base de datos
        this.historialEstados.add(historial);
    }

}
