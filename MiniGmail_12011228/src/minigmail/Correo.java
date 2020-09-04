package minigmail;

import java.util.Date;

public class Correo {
    private Cuenta emisor;
    private Cuenta receptor;
    private String asunto;
    private String cuerpo;
    private Date fechaEnvio;
    private boolean leido;

    public Correo() {
    }

    public Correo(Cuenta emisor, Cuenta receptor, String asunto, String cuerpo, Date fechaEnvio, boolean leido) {
        this.emisor = emisor;
        this.receptor = receptor;
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

    public Cuenta getReceptor() {
        return receptor;
    }

    public void setReceptor(Cuenta receptor) {
        this.receptor = receptor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
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
