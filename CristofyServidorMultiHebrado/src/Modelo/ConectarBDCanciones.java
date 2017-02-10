/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author JuanPablo
 */
public class ConectarBDCanciones {
    
    

    public ConectarBDCanciones() {
    }
    
    public ArrayList<Cancion> conectarYdevolverCanciones(String ip, String userBD, String passBD) {
        
        String nombreBD = "cristofy";
        //creo el array donde se meterán los resultados de la conexion
        ArrayList<Cancion> listaCanciones = new ArrayList<>();
        
      
        //para que añada el puerto para el servidor de Tarek
        if("178.62.241.66".equals(ip)){
            ip = "178.62.241.66" + ":3306";
        }
        
        try{
            
           
            System.out.println("\nIntentando conectarme con la base de datos... \n");
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + nombreBD, userBD, passBD );
            System.out.println("Conectado con éxito\n");
            
            
            //devolver datos de la bd
            Statement estado = con.createStatement();
            ResultSet rs = estado.executeQuery("select * from cancion");
            
           
            while (rs.next()){
                
                listaCanciones.add(new Cancion(rs.getInt("Id"), rs.getString("Titulo"), rs.getString("Artista"), rs.getString("Duracion"), rs.getString("Genero"), rs.getInt("Valoracion"), rs.getLong("Tamanio_bytes"), rs.getString("Ruta"), rs.getString("Id_usuario")   ) );
               
            }
            
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
        
       
        return listaCanciones;
    }

    
    
    
    //AHORA EL METODO DE INSERTAR UNA CANCION EN LA BD
    public boolean insertarCancionBD(String ip, Cancion cancion){
        
        boolean resultado = false;
        String nombreBD = "cristofy";
        
        
        try{
            
           
            System.out.println("\nIntentando conectarme con la base de datos... \n");
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + nombreBD, "root", "" );
            System.out.println("Conectado con éxito\n");
            
            
            PreparedStatement psInsertar = con.prepareStatement("insert into cancion (Titulo, Artista, Duracion, Calidad, Valoracion, Ruta)" + " values(?, ?, ?, ?, ?, ?)");
              
            psInsertar.setString(1, cancion.getTitulo());
            psInsertar.setString(2, cancion.getArtista());
            psInsertar.setString(3, cancion.getDuracion());
            psInsertar.setString(4, cancion.getGenero());
            psInsertar.setString(5, Integer.toString(cancion.getValoracion()) );
            psInsertar.setString(6, cancion.getRuta());
             
            psInsertar.executeUpdate();
            
            
            resultado = true;
            
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
        
        
        return resultado;
    }
    
    
    
}
