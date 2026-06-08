package tuti.desi.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dniCuit;
    private String telefono;
    private String email;
    private String domicilio;

    private boolean eliminada = false;

    // Constructores
    public Persona() {}

    public Persona(String nombre, String apellido, String dniCuit, String telefono, String email, String domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dniCuit = dniCuit;
        this.telefono = telefono;
        this.email = email;
        this.domicilio = domicilio;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDniCuit() { return dniCuit; }
    public void setDniCuit(String dniCuit) { this.dniCuit = dniCuit; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public boolean isEliminada() { return eliminada; }
    public void setEliminada(boolean eliminada) { this.eliminada = eliminada; }

    // Método para obtener el nombre completo en listados
    public String getNombreCompleto() {
        return (apellido != null ? apellido : "") + ", " + (nombre != null ? nombre : "");
    }
}
