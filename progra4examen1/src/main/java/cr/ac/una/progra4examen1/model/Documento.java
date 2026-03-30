package cr.ac.una.progra4examen1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Documento")
public class Documento {

    @Id
    private String id;

    private String descripcion;

    private float monto;

    private float timbres;

    @ManyToOne
    @JoinColumn(name = "tipo")
    private TipoDocumento tipo;

    public Documento() {}

    public Documento(String id, String descripcion, float monto, float timbres, TipoDocumento tipo) {
        this.id          = id;
        this.descripcion = descripcion;
        this.monto       = monto;
        this.timbres     = timbres;
        this.tipo        = tipo;
    }

    public String getId()              { return id; }
    public void   setId(String id)     { this.id = id; }

    public String getDescripcion()                   { return descripcion; }
    public void   setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public float getMonto()              { return monto; }
    public void  setMonto(float monto)   { this.monto = monto; }

    public float getTimbres()                { return timbres; }
    public void  setTimbres(float timbres)   { this.timbres = timbres; }

    public TipoDocumento getTipo()                  { return tipo; }
    public void          setTipo(TipoDocumento tipo) { this.tipo = tipo; }
}