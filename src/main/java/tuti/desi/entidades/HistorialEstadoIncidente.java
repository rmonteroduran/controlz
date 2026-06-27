package tuti.desi.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estados_incidente")
public class HistorialEstadoIncidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoIncidente estado;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "incidente_id", nullable = false)
    private Incidente incidente;

    // Constructores
    public HistorialEstadoIncidente() {}

    public HistorialEstadoIncidente(EstadoIncidente estado, LocalDateTime fechaHora, Incidente incidente) {
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.incidente = incidente;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public EstadoIncidente getEstado() { return estado; }
    public void setEstado(EstadoIncidente estado) { this.estado = estado; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Incidente getIncidente() { return incidente; }
    public void setIncidente(Incidente incidente) { this.incidente = incidente; }
}
