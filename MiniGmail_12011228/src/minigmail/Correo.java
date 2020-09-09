package minigmail;

import java.io.Serializable;
import java.util.Date;

public class Correo implements Serializable {
    private Cuenta emisor;
    private Cuenta receptorPrincipal;
    private String asunto;
    private Documento cuerpo;
    private Date fechaEnvio;
    private boolean leido;

    public static final long SerialVersionUID = 1L;
    
    public Correo() {
    }

    public Correo(Cuenta emisor, Cuenta receptorPrincipal, String asunto, Documento cuerpo, Date fechaEnvio, boolean leido) {
        this.emisor = emisor;
        this.receptorPrincipal = receptorPrincipal;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fechaEnvio = fechaEnvio;
        this.leido = leido;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public Cuenta getEmisor() {
        return emisor;
    }

    public void setEmisor(Cuenta emisor) {
        this.emisor = emisor;
    }

    public Cuenta getReceptorPrincipal() {
        return receptorPrincipal;
    }

    public void setReceptorPrincipal(Cuenta receptorPrincipal) {
        this.receptorPrincipal = receptorPrincipal;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Documento getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(Documento cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    @Override
    public String toString() {
        return asunto;
    }
}
