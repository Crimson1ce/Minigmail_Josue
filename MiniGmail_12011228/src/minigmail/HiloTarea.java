package minigmail;

import java.util.Date;
import javax.swing.JOptionPane;

public class HiloTarea extends Thread {

    private Tarea tarea;
    private boolean viva;
    private Cuenta cuenta;
    private final String mensaje;

    private static final long SEMANA = 604800000L;

    public HiloTarea(Tarea tarea, Cuenta cuenta) {
        this.tarea = tarea;
        this.viva = true;
        this.cuenta = cuenta;
        this.mensaje = "RECORDATORIO DE TAREA\n\n"
                + "Prioridad: " + tarea.getPrioridad() + "\n"
                + "Asunto: " + tarea.getAsunto() + "\n"
                + "Fecha de vencimiento: "
                + new java.text.SimpleDateFormat("dd/MM/yyyy").format(tarea.getFechaVencimiento()) + "\n\n"
                + "Descripción:\n" + tarea.getDescripcion();
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return "HiloTarea{" + "tarea=" + tarea + ", viva=" + viva + '}';
    }

    public boolean condicional(boolean p, boolean q) {
        if (p == true && q == false) {
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        if (!tarea.getEstado().equals("Terminado")) {

            while (true) {
                Date ahora = new Date();
                //Validamos hora de inicio
                if (ahora.getTime() > tarea.getFechaInicio().getTime()) {
                    tarea.setEstado("Comenzado");
                    break;
                }
            }
            while (viva) {
                Date ahora = new Date();
                //Validamos fecha de finalización
                boolean todavia;

                if (tarea.getMaxRepeticiones() != 0) {
                    todavia = tarea.getRepeticiones() < tarea.getMaxRepeticiones();
                } else if (tarea.getFechaFinalRecordatorio() != null) {
                    todavia = (ahora.getTime() < tarea.getFechaFinalRecordatorio().getTime());
                } else {
                    todavia = true;
                }

                if (todavia) {
                    boolean flag
                            = condicional(
                                    ahora.getTime() > tarea.getFechaInicio().getTime() + SEMANA,
                                    tarea.isSemanal()
                            );
                    if (flag) {
                        //Si hoy es uno de los días de repetición
                        if (tarea.getDias()[ahora.getDay()]) {

                            //Comprobamos horas y minutos
                            if (ahora.getHours() == tarea.getHora()
                                    && ahora.getMinutes() == tarea.getMinuto()) {
                                JOptionPane.showMessageDialog(null, mensaje, "Recordatorio", JOptionPane.INFORMATION_MESSAGE);
                                tarea.setRepeticiones(tarea.getRepeticiones() + 1);
                                if (tarea.getRepeticiones() == tarea.getMaxRepeticiones()) {
                                    viva = false;
                                    tarea.setEstado("Terminado");
                                    break;
                                }
                            }
                            try {
                                sleep(45000L); //Revisa cada 45 segundos
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                } else {
                    viva = false;
                    tarea.setEstado("Terminado");
                    break;
                }
            }
        }
        if (tarea.getEstado().equals("Terminado")) {
            cuenta.getTareas().remove(tarea);
        }
    }

}
