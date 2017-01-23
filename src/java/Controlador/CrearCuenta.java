/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import BasesDeDatos.PostgreSQL.DataBrowser;
import BasesDeDatos.PostgreSQL.PaisBD;
import BasesDeDatos.PostgreSQL.PersonaBD;
import Modelo.Cadena;
import Modelo.Ciudad;
import Modelo.Pais;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import Modelo.Persona;
import java.util.ArrayList;
/**
 *
 * @author snaji
 */
public class CrearCuenta implements Controller{

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
      //  DataBrowser bd = new DataBrowser();
        ModelAndView mv =  null;
     
   
        String forumName = hsr.getParameter("txtname");
        String forumSurnames = hsr.getParameter("txtsurnames");
        String forumUser = hsr.getParameter("txtuser");
        String forumPassword = hsr.getParameter("txtpassword");
        String forumBirthday = hsr.getParameter("txtbirthday");
        String forumGender = hsr.getParameter("txtgender");
        String forumEmail = hsr.getParameter("txtemail");
        String forumCountry = hsr.getParameter("txtcountry");
     
      
           
        
        if((forumName != null) || (forumSurnames != null) || (forumUser != null) || (forumPassword != null) || (forumBirthday != null) || (forumGender != null) || (forumEmail != null) || (forumCountry != null)){
            
            System.out.println(forumName);
            System.out.println(forumSurnames);
            System.out.println(forumUser);
            System.out.println(forumPassword);
            System.out.println(forumBirthday);
            System.out.println(forumGender);
            System.out.println(forumEmail);
            System.out.println(forumCountry);
            
            
            Persona persona = new Persona();
            
            persona.setNombre(forumName);
            persona.setApellidos(forumSurnames);
            persona.setUsuario(forumUser);
            persona.setPswd(forumPassword);
            persona.setFecha_nacimiento(forumBirthday);
            persona.setGenero(forumGender);
            persona.setEmail(forumEmail);
            
            Pais pais = PaisBD.getPaisByName(forumCountry);
            persona.setPais(pais);
            
          
            
            
            
            return mv; 
        }
              mv = new ModelAndView("formCrearCuenta");
//DataBrowser bd = new DataBrowser();

         ArrayList<Pais> p =PaisBD.getPaises();
	/*			  for (int i = 0; i < p.size(); i++) {
					  System.out.println(p.get(i).getNombre_english());	
                                  }*/
	//(int id, String nombre, String apellidos, String usuario, String pswd, String dia_nacimiento, String mes_naciemiento, String anio_nacimeinto, String email, String genero,Pais pais) 			}
      Pais pais = PaisBD.getPaisByName("Spain");
        Persona persona = new Persona(0, "Pepe", "Perez Carrasco", "pepe1234", "111111", "21", "06", "2016", "pepe@y.com", Cadena.getGenero("hombre"), pais);    
        System.out.println(persona.getNombre());
        System.out.println(persona.getApellidos());
        System.out.println(persona.getUsuario());
        System.out.println(persona.getPswd());
        System.out.println(persona.getFecha_nacimiento());
        System.out.println(persona.getEmail());
        System.out.println(persona.getGenero());
        System.out.println(persona.getPais().getNombre_english());
        if(PersonaBD.insertarPersona(persona) == 1){
            Persona aux = PersonaBD.getPersona("pepe1234", Cadena.Encriptar("111111"));
        System.out.println(aux.getNombre());
        System.out.println(aux.getApellidos());
        System.out.println(aux.getUsuario());
        System.out.println(aux.getPswd());
        System.out.println(aux.getFecha_nacimiento());
        System.out.println(aux.getEmail());
        System.out.println(aux.getGenero());
        System.out.println(aux.getPais().getNombre_english());    
      
          
        aux.setNombre("Juan");
        PersonaBD.modificarPersona(aux);
        System.out.println(aux.getNombre());
        }
        
        
        
      /* Persona aux = PersonaBD.getPersona("pepe1234", Cadena.Encriptar("111111"));
       if(PersonaBD.eliminarPersona(aux) == 1){
           System.out.println("Eliminado");
       }*/
        
     // ArrayList<Ciudad> c =   Cadena.getCiudades("AF");
    /*     for(int i = 0; i < c.size(); i++){
             System.out.println(c.get(i).getNombre_english());
         }*/
         /*   
            CiudadBD.insertarCiudad("AF");
            ArrayList<Ciudad> c = CiudadBD.getCiudades();
            for (int i = 0; i < c.size(); i++) {
                System.err.println(c.get(i).getNombre_english());
        }*/
             mv.addObject("paises", p);
 // DataBrowser.eliminarTablas();
 return mv;
    }
    
}
