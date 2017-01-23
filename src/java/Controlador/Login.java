/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import BasesDeDatos.PostgreSQL.UsuarioBD;
import Modelo.Usuario;
import org.springframework.web.servlet.ModelAndView; 
import org.springframework.web.servlet.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author snaji
 */
public class Login implements Controller{
          @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            ModelAndView mv =  null;
            String forumUser = hsr.getParameter("txtuser");
            String forumPassword = hsr.getParameter("txtpassword");  
            HttpSession sesion = hsr.getSession();
            Usuario usuario = null;
            
            usuario = UsuarioBD.getUsuario(forumUser, forumPassword);
            
            if(usuario != null){
                mv =  new ModelAndView("messages");               
                sesion.setAttribute("usuarioregistrado", usuario);
            }
            else{
                mv = new ModelAndView("index");
                mv.addObject("errorusuario","user invalid");
                
            }
            return mv;
    }
    
    
    
}
