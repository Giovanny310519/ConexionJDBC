
package conexionjdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
               
public class ConexionJDBC {

    public static void main(String[] args) {
        
        /*referenciar el usuario y password con el cual se concectara a la base datos*/
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3307/conexionjdbc";
        
        /*Establcecer conexion*/
        Connection conexion;
        Statement statement;
        ResultSet rs;

        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Establcecer conexion*/
        try {
             /*Metodos*/
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            
            /*INSERTAR informacion a la basse de datos, se debe cambiar el id cada vez que se corra el codigo para insertar el nuevo dato*/
            statement.executeUpdate("INSERT INTO EMPLEADOS (UserId, UserNombreEmpleado, UserPassword) VALUES(12, 'Pedro', '2626')");
            rs = statement.executeQuery("SELECT * FROM EMPLEADOS");
            System.out.println("Elemento Añadido");/*Mensaje*/
                    
            rs.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*CONSULTAR*/
        
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/conexionjdbc","root","");
            PreparedStatement sentencia = cn.prepareStatement("SELECT * FROM EMPLEADOS WHERE UserId = ?");
            sentencia.setString(1, "5");
            ResultSet crs = sentencia.executeQuery();
            crs.next();
            do {                
                System.out.println(crs.getInt("UserId") + " " + crs.getString("UserNombreEmpleado") + " " + crs.getInt("UserPassword"));
            }while (crs.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*EDITAR*/
        
        try {
            Connection cne = DriverManager.getConnection("jdbc:mysql://localhost:3307/conexionjdbc","root","");
            PreparedStatement sentenciaEditar = cne.prepareStatement("UPDATE EMPLEADOS SET UserNombreEmpleado = ? WHERE UserId = ?");
            sentenciaEditar.setString(1, "Camilo");
            sentenciaEditar.setString(2, "3");
            int edt = sentenciaEditar.executeUpdate();
            System.out.println("Elemento Editado: " + edt);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*ELIMINAR*/
        
        try {
            Connection cne = DriverManager.getConnection("jdbc:mysql://localhost:3307/conexionjdbc","root","");
            PreparedStatement sentenciaEliminar = cne.prepareStatement("DELETE FROM EMPLEADOS WHERE UserId = ? ");
            sentenciaEliminar.setString(1, "10");
            int elm = sentenciaEliminar.executeUpdate();
            
            System.out.println("Elemento Eliminado: " + elm);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*LISTAR*/
        
        try {
            Connection cnl = DriverManager.getConnection("jdbc:mysql://localhost:3307/conexionjdbc","root","");
            PreparedStatement sentenciaListar = cnl.prepareStatement("SELECT * FROM EMPLEADOS");
            ResultSet lrs = sentenciaListar.executeQuery();
            lrs.next();
            do {                
                /*System.out.println("ID", + "NOMBRE", + "PASSWORD");*/
                System.out.println(lrs.getInt("UserId") + " " + lrs.getString("UserNombreEmpleado") + " " + lrs.getInt("UserPassword"));
                
            } while (lrs.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}


