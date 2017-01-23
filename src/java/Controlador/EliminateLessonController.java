/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Archivo;
import Modelo.Equipment;
import Modelo.Lessons;
import Modelo.Students;
import Modelo.Usuario;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Jesus
 */
public class EliminateLessonController implements Controller{
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        ModelAndView mv = new ModelAndView("lessonList");
        int idLesson = Integer.parseInt(request.getParameter("TXTid_lessons_eliminar"));
        Equipment equip = new Equipment(request.getServletContext());
        Lessons lesson = new Lessons(request.getServletContext());
        Archivo file = new Archivo(request.getServletContext());
        Students student = new Students(request.getServletContext());
        
        file.deleteArchivo(idLesson);
        equip.deleteEquipment(idLesson);
        student.deleteStudent(idLesson);
        lesson.deleteLesson(idLesson);
        
        mv.addObject("listalecciones",lesson.getLessonFromBBDD());
        //mv.addObject("mensaje", "Registros insertados: " + resultado);
        //mv.addObject("listalevels", this.getLevels());
        //mv.addObject("listalecciones", this.getLessons());
        //mv.addObject("listaAlumnos", this.getStudents());
        //mv.addObject("listalevelsFor", this.getLevelsForStudents());
        
        return mv;
    }
}
