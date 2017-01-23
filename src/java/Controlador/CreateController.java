/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Archivo;
import Modelo.Asignatura;
import Modelo.Equipment;
import Modelo.Lessons;
import Modelo.Students;
import Modelo.Subjects;
import Modelo.Subsection;
import Modelo.Usuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author Jesus
 */
public class CreateController implements Controller{
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("create");
        
        Lessons lesson = new Lessons(request.getServletContext());
        Asignatura asig = new Asignatura(request.getServletContext());
        Students students = new Students(request.getServletContext());
        
        mv.addObject("listalevels", asig.getLevels());
        mv.addObject("listalevelsFor", asig.getLevelsForStudents());
        mv.addObject("listalecciones", lesson.getLessonFromBBDD());
        mv.addObject("listaAlumnos", students.getStudents());
        mv.addObject("mensaje", "Registros insertados: ");
        mv.addObject("isDisabled", "disabled");
        
        return mv;
    }
    
    public ModelAndView levelStudentSelecccionado(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException
    {
        ModelAndView mv = new ModelAndView("create");
        Students student = new Students(request.getServletContext());
        Asignatura asigStudent = new Asignatura(request.getServletContext());
        Asignatura asigLevel = new Asignatura(request.getServletContext());
        Subjects sub = new Subjects(request.getServletContext());
        Subsection subsec = new Subsection(request.getServletContext());
        Equipment equip = new Equipment(request.getServletContext());
        
        String nombreLessons = request.getParameter("TXTnombreLessons");
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        int idSubsection = Integer.parseInt(request.getParameter("TXTsubsection"));
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        String stringStudents = request.getParameter("TXTalumnosSeleccionados");
        String isDisabled = request.getParameter("TXTdisabled");
        ArrayList<Students> listStudents = student.getParseStudents(stringStudents);
        
        if(numeroLevelStudent == 0){
            mv.addObject("listaAlumnos", student.getStudents()); 
            asigStudent.setId(0);
            asigStudent.setNombre("ALL STUDENTS");
        }else{
            mv.addObject("listaAlumnos", student.getStudentsForLevel(numeroLevelStudent).removeAll(listStudents));
            
        }
        
        //listStudents=student.getParseStudents(stringStudents);
        
        if(idLevel == 0) {
            asigLevel.setId(0);
            asigLevel.setNombre("Choose a level");
        }
        else{
            asigLevel.getOneLevels(idLevel);
        }
        asigStudent.getOneLevels(numeroLevelStudent);
        sub.getOneSubject(idSubjects);
        subsec.getOneSubsections(idSubsection);
        ArrayList<Students> aux = student.getStudentsForLevel(numeroLevelStudent);
        ArrayList<Students> studentsSelec = new ArrayList<>();
        for(int i=0; i<aux.size();i++){
            if(!aux.get(i).removeStudents(listStudents)) studentsSelec.add(aux.get(i));
        }
        mv.addObject("nombrelessons", nombreLessons);
        mv.addObject("levelStudentsSeleccionado", asigStudent);
        mv.addObject("listalevelsFor", asigStudent.getLevelsForStudents());
        mv.addObject("listaequipment", equip.getMaterialsFromSubsection(idSubsection));     
        mv.addObject("listasubsection", subsec.getSubsections(idSubjects));
        mv.addObject("listasubjects", sub.getSubjects(idLevel));
        mv.addObject("listalevels", asigLevel.getLevels());
        mv.addObject("levelselection", asigLevel);
        mv.addObject("subjectselection", sub);
        mv.addObject("SubsectionSelection", subsec);
        mv.addObject("alumnosSeleccionados", listStudents);
        mv.addObject("listaAlumnos", studentsSelec);
        mv.addObject("isDisabled", isDisabled);
        
        
        return mv;
    }
    
    public ModelAndView levelOnClick(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        ModelAndView mv = new ModelAndView("levelAjax");
        String nombreLessons = request.getParameter("TXTnombreLessons");        
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));        
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        String stringStudents = request.getParameter("TXTalumnosSeleccionados");
        /*Students student = new Students(request.getServletContext());
        Asignatura asigLevel = new Asignatura(request.getServletContext());
        Asignatura asigStudent = new Asignatura(request.getServletContext());
        */Subjects sub = new Subjects(request.getServletContext());
        /*ArrayList<Students> listStudents = student.getParseStudents(stringStudents);
        
        asigLevel.getOneLevels(idLevel);
        asigStudent.getOneLevels(numeroLevelStudent);
        /*for(int i=0; i<listStudents.size();i++){
            
        }*/
        /*ArrayList<Students> aux = student.getStudentsForLevel(numeroLevelStudent);
        ArrayList<Students> studentsSelec = new ArrayList<>();
        for(int i=0; i<aux.size();i++){
            if(!aux.get(i).removeStudents(listStudents)) studentsSelec.add(aux.get(i));
        } 
        mv.addObject("nombrelessons", nombreLessons);
        mv.addObject("levelStudentsSeleccionado", asigStudent);
        mv.addObject("listalevelsFor", asigStudent.getLevelsForStudents());
        mv.addObject("listalevels", asigLevel.getLevels());
        mv.addObject("listaAlumnos", studentsSelec);
        mv.addObject("levelselection", asigLevel);        
        mv.addObject("listasubjects", sub.getSubjects(idLevel));
        mv.addObject("alumnosSeleccionados", listStudents);
        mv.addObject("isDisabled", "disabled");*/
        
        
        String stringSubject="<option value =\"0\" selected><spring:message code=\"etiq.selectsubject\"/></option>";
        ArrayList<Subjects> listSub = new ArrayList<>();
        listSub = sub.getSubjects(idLevel);
        for (Subjects subject: listSub){
            stringSubject += "<option value=\""+ subject.getId()+"\" >" + subject.getNombre() + "</option>";
        }
        mv.addObject("levelSelection",stringSubject);
        
        return mv;
    }
    
    public ModelAndView subjectOnClick(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        ModelAndView mv = new ModelAndView("create");
        String nombreLessons = request.getParameter("TXTnombreLessons");        
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));        
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        String stringStudents = request.getParameter("TXTalumnosSeleccionados");
        Students student = new Students(request.getServletContext());
        Asignatura asigLevel = new Asignatura(request.getServletContext());
        Asignatura asigStudent = new Asignatura(request.getServletContext());
        Subjects sub = new Subjects(request.getServletContext());
        Subsection subsec = new Subsection(request.getServletContext());
        ArrayList<Students> listStudents = student.getParseStudents(stringStudents);
        
        asigLevel.getOneLevels(idLevel);
        asigStudent.getOneLevels(numeroLevelStudent);
        sub.getOneSubject(idSubjects);
        //listStudents=student.getParseStudents(stringStudents);
        ArrayList<Students> aux = student.getStudentsForLevel(numeroLevelStudent);
        ArrayList<Students> studentsSelec = new ArrayList<>();
        for(int i=0; i<aux.size();i++){
            if(!aux.get(i).removeStudents(listStudents)) studentsSelec.add(aux.get(i));
        }
        mv.addObject("nombrelessons", nombreLessons);
        mv.addObject("levelStudentsSeleccionado", asigStudent);
        mv.addObject("listalevelsFor", asigStudent.getLevelsForStudents());
        mv.addObject("listalevels", asigLevel.getLevels());
        mv.addObject("levelselection", asigLevel);        
        mv.addObject("listasubjects", sub.getSubjects(idLevel));
        mv.addObject("subjectselection", sub);
        mv.addObject("listasubsection", subsec.getSubsections(idSubjects));
        mv.addObject("alumnosSeleccionados", listStudents);
        mv.addObject("listaAlumnos", studentsSelec);
        mv.addObject("isDisabled", "disabled");
        
        return mv;
    }
    
    public ModelAndView subsectionOnClick(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        ModelAndView mv = new ModelAndView("create");
        String nombreLessons = request.getParameter("TXTnombreLessons");        
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));        
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        int idSubsection = Integer.parseInt(request.getParameter("TXTsubsection"));
        String stringStudents = request.getParameter("TXTalumnosSeleccionados");
        Students student = new Students(request.getServletContext());
        Asignatura asigLevel = new Asignatura(request.getServletContext());
        Asignatura asigStudent = new Asignatura(request.getServletContext());
        Subjects sub = new Subjects(request.getServletContext());
        Subsection subsec = new Subsection(request.getServletContext());
        Equipment equip = new Equipment(request.getServletContext());
        ArrayList<Students> listStudents = student.getParseStudents(stringStudents);
        
        asigLevel.getOneLevels(idLevel);
        asigStudent.getOneLevels(numeroLevelStudent);
        sub.getOneSubject(idSubjects);
        subsec.getOneSubsections(idSubsection);
        //listStudents=student.getParseStudents(stringStudents);
        ArrayList<Students> aux = student.getStudentsForLevel(numeroLevelStudent);
        ArrayList<Students> studentsSelec = new ArrayList<>();
        for(int i=0; i<aux.size();i++){
            if(!aux.get(i).removeStudents(listStudents)) studentsSelec.add(aux.get(i));
        }
        mv.addObject("nombrelessons", nombreLessons);
        mv.addObject("levelStudentsSeleccionado", asigStudent);
        mv.addObject("listalevelsFor", asigStudent.getLevelsForStudents());
        mv.addObject("listalevels", asigLevel.getLevels());
        mv.addObject("listaAlumnos", studentsSelec);
        mv.addObject("levelselection", asigLevel);        
        mv.addObject("listasubjects", sub.getSubjects(idLevel));
        mv.addObject("subjectselection", sub);
        mv.addObject("listasubsection", subsec.getSubsections(idSubjects));
        mv.addObject("SubsectionSelection", subsec);
        mv.addObject("listaequipment", equip.getMaterialsFromSubsection(idSubsection));
        mv.addObject("alumnosSeleccionados", listStudents);
        mv.addObject("isDisabled", "");
        
        return mv;
    }
    
    public ModelAndView createOnClick(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        ModelAndView mv = new ModelAndView("lessonList");
        int numLesson = 0;
        int materialesAsignados = 0;
        int idLesson = 0;
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
        int idUsuario = user.getId_usuario();
        Equipment equip = new Equipment(request.getServletContext());
        Lessons lesson = new Lessons(request.getServletContext());
        Archivo file = new Archivo(request.getServletContext());
        Students student = new Students(request.getServletContext());
        ArrayList<Students> listStudents = student.getParseStudents(stringStudents);
        
        numLesson = lesson.createLesson(nombreLessons, idLevel, idSubjects, idSubsection, idUsuario, idEquipament, fechainicio, fechafin);
        lesson.getultimoLessonsCreado();
        idLesson = lesson.getId_lessons();
        file.insertArchivo(idLesson, nombreArchivo);
        materialesAsignados = equip.insertEquipment(idLesson, idEquipament);
        student.insertStudents(idLesson, listStudents);
        
        
        mv.addObject("LessonsCreadas", numLesson);  
        mv.addObject("MaterialesAsignados", materialesAsignados);
        mv.addObject("listalecciones",lesson.getLessonFromBBDD());
        //mv.addObject("mensaje", "Registros insertados: " + resultado);
        //mv.addObject("listalevels", this.getLevels());
        //mv.addObject("listalecciones", this.getLessons());
        //mv.addObject("listaAlumnos", this.getStudents());
        //mv.addObject("listalevelsFor", this.getLevelsForStudents());
        
        return mv;
    }
}
