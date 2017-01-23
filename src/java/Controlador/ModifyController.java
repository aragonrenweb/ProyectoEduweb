/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Archivo;
import Modelo.Asignatura;
import Modelo.DetallesLessons;
import Modelo.Equipment;
import Modelo.Lessons;
import Modelo.Students;
import Modelo.Subjects;
import Modelo.Subsection;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


/**
 *
 * @author Jesus
 */
public class ModifyController implements Controller{
    
    

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("modify");

        Lessons lesson = new Lessons(request.getServletContext());
        int id_lesson = Integer.parseInt(request.getParameter("TXTid_lessons_modificar"));
        lesson.getOneLesson(id_lesson);
        
        Asignatura levelSeleccionado = new Asignatura(request.getServletContext());
        levelSeleccionado.setId(lesson.getId_level());
        levelSeleccionado.setNombre(lesson.getNombre());
        
        Subjects subjectSeleccionado = new Subjects(request.getServletContext());
        subjectSeleccionado.setId_level(lesson.getId_level());
        subjectSeleccionado.setId(lesson.getId_subject());
        subjectSeleccionado.setNombre(lesson.getNombre_subject());
        
        Subsection subsecSeleccionado = new Subsection(request.getServletContext());
        subsecSeleccionado.setId_subject(subjectSeleccionado.getId());
        subsecSeleccionado.setId_subsection(lesson.getId_subsection());
        subsecSeleccionado.setNombre_sub_section(lesson.getNombre_subsection());
        
        Equipment equip = new Equipment(request.getServletContext());
        
        Students student = new Students(request.getServletContext());
        ArrayList<Students> listStudents = new ArrayList<>();
        listStudents = student.getStudentsFromLesson(id_lesson);
        
        ArrayList<Students> aux = student.getStudents();
        ArrayList<Students> studentsSelec = new ArrayList<>();
        for(int i=0; i<aux.size();i++){
            if(!aux.get(i).removeStudents(listStudents)) studentsSelec.add(aux.get(i));
        }
        
        
        
