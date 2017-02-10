/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author JuanPablo
 */

import Modelo.ConectarBDCanciones;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class HebraDeCadaCliente extends Thread {
    
    private Socket socket = null;
    private String nombreHebra;
    String ip;
    String usuarioBD; 
    String passBD;
    
    
    
    //meter booleanos de estado
    //esta coenctado, no conectado.... etc
    
    //hay ke meter otro controlador, ke es el ke desde esta clase, llame a la base de datos
    //como lo tenia en el primer cliente cristofy
    
    //metodos a implemenetar CONECTAR(LOGIN, PASS)   transmitirCanciones()
    
    
    
    
    
    //arrayList para manejar las canciones devueltas por la BD
    ArrayList canciones;
    
    //pasando la vista a la hebra puedo imprimir mensajes
    public static javax.swing.JTextArea taVentanaTexto;
    
    //paso el checkbox para el log extendido
    javax.swing.JCheckBox checkBox;

    public HebraDeCadaCliente(Socket socket, String nombre, String ip, String usuarioBD, String passBD, javax.swing.JTextArea ventana, javax.swing.JCheckBox checkBox) {
        super("MultiServerThread");
        this.socket = socket;
        this.nombreHebra = nombre;
        
        taVentanaTexto = ventana;
        this.ip = ip;
        
        this.usuarioBD = usuarioBD;
        this.passBD = passBD;
        
        this.checkBox = checkBox;
    }
    
    
    
    @Override
    public void run() {

        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            ProtocoloServidor protocolo = new ProtocoloServidor(taVentanaTexto, nombreHebra, ip, usuarioBD, passBD, checkBox);
            
            taVentanaTexto.append(nombreHebra + ": ya hay conexión con el cliente... ...\n");
            
            while ((inputLine = in.readLine()) != null) {
               
                
                if(checkBox.isSelected()){
                    taVentanaTexto.append(nombreHebra + ", Cliente dice: " + inputLine + "\n");
                }
                
                //aqui se mete lo que ha enviado el cliente, para procesarlo
                outputLine = protocolo.processInput(inputLine);
                
     
                //lo envio al cliente
                out.println(outputLine);
                
                if(checkBox.isSelected()){
                    taVentanaTexto.append(nombreHebra + ", Servidor envia al cliente: " + outputLine + "\n");
                }
                
                
                //esto no lo utilizo, pero puedo utilizarlo si el cliente manda un mensaje en concreto
                //ke el protocolo mande el output "bye" y cierro
                if (outputLine.equals("Bye"))
                    break;
            }
            
            socket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}