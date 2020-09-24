package minigmail;

import java.io.Serializable;
import java.util.Date;

public class Tarea implements Serializable{
    private String asunto;
    private Date fechaInicio;
    private Date fechaVencimiento;
    private String estado;
    private String prioridad;
    private int hora;
    private int minuto;
    private boolean semanal;
    private boolean[] dias;
    private Date fechaFinalRecordatorio;
    private int maxRepeticiones;
    private int repeticiones;
    private String descripcion;

    public Tarea(String asunto, Date fechaInicio, Date fechaVencimiento, String estado, String prioridad, int hora, int minuto, boolean semanal, boolean[] dias, Date fechaFinalRecordatorio, int maxRepeticiones, String descripcion) {
        this.asunto = asunto;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.prioridad = prioridad;
        this.hora = hora;
        this.minuto = minuto;
        this.semanal = semanal;
        this.dias = dias;
        this.fechaFinalRecordatorio = fechaFinalRecordatorio;
        this.maxRepeticiones = maxRepeticiones;
        this.repeticiones = 0;
        this.descripcion = descripcion;
    }

    public int getMaxRepeticiones() {
        return maxRepeticiones;
    }

    public void setMaxRepeticiones(int maxRepeticiones) {
        this.maxRepeticiones = maxRepeticiones;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public boolean isSemanal() {
        return semanal;
    }

    public void setSemanal(boolean semanal) {
        this.semanal = semanal;
    }

    public boolean[] getDias() {
        return dias;
    }

    public void setDias(boolean[] dias) {
        this.dias = dias;
    }

    public Date getFechaFinalRecordatorio() {
        return fechaFinalRecordatorio;
    }

    public void setFechaFinalRecordatorio(Date fechaFinalRecordatorio) {
        this.fechaFinalRecordatorio = fechaFinalRecordatorio;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        if (repeticiones<=maxRepeticiones) {
            this.repeticiones = repeticiones;
        }
        if (this.repeticiones!=0 && repeticiones==maxRepeticiones) {
            setEstado("Terminado");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return asunto;
    }
}
