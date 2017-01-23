/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Lessons;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Jesus
 */
public class LessonListController implements Controller{

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("lessonList");
        Lessons lesonList = new Lessons(hsr.getServletContext());
        //ArrayList<Integer> ids = new ArrayList<>();
        //ids = lesonList.getAllId_lessons();
        
        
        return mv.addObject("listalecciones",lesonList.getLessonFromBBDD());
    }
    
    
    
}
