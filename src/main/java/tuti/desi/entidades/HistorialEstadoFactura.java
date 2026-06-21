package tuti.desi.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estado_factura")
public class HistorialEstadoFactura {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;

    private LocalDateTime fechaEstado;

    public HistorialEstadoFactura() {}

    public HistorialEstadoFactura(Factura factura, EstadoFactura estado) {
        this.factura = factura;
        this.estado = estado;
        this.fechaEstado = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Factura getFactura() { return factura; }
    public void setFactura(Factura factura) { this.factura = factura; }

    public EstadoFactura getEstado() { return estado; }
    public void setEstado(EstadoFactura estado) { this.estado = estado; }

    public LocalDateTime getFechaEstado() { return fechaEstado; }
    public void setFechaEstado(LocalDateTime fechaEstado) { this.fechaEstado = fechaEstado; }

}
