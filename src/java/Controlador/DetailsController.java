/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Archivo;
import Modelo.DetallesLessons;
import Modelo.Lessons;
import Modelo.Students;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Jesus
 */
public class DetailsController implements Controller{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("lessonList");
        Lessons lesson = new Lessons(request.getServletContext());
        Archivo file = new Archivo(request.getServletContext());
        Students student = new Students(request.getServletContext());
        ArrayList<Students> students = new ArrayList<>();
        int idLessons = Integer.parseInt(request.getParameter("TXTid_lessons_detalles"));
        
        lesson.getOneLesson(idLessons);
        file.getFile(idLessons);
        students = student.getStudentsFromLesson(idLessons);
        
        
        mv.addObject("detallesleccion", lesson);
        mv.addObject("detallesAlumnos", students);
        mv.addObject("detallesarchivo", file);
        mv.addObject("listalecciones", lesson.getLessonFromBBDD());
        //mv.addObject("listamateriales", this.getMateriales());
        
        return mv;
    }
    
}
