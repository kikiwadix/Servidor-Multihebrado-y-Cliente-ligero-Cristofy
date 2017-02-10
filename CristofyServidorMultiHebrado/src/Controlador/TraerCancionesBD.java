/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cancion;
import Modelo.ConectarBDCanciones;
import java.util.ArrayList;

/**
 *
 * @author JuanPablo
 */
public class TraerCancionesBD {
    
    
    
    public String traerCanciones(String ip, String user, String usuarioBD, String passBD){
        
        //el arrayList de canciones que me devuelve el metodo
        ArrayList<Cancion> arrayCanciones = new ArrayList<>();
        
        //lo que voy a devolver ya en string
        //inicializo el string con su principio
        String string = "PROTOCOLCRISTOFY1.0#OKSTART#";
        
 
        //este metodo trae las canciones de la bd, llamando al modelo
        ConectarBDCanciones cc = new ConectarBDCanciones();
        //el metodo me devuelve un arrayList de cancines
        arrayCanciones = cc.conectarYdevolverCanciones(ip, usuarioBD, passBD);
        //y cuando ya las tengo, aki mismo hago la conversion de objeto a string
        
        
        
        
        for(int i=0;i<arrayCanciones.size();i++){
            string += arrayCanciones.get(i).getId();
            string += "@";
            string += arrayCanciones.get(i).getRuta();
            string += "@";
            string += arrayCanciones.get(i).getTamanio_bytes();
            string += "@";
            string += arrayCanciones.get(i).getTitulo();
            string += "@";
            string += arrayCanciones.get(i).getArtista();
            string += "@";
            string += arrayCanciones.get(i).getGenero();
            string += "@";
            string += arrayCanciones.get(i).getDuracion();
            string += "@";
            string += arrayCanciones.get(i).getValoracion();
            string += "@";
            string += arrayCanciones.get(i).getId_usuario();
            //voy a comprobar que el usuario que las pide, es el mismo usuario que la ha subido
            if(arrayCanciones.get(i).getId_usuario().equals(user)){
                string += ":TRUE";
            }else{
                string += ":FALSE";
            }
            
            string += "#";
            
        }
        
        //Y EL FINAL DEL ARCHIVO
        string += "END";
        
        
        //para probar lo que se genera
        System.out.println(string);
        
        
        return string;
    }
    
    
}
