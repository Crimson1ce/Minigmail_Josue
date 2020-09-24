package minigmail;

import java.io.Serializable;

public class FiltroHora extends Filtro implements Serializable {
    private int horaInicio;
    private int minutoInicio;
    private int horaFinal;
    private int minutoFinal;

    public FiltroHora(int horaInicio, int minutoInicio, int horaFinal, int minutoFinal) {
        this.horaInicio = horaInicio;
        this.minutoInicio = minutoInicio;
        this.horaFinal = horaFinal;
        this.minutoFinal = minutoFinal;
    }

    public int getMinutoFinal() {
        return minutoFinal;
    }

    public void setMinutoFinal(int minutoFinal) {
        this.minutoFinal = minutoFinal;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getMinutoInicio() {
        return minutoInicio;
    }

    public void setMinutoInicio(int minutoInicio) {
        this.minutoInicio = minutoInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Override
    public String toString() {
        return "FiltroHora " + horaInicio + "h" + minutoInicio +"m - " + horaFinal + "h" + minutoFinal +"m";
    }
     
}
