/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ProtocoloServidor.taVentanaTexto;
import Modelo.ConectarBDLogin;

/**
 *
 * @author JuanPablo
 */
public class ComprobarLoginBD {
    
    String user;
    String pass;
    
    String ip;
    
    String usuarioBD;
    String passBD;
    
    public boolean comprobar(String user, String pass, String ip, String usuarioBD, String passBD ){
        
        ConectarBDLogin conLogin = new ConectarBDLogin();
        Boolean correctLogin = conLogin.conectarYcomprobar(user, pass, ip, usuarioBD, passBD);
                    
   
        return correctLogin;
    }
    
}
