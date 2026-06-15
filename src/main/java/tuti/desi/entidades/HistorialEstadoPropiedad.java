package tuti.desi.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estados")
public class HistorialEstadoPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoDisponibilidad estado;

    private LocalDateTime fechaHora;

    // Relación Muchos a Uno: Muchos registros de historial pertenecen a una sola Propiedad
    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;

    // Constructores
    public HistorialEstadoPropiedad() {}
    
    public HistorialEstadoPropiedad(EstadoDisponibilidad estado, LocalDateTime fechaHora, Propiedad propiedad) {
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.propiedad = propiedad;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public EstadoDisponibilidad getEstado() { return estado; }
    public void setEstado(EstadoDisponibilidad estado) { this.estado = estado; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Propiedad getPropiedad() { return propiedad; }
    public void setPropiedad(Propiedad propiedad) { this.propiedad = propiedad; }
}