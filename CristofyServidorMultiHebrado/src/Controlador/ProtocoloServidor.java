/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConectarBDCanciones;
import Modelo.ConectarBDLogin;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ProtocoloServidor {
    private static final int COMPROBAR_NOMBRE_USUARIO_Y_PASSWORD = 0;
    private static final int LISTO_PARA_TRAER_CANCIONES_BD = 1;
    private static final int CONECTADO = 2;
    
    //este arrayList es para meter el mensaje del cliente partido en trozos
    ArrayList<String> mensajeCliente = new ArrayList<>();
    
    String user;
    String pass;
    
    String ip;
    
    String usuarioBD;
    String passBD;
    
    //pasando la vista a la hebra puedo imprimir mensajes
    public static javax.swing.JTextArea taVentanaTexto;
    
    //paso el checkbox para el log extendido
    javax.swing.JCheckBox checkBox;
    
    String nombreHebra;
    
    
   

    private int state = COMPROBAR_NOMBRE_USUARIO_Y_PASSWORD;
    
    

    public ProtocoloServidor(javax.swing.JTextArea ventana, String nombre, String ip, String usuarioBD, String passBD, javax.swing.JCheckBox checkBox ) {
        
        taVentanaTexto = ventana;
        nombreHebra = nombre;
        this.ip = ip;
        this.usuarioBD = usuarioBD;
        this.passBD = passBD;
        this.checkBox = checkBox;
    }
    
    

    public String processInput(String theInput) {
        
        //LO QUE ENVIA EL SERVIDOR AL CLIENTE
        String theOutput = null;

        switch (state) {
            
            case COMPROBAR_NOMBRE_USUARIO_Y_PASSWORD:
                
                if(theInput.isEmpty()){
                    taVentanaTexto.append( nombreHebra + "El cliente no ha enviado nada... ...\n");
                }else{
                    
                    //esto es una herramienta que divide un string segun el caracter que le metas
                    StringTokenizer tokens = new StringTokenizer(theInput,"#");
                    while(tokens.hasMoreTokens()){
                        //meto en el array los 4 parametros del protocolo
                        //1º en la posicion 0 se metera PROTOCOLCRISTOFY1.0
                        //2º en la posicion 1 se metera CONNECT
                        //3º en la posicion 2 se metera LOGIN
                        //4º en la posicion 3 se metera PASS
                        mensajeCliente.add(tokens.nextToken());
                        
                    }
                    
                    
                    //COMPRUEBO EL PROTOCOLO, QUE EMPIEZE CORRECTAMENTE
                    if(("PROTOCOLCRISTOFY1.0".equals(mensajeCliente.get(0))) && ("CONNECT".equals(mensajeCliente.get(1)))){
                        
                        //y meto en las variables los valores adecuados
                        user = mensajeCliente.get(2);
                        pass = mensajeCliente.get(3);


                        //llamo al otro controlador, que se encarga de coger los parametros
                        //y llamar a la clase ConectarBDLogin del modelo
                        ComprobarLoginBD comprobar = new ComprobarLoginBD();
                        Boolean correctLogin = comprobar.comprobar(user, pass, ip, usuarioBD, passBD);

                        //si el login es correcto, emvia el protocolo de ok,
                        if(correctLogin){
                            state = LISTO_PARA_TRAER_CANCIONES_BD;
                            theOutput = "PROTOCOLCRISTOFY1.0#OK#CONNECTED";
                            taVentanaTexto.append(nombreHebra + "" + user + ": Cliente Conectado y preparado para enviar lista canciones... ...\n");
                        }else{
                            theOutput = "PROTOCOLCRISTOFY1.0#ERROR#INVALID_CREDENTIALS";
                            taVentanaTexto.append(nombreHebra + ": Error del cliente, usuario o contraseña invalidas... ...\n");
                        }
                    
                        
                        
                    }else{
                        theOutput = "PROTOCOLCRISTOFY1.0#ERROR#INVALID_CREDENTIALS";
                    }
                    
                    
                    
                    
                    
                }
                
                break;
                
            case LISTO_PARA_TRAER_CANCIONES_BD:
                
                
                    //compruebo que el cliente me ha enviado el protocolo correcto
                if(theInput.equals("PROTOCOLCRISTOFY1.0#FILES_USERS_SHARED#FAVORITES#"+user)){
                    
                    
                    //llamo al metodo que me trae las canciones de la base de datos
                    TraerCancionesBD tc = new TraerCancionesBD();
                                  
                    //llamo al metodo que las trae, las transforma en string, y las meto en TheOutput
                    //para que se las envie al cliente
                    theOutput = tc.traerCanciones(ip, user, usuarioBD, passBD);
                    
                     if(checkBox.isSelected()){
                        taVentanaTexto.append(nombreHebra + ": canciones cogidas de la bd, transformadas y listas para enviar al cliente\n");
                    }
                    
                    
                    
                    
                }else{
                    theOutput = "ERROR";
                }
                
                   
                    
     
                break;
                
           
            default:
                break;
        }
        
        return theOutput;
    }
}