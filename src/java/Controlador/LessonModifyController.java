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
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Jesus
 */
public class LessonModifyController implements Controller{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("lessonList");
        int idLesson = Integer.parseInt(request.getParameter("TXTIdLesson"));
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuarioregistrado");
        String nombreLessons = request.getParameter("TXTnombreLessons");
        String stringStudents = request.getParameter("TXTalumnosSeleccionados");
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        int idSubsection = Integer.parseInt(request.getParameter("TXTsubsection"));
        String[] idEquipament = request.getParameterValues("TXTequipment");
        String fechainicio = request.getParameter("TXTfechainicio");
        String fechafin = request.getParameter("TXTfechafin");
        String nombreArchivo = request.getParameter("TXTnombreArchivo");
        
        Equipment equip = new Equipment(request.getServletContext());
        Lessons lesson = new Lessons(request.getServletContext());
        Archivo file = new Archivo(request.getServletContext());
        Students student = new Students(request.getServletContext());
        ArrayList<Students> listStudents = student.getParseStudents(stringStudents);
        
        lesson.updateLesson(nombreLessons, idLesson, idLevel, idSubjects, idSubsection, idEquipament, fechainicio, fechafin);
        equip.updateEquipment(idLesson, idEquipament);
        student.updateStudent(idLesson, listStudents);
        //file.insertArchivo(idLesson, nombreArchivo);
        
        mv.addObject("listalecciones",lesson.getLessonFromBBDD());

        return mv;
    }
    
}
