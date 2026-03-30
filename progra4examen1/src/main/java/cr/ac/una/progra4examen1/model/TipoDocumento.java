package cr.ac.una.progra4examen1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "Tipo")
public class TipoDocumento {

    @Id
    private String id;

    private String nombre;

    public TipoDocumento() {}

    public TipoDocumento(String id, String nombre) {
        this.id     = id;
        this.nombre = nombre;
    }

    public String getId()              { return id; }
    public void   setId(String id)     { this.id = id; }

    public String getNombre()                { return nombre; }
    public void   setNombre(String nombre)   { this.nombre = nombre; }
}
