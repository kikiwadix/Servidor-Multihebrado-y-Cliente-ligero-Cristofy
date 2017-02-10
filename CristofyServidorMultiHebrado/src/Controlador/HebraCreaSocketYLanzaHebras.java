/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



/**
 *
 * @author JuanPablo
 */
public class HebraCreaSocketYLanzaHebras extends Thread{
    
    public static boolean listening;
    int portNumber;
    String ip;
    String usuarioBD;
    String passBD;
    
    
    javax.swing.JCheckBox checkBox;
    
    //array de hebras(clientes)
    public static ArrayList<HebraDeCadaCliente> arrayHebras = new ArrayList<>();
    
    //pasando la vista a la hebra puedo imprimir mensajes
    public static javax.swing.JTextArea taVentanaTexto;

    
    public HebraCreaSocketYLanzaHebras(int puerto, String ip, String usuarioBD, String passBD, javax.swing.JTextArea ventana, javax.swing.JCheckBox checkBox) {
        this.portNumber = puerto;
        taVentanaTexto = ventana;
        this.ip = ip;
        this.usuarioBD = usuarioBD;
        this.passBD = passBD;
        this.listening = true;
        this.checkBox = checkBox;
        
    }
    
    
    public void pararHebra(){
        
        listening = false;
    }
    
    
    
    
    //metodo en Exclusion Mutua que se encarga de crear la hebra, meterla en el array de clientes, y lanzarla
    public synchronized void gestionArrayHebrasCliente(Socket clientSocket){
        
        //para ponerle nombre a la hebra
        String nombreHebra = "Hebra" + String.valueOf(arrayHebras.size()+1);
                    
        arrayHebras.add(new HebraDeCadaCliente(clientSocket, nombreHebra, ip, usuarioBD, passBD, taVentanaTexto, checkBox));
                    
        //lanzo la ultima hebra del arraylist
        arrayHebras.get(arrayHebras.size()-1/*el tamaño del array menos uno*/).start();
        
    }
    
    
    
    
    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            
            
            while (listening) {
                
                
                Socket clientSocket = serverSocket.accept();
                if(checkBox.isSelected()){
                    taVentanaTexto.append("\tHebra principal: Puerto abierto, cliente conectándose... ...\n");
                }
                
                
                //este método es Syncronized, para que se ejecute en exclusion mutua
                //este crea una hebra, la mete en el arrayList, y la lanza
                gestionArrayHebrasCliente(clientSocket);
                   
                
	    }
            
            //y aki ke cierre el socket, no en la vista
            serverSocket.close();
            taVentanaTexto.append("Hebra principal: Parando hebra para desconectar... funciona esto o keeeee?????\n");
            
            
	} catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
    
}
