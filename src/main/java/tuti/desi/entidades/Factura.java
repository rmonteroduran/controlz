package tuti.desi.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "facturas")
public class Factura {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    private String concepto;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private Double importe;

    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;
    private LocalDate fechaPago;

    @Enumerated(EnumType.STRING)
    private MedioDePago medioDePago;

    private Double importePago;
    private Double interes;

    private boolean eliminada = false;

    public Factura() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public Double getImporte() { return importe; }
    public void setImporte(Double importe) { this.importe = importe; }

    public EstadoFactura getEstado() { return estado; }
    public void setEstado(EstadoFactura estado) { this.estado = estado; }

    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }

    public MedioDePago getMedioDePago() { return medioDePago; }
    public void setMedioDePago(MedioDePago medioDePago) { this.medioDePago = medioDePago; }

    public Double getImportePago() { return importePago; }
    public void setImportePago(Double importePago) { this.importePago = importePago; }

    public Double getInteres() { return interes; }
    public void setInteres(Double interes) { this.interes = interes; }

    public boolean isEliminada() { return eliminada; }
    public void setEliminada(boolean eliminada) { this.eliminada = eliminada; }
}
