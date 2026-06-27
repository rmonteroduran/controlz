package tuti.desi.entidades;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name="contratos")
public class Contrato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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

    private boolean eliminada = false;

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

    public EstadoContrato getEstado() { return estado; }
    public void setEstado(EstadoContrato estado) { this.estado = estado; }

    public boolean isEliminada() { return eliminada; }
    public void setEliminada(boolean eliminada) { this.eliminada = eliminada; }

}
