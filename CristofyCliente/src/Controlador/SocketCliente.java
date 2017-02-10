/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author JuanPablo
 */
public class SocketCliente {
    
    public Socket socket;
    public PrintWriter out;
    public BufferedReader in;
   
    boolean resultado;
    
    //creo una instancia de la clase Protocolo
    ProtocoloCliente pro;

    
    public SocketCliente(String ip, int puerto) {
        
        try{
            socket = new Socket(ip, puerto);
            //para imprimir datos del servidor
            out = new PrintWriter(socket.getOutputStream(), true);
            //para leer lo ke envia el servidor
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + ip);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                ip);
            System.exit(1);
        }
        
        
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }
    
    
    
    
    public boolean comprobarProtocoloLogin(String login, String pass) throws IOException{
        
        //string con lo que me devuelve el server
            String fromServer;
            String protocolo;
            
            String respuestaProtocolo;
            String comparar;
        
            //creo el protocolo principal, que consta del usuario y el password
            protocolo = "PROTOCOLCRISTOFY1.0#CONNECT#" + login + "#" + pass;
            
            //lo mando al servidor
            out.println(protocolo);
    
            //el servidor envia respuesta
            fromServer = in.readLine();
            
            
            //de la isntancia creada de la clase ProtocoloCliente
            pro = new ProtocoloCliente(login);
            //y proceso el string enviado por el server
            respuestaProtocolo = pro.processInput(fromServer);
            
            //creo string con login incluido
            comparar = "PROTOCOLCRISTOFY1.0#FILES_USERS_SHARED#FAVORITES#"+login;
            
            if(comparar.equals(respuestaProtocolo)){
                resultado = true;
            }else{
                resultado = false;
            }
            
            
        
        return resultado;
    }
    
    public String traerCancionesDelServidor(String login) throws IOException{
        
        
        String fromServer;
        String protocolo;
        
        
        
        //aki la respuesta del protocolo va a ser el string ke ha recibido
        String respuestaProtocolo;
        
        //creo el protocolo para traerme canciones
        protocolo ="PROTOCOLCRISTOFY1.0#FILES_USERS_SHARED#FAVORITES#" + login;
            
        //lo mando al servidor
        out.println(protocolo);
    
        //el servidor envia respuesta
        fromServer = in.readLine();
        
        //ya hay instancia creada de la clase Protocolo
        //y proceso el string enviado por el server
        //solo proceso si es valido o no, esto me devuelve "ok" o "Error"
        respuestaProtocolo = pro.processInput(fromServer);
        
        if("Error".equals(respuestaProtocolo)){
            fromServer = "Error";
        }
        
        //devulevo lo que me ha enviado el server, si está ok el mensaje con las canciones
        return fromServer;
    }
    
}
