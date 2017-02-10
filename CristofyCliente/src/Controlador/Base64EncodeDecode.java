/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author JuanPablo
 */
public class Base64EncodeDecode {
    
    public Base64EncodeDecode(){
    
    }
    
    public String codificar(String mensaje){
        
        Base64.Encoder encoder = Base64.getEncoder();
       
        String mensajeEncriptado = encoder.encodeToString(mensaje.getBytes(StandardCharsets.UTF_8) );
        
        return mensajeEncriptado;
    }
    
    
    public String decodificar(String mensajeEncriptado){
        
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(mensajeEncriptado);
        
        String mensajeDesencriptado = new String(decodedByteArray);    
        
        return mensajeDesencriptado;
    }
    
    
}