        mv.addObject("nombrelessons", lesson.getNombre_lessons());
        mv.addObject("levelselection", levelSeleccionado);
        mv.addObject("subjectselection", subjectSeleccionado);
        mv.addObject("SubsectionSelection", subsecSeleccionado);
        mv.addObject("fechaInicio", lesson.getFecha_inicio());
        mv.addObject("fechaFin", lesson.getFecha_fin());
        mv.addObject("levelStudentsSeleccionado", new ArrayList<Students>());
        mv.addObject("listalevelsFor", levelSeleccionado.getLevelsForStudents());
        mv.addObject("listaequipment", equip.getMaterialsFromSubsection(subsecSeleccionado.getId_subsection())); 
        mv.addObject("listasubjects", subjectSeleccionado.getSubjects(lesson.getId_level()));
        mv.addObject("listasubsection", subsecSeleccionado.getSubsections(subjectSeleccionado.getId()));
        mv.addObject("listalevels", levelSeleccionado.getLevels());
        mv.addObject("listamateriales", new Equipment(request.getServletContext()));
        mv.addObject("listaAlumnos", studentsSelec);
        mv.addObject("lessonToModify", id_lesson);
        mv.addObject("alumnosSeleccionados", listStudents);
        mv.addObject("mensaje", "Registros insertados: ");
        return mv;
    }
    
    public ModelAndView levelStudentSelecccionado(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException
    {
        ModelAndView mv = new ModelAndView("modify");
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
        int id_lesson = Integer.parseInt(request.getParameter("TXTIdLesson"));
        String fechainicio = request.getParameter("TXTfechainicio");
        String fechafin = request.getParameter("TXTfechafin");
        String isDisabled = request.getParameter("TXTdisabled");
        
        if(numeroLevelStudent == 0){
            mv.addObject("listaAlumnos", student.getStudents()); 
            asigStudent.setId(0);
            asigStudent.setNombre("ALL STUDENTS");
        }else{
            mv.addObject("listaAlumnos", student.getStudentsForLevel(numeroLevelStudent));
            
        }
        
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
        mv.addObject("lessonToModify", id_lesson);
        mv.addObject("fechaInicio", fechainicio);
        mv.addObject("fechaFin", fechafin);
        mv.addObject("isDisabled", isDisabled);
        
        return mv;
    }
    
    public ModelAndView levelOnClick(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        ModelAndView mv = new ModelAndView("modify");
        String nombreLessons = request.getParameter("TXTnombreLessons");        
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));        
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        String fechainicio = request.getParameter("TXTfechainicio");
        String fechafin = request.getParameter("TXTfechafin");
        int id_lesson = Integer.parseInt(request.getParameter("TXTIdLesson"));
        Students student = new Students(request.getServletContext());
        Asignatura asigLevel = new Asignatura(request.getServletContext());
        Asignatura asigStudent = new Asignatura(request.getServletContext());
        Subjects sub = new Subjects(request.getServletContext());
        
        asigLevel.getOneLevels(idLevel);
        asigStudent.getOneLevels(numeroLevelStudent);
        
        mv.addObject("nombrelessons", nombreLessons);
        mv.addObject("levelStudentsSeleccionado", asigStudent);
        mv.addObject("listalevelsFor", asigStudent.getLevelsForStudents());
        mv.addObject("listalevels", asigLevel.getLevels());
        mv.addObject("listaAlumnos", student.getStudentsForLevel(numeroLevelStudent));
        mv.addObject("levelselection", asigLevel);        
        mv.addObject("listasubjects", sub.getSubjects(idLevel));
        mv.addObject("lessonToModify", id_lesson);
        mv.addObject("fechaInicio", fechainicio);
        mv.addObject("fechaFin", fechafin);
        mv.addObject("isDisabled", "disabled");
        
        return mv;
    }
    
    public ModelAndView subjectOnClick(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        ModelAndView mv = new ModelAndView("modify");
        String nombreLessons = request.getParameter("TXTnombreLessons");        
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));        
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        int id_lesson = Integer.parseInt(request.getParameter("TXTIdLesson"));
        String fechainicio = request.getParameter("TXTfechainicio");
        String fechafin = request.getParameter("TXTfechafin");
        Students student = new Students(request.getServletContext());
        Asignatura asigLevel = new Asignatura(request.getServletContext());
        Asignatura asigStudent = new Asignatura(request.getServletContext());
        Subjects sub = new Subjects(request.getServletContext());
        Subsection subsec = new Subsection(request.getServletContext());
        
        asigLevel.getOneLevels(idLevel);
        asigStudent.getOneLevels(numeroLevelStudent);
        sub.getOneSubject(idSubjects);
        
        mv.addObject("nombrelessons", nombreLessons);
        mv.addObject("levelStudentsSeleccionado", asigStudent);
        mv.addObject("listalevelsFor", asigStudent.getLevelsForStudents());
        mv.addObject("listalevels", asigLevel.getLevels());
        mv.addObject("listaAlumnos", student.getStudentsForLevel(numeroLevelStudent));
        mv.addObject("levelselection", asigLevel);        
        mv.addObject("listasubjects", sub.getSubjects(idLevel));
        mv.addObject("subjectselection", sub);
        mv.addObject("listasubsection", subsec.getSubsections(idSubjects));
        mv.addObject("lessonToModify", id_lesson);
        mv.addObject("fechaInicio", fechainicio);
        mv.addObject("fechaFin", fechafin);
        mv.addObject("isDisabled", "disabled");
        
        return mv;
    }
    
    public ModelAndView subsectionOnClick(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        ModelAndView mv = new ModelAndView("modify");
        String nombreLessons = request.getParameter("TXTnombreLessons");        
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));        
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        int idSubsection = Integer.parseInt(request.getParameter("TXTsubsection"));
        int id_lesson = Integer.parseInt(request.getParameter("TXTIdLesson"));
        String fechainicio = request.getParameter("TXTfechainicio");
        String fechafin = request.getParameter("TXTfechafin");
        Students student = new Students(request.getServletContext());
        Asignatura asigLevel = new Asignatura(request.getServletContext());
        Asignatura asigStudent = new Asignatura(request.getServletContext());
        Subjects sub = new Subjects(request.getServletContext());
        Subsection subsec = new Subsection(request.getServletContext());
        Equipment equip = new Equipment(request.getServletContext());
        
        asigLevel.getOneLevels(idLevel);
        asigStudent.getOneLevels(numeroLevelStudent);
        sub.getOneSubject(idSubjects);
        subsec.getOneSubsections(idSubsection);
        
        mv.addObject("nombrelessons", nombreLessons);
        mv.addObject("levelStudentsSeleccionado", asigStudent);
        mv.addObject("listalevelsFor", asigStudent.getLevelsForStudents());
        mv.addObject("listalevels", asigLevel.getLevels());
        mv.addObject("listaAlumnos", student.getStudentsForLevel(numeroLevelStudent));
        mv.addObject("levelselection", asigLevel);        
        mv.addObject("listasubjects", sub.getSubjects(idLevel));
        mv.addObject("subjectselection", sub);
        mv.addObject("listasubsection", subsec.getSubsections(idSubjects));
        mv.addObject("SubsectionSelection", subsec);
        mv.addObject("listaequipment", equip.getMaterialsFromSubsection(idSubsection));
        mv.addObject("lessonToModify", id_lesson);
        mv.addObject("fechaInicio", fechainicio);
        mv.addObject("fechaFin", fechafin);
        mv.addObject("isDisabled", "");
        
        return mv;
    }
    
    public ModelAndView modifyOnClick(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
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
        //mv.addObject("LessonsCreadas", numLesson);  
        //mv.addObject("MaterialesAsignados", materialesAsignados);
        //mv.addObject("listalecciones",lesson.getLessonFromBBDD());
        //mv.addObject("mensaje", "Registros insertados: " + resultado);
        //mv.addObject("listalevels", this.getLevels());
        //mv.addObject("listalecciones", this.getLessons());
        //mv.addObject("listaAlumnos", this.getStudents());
        //mv.addObject("listalevelsFor", this.getLevelsForStudents());
        
        return mv;
    }
}
