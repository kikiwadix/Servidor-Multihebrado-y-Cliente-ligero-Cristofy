/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author JuanPablo
 */
public class ProtocoloCliente {
    
    private static final int COMPROBAR_SI_CONECTADO = 0;
    private static final int LISTO_PARA_TRAER_CANCIONES_BD = 1;
    
    //este arrayList es para meter el mensaje del SERVIDOR partido en trozos
    ArrayList<String> mensajeServidor = new ArrayList<>();
   
    //nombre del cliente
    String login;
   

    private int state = COMPROBAR_SI_CONECTADO;

    public ProtocoloCliente(String login) {
        this.login = login;
    }
    
    

    public String processInput(String theInput) {
        
        //LO QUE ENVIA EL CLIENTE AL SERVIDOR
        String theOutput = null;

        switch (state) {
            
            case COMPROBAR_SI_CONECTADO:
                
                if("PROTOCOLCRISTOFY1.0#ERROR#INVALID_CREDENTIALS".equals(theInput)){
                    
                    theOutput = "Error";
                    
                }else if("PROTOCOLCRISTOFY1.0#OK#CONNECTED".equals(theInput)){
                    
                    theOutput = "PROTOCOLCRISTOFY1.0#FILES_USERS_SHARED#FAVORITES#" + login;
                    //cambio el estado al siguiente para traer cancines
                    state = LISTO_PARA_TRAER_CANCIONES_BD;
                }
                
                break;    
               
                    
                
            case LISTO_PARA_TRAER_CANCIONES_BD:
                
                   
       
                    //esto es una herramienta que divide un string segun el caracter que le metas
                    StringTokenizer tokens = new StringTokenizer(theInput,"#");
                    while(tokens.hasMoreTokens()){
                        //meto en el array los parametros del protocolo
                        //1º en la posicion 0 se metera PROTOCOLCRISTOFY1.0
                        //2º en la posicion 1 se metera OKSTART
                        //Y EN LAS SIGUIENTES LAS CANCIONES
                        mensajeServidor.add(tokens.nextToken());
                        
                    }
                
                    //compruebo que el principio del protocolo es correcto
                    if( ("PROTOCOLCRISTOFY1.0".equals(mensajeServidor.get(0))) && "OKSTART".equals(mensajeServidor.get(1)) ){
                        
                            //si es asi, devuelvo los mismo que me envió el servidor
                           theOutput = theInput;
                        
                        
                    }else
                            theOutput = "Error";
                
                    
     
                break;
                
           
            default:
                break;
        }
        
        return theOutput;
    }
    
}
