package cr.ac.una.progra4examen1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Linea")
public class MisDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "documento")
    private Documento documento;

    private Integer cantidad;

    public MisDocumento() {}

    public MisDocumento(Usuario usuario, Documento documento, Integer cantidad) {
        this.usuario   = usuario;
        this.documento = documento;
        this.cantidad  = cantidad;
    }

    public Integer getId()               { return id; }
    public void    setId(Integer id)     { this.id = id; }

    public Usuario getUsuario()                  { return usuario; }
    public void    setUsuario(Usuario usuario)   { this.usuario = usuario; }

    public Documento getDocumento()                    { return documento; }
    public void      setDocumento(Documento documento) { this.documento = documento; }

    public Integer getCantidad()                   { return cantidad; }
    public void    setCantidad(Integer cantidad)   { this.cantidad = cantidad; }
}
