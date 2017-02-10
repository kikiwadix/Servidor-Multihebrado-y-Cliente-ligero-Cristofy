/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Vista.VistaServidor.taVentanaTexto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JuanPablo
 */
public class ConectarBDLogin {
    
    boolean resultado = false;
    ResultSet rs;

    public ConectarBDLogin() {
    }
    
    public boolean conectarYcomprobar(String user, String pass, String ip, String usuarioBD, String passwordBD){
        
        String nombreBD = "cristofy";
        
       //para que añada el puerto para el servidor de Tarek
        if("178.62.241.66".equals(ip)){
            ip = "178.62.241.66" + ":3306";
        }
        
       
        
        try{
            
            System.out.println("\nIntentando conectarme con la base de datos... \n");
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + nombreBD, usuarioBD , passwordBD );
            System.out.println("Conectado con éxito\n");
            
            
            //devolver datos de la bd
            Statement estado = con.createStatement();
            rs = estado.executeQuery("select * from usuario");
        }
        
        catch(SQLException e){
            System.out.println("Error de MySQL\n");
            System.out.println("_______________________________________\n");
            
        }
        
        catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("_______________________________________\n");
        }
        
        catch(Exception e){
            System.out.println("Se ha encontrado el siguiente error: " + e.getMessage() + "\n");
            System.out.println("_______________________________________\n");
        }
        
        
        //Ahora comprobamos aqui mismo los datos del usuario
        
        try{
            while(rs.next()){
                
                String userBD = rs.getString("Login");
                String passBD = rs.getString("Password");
                
                if( userBD.equals(user) && passBD.equals(pass) ){
                    resultado = true;
                }
                    
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ConectarBDLogin.class.getName()).log(Level.SEVERE, null, ex);
        }    
            
        return resultado;
    }
}
