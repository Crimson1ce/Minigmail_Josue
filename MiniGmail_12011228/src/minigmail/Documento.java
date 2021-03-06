package minigmail;

import java.io.Serializable;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

public class Documento implements Serializable{
    private JTextPane panel;
    private StyledDocument doc;
    private Style estilo;
    private String texto;
    
    public static final long serialVersionUID = 1L;

    public Documento(JTextPane panel, StyledDocument doc, Style estilo, String texto) {
        this.panel = panel;
        this.doc = doc;
        this.estilo = estilo;
        this.texto = texto;
    }

    public JTextPane getPanel() {
        return panel;
    }

    public void setPanel(JTextPane panel) {
        this.panel = panel;
    }

    public StyledDocument getDoc() {
        return doc;
    }

    public void setDoc(StyledDocument doc) {
        this.doc = doc;
    }

    public Style getEstilo() {
        return estilo;
    }

    public void setEstilo(Style estilo) {
        this.estilo = estilo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }

}
