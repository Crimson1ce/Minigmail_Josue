package minigmail;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Clase20200623 {

    public static void main(String[] args) {
        //listar        
        Dba db = new Dba("./base1.mdb");
        db.conectar();
        try {
            db.query.execute("select cuenta,nombre from alumnos");
            ResultSet rs = db.query.getResultSet();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "--->" + rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        db.desconectar();
       
       
        //agregar
        Dba dba = new Dba("./base1.mdb");
        db.conectar();
        try {
            int c;
            String n;
            c = Integer.parseInt(JOptionPane.showInputDialog("Codigo"));
            n = JOptionPane.showInputDialog("Nombre");
            dba.query.execute("INSERT INTO alumnos"
                    + " (cuenta,nombre)"
                    + " VALUES ('" + c + "', '" + n + "')");
            dba.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dba.desconectar();
        
       
        //modificar
        Dba dab = new Dba("./base1.mdb");
        dab.conectar();
        try {
            dab.query.execute("update alumnos set nombre='Donald Trump' where cuenta=5000");
            dab.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dab.desconectar();
       
       
       //eliminar
       /* Dba db = new Dba("./base1.mdb");
        db.conectar();
        try {
            db.query.execute("delete from alumnos where cuenta=5000");
            db.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        db.desconectar();*/



       
       
       

    }
}
