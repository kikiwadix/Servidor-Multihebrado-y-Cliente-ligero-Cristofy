/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cancion;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author JuanPablo
 */
public class ConvertidorDeStringAArrayCancion {

    
    public ConvertidorDeStringAArrayCancion() {
    }
    
    
    public ArrayList<Cancion> convertirAArray(String stringServer){
        
        //atributos
        int id;
        String titulo;
        String artista;
        String duracion; 
        String genero;
        int valoracion;
        long tamanio_bytes;
        String ruta;
        String id_usuario;
        
        
        //para saber si quedan mas canciones o no
        Boolean existenCanciones;
                    
        //en este array se meteran las canciones
        ArrayList<Cancion> arrayCanciones = new ArrayList<>();
        
        //en este array se mete en bruto lo que el servidor envió
        ArrayList<String> mensajeServidor = new ArrayList<>();
        //en este segundo array ya se meten las canciones
        
        //Array auxiliar para meter los datos de cada cancion
        ArrayList<String> aux = new ArrayList<>();
        
        
        //esto es una herramienta que divide un string segun el caracter que le metas
        StringTokenizer tokens = new StringTokenizer(stringServer,"#");
        while(tokens.hasMoreTokens()){
            //meto en el array los parametros del protocolo
            //1º en la posicion 0 se metera PROTOCOLCRISTOFY1.0
            //2º en la posicion 1 se metera OKSTART
            //Y EN LAS SIGUIENTES LAS CANCIONES
            mensajeServidor.add(tokens.nextToken());
                        
        }
        
        
        //ahora lo tengo divido en canciones, ahora voy a sacar los valores de cada cancion
        //y paro cuando encuentre un END
        //la variable existenCanciones para saber si siguen quedando canciones en el array de Strings
        existenCanciones = true;
        for(int i=2;i<mensajeServidor.size() && existenCanciones;i++){
            if("END".equals(mensajeServidor.get(i))){
                existenCanciones = false;
            }else{
                 
                //borro el contenido del arrayAux, para que se meta una nueva cancion
                aux.clear();
                
                StringTokenizer tokenss = new StringTokenizer(mensajeServidor.get(i),"@");
                while(tokenss.hasMoreTokens()){
                    //meto en el array los parametros del protocolo
                    //1º en la posicion 0 se metera la id
                    //1º en la posicion 1 se metera la ruta
                    //2º en la posicion 2 se metera el tamaño
                    //2º en la posicion 3 se metera el titulo
                    //2º en la posicion 4 se metera el artista
                    //2º en la posicion 5 se metera el genero
                    //2º en la posicion 6 se metera la duracion
                    //2º en la posicion 7 se metera la valoracion
                    //2º en la posicion 8 se metera el propietario
                    aux.add(tokenss.nextToken());

                }
                
                //y ahora que tengo los datos de una cancion metidos en el array aux, voy a insertarla en el array de canciones
                //no sin antes, para que sea más claro, pasarlos a variables
                id = Integer.parseInt(aux.get(0));
                ruta = aux.get(1);
                tamanio_bytes = Long.parseLong(aux.get(2));
                titulo = aux.get(3);
                artista = aux.get(4);
                genero = aux.get(5);
                duracion = aux.get(6); 
                valoracion = Integer.parseInt(aux.get(7));
                id_usuario = aux.get(8);
                
                
                //ahora si inserto cancion en el array
                arrayCanciones.add(new Cancion(id, titulo, artista, duracion, genero, valoracion, tamanio_bytes, ruta, id_usuario));
                                   
            }
        }
                        
        
        
        
        
        return arrayCanciones;
        
        
    }
    
    
    
    
}
