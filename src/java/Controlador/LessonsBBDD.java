package Controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import Modelo.Usuario;
import Modelo.Asignatura;
import Modelo.DetallesLessons;
import Modelo.Equipment;
import Modelo.Lessons;
import Modelo.LessonsDatos;
import Modelo.LessonsEquipment;
import Modelo.Subjects;
import Modelo.Subsection;
import Modelo.Students;
import Modelo.LessonsFunc;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class LessonsBBDD extends MultiActionController
{

    Connection cn;
    
    
    private Object getBean(String nombrebean, ServletContext servlet)
    {
        ApplicationContext contexto = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet);
        Object beanobject = contexto.getBean(nombrebean);
        return beanobject;
    }
    
    LessonsDatos modelo = new LessonsDatos();
    
      public ModelAndView levels(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();

        
        mv.addObject("listalevels", this.getLevels());
        
        mv.addObject("listalevelsFor", this.getLevelsForStudents());
        
        mv.addObject("listalecciones", this.getLessons());
//        mv.addObject("listamateriales", this.getMateriales());
        mv.addObject("listaAlumnos", this.getStudents());
        

        mv.addObject("mensaje", "Registros insertados: ");
        return mv;
      
}
      
        public ModelAndView comboSubject(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        String nombreLessons = request.getParameter("TXTnombreLessons");
        
        int numero = Integer.parseInt(request.getParameter("TXTlevel"));
        
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();
        
        ArrayList<Subjects> listasubjs = new ArrayList<Subjects>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subject where id_level =" + numero);
            
            while (rs.next())
            {
                Subjects subjs = new Subjects();
                subjs.setId(rs.getInt("id"));
                subjs.setId_level(rs.getInt("id_level"));
                subjs.setNombre(rs.getString("nombre_subject"));
                listasubjs.add(subjs);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        //LessonsFunc less = new LessonsFunc();
        //listasubjs = less.takeSubject(this.cn,numero);

//        --------------SELECT ANTERIORES--------------------------------------------        
        
        ArrayList<Asignatura> LevelSelecionado = new ArrayList<Asignatura>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM level where id =" + numero);
            
            while (rs1.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs1.getInt("id"));
                levels.setNombre(rs1.getString("nombre"));
                LevelSelecionado.add(levels);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        
        if(numeroLevelStudent == 0){
        ArrayList<Students> listastudentlevel = new ArrayList<Students>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs2 = st.executeQuery("SELECT * FROM students");
            
             while (rs2.next())
            {
                Students stud = new Students();
                stud.setId_students(rs2.getInt("id_students"));
                stud.setNombre_students(rs2.getString("nombre_students"));
                stud.setFoto(rs2.getString("foto"));
                stud.setId_students(rs2.getInt("level_id"));
                listastudentlevel.add(stud);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
            mv.addObject("levelStudentsSeleccionado", listastudentlevel); 
        }
        ArrayList<Students> listastudentlevel = new ArrayList<Students>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs2 = st.executeQuery("SELECT * FROM students where level_id =" + numeroLevelStudent);
            
             while (rs2.next())
            {
                Students stud = new Students();
                stud.setId_students(rs2.getInt("id_students"));
                stud.setNombre_students(rs2.getString("nombre_students"));
                stud.setFoto(rs2.getString("foto"));
                stud.setId_students(rs2.getInt("level_id"));
                listastudentlevel.add(stud);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        
        mv.addObject("nombrelessons", nombreLessons);
        
        mv.addObject("levelselection", LevelSelecionado);
        
        mv.addObject("levelStudentsSeleccionado", listastudentlevel);
        mv.addObject("listalevelsFor", this.getLevelsForStudents());
        
        mv.addObject("listasubjects", listasubjs);
        mv.addObject("listalevels", this.getLevels());
        mv.addObject("listalecciones", this.getLessons());
//        mv.addObject("listamateriales", this.getMateriales());
        mv.addObject("listaAlumnos", this.getStudents());
        
        return mv;
      
}
 
        public ModelAndView comboSubsection(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        String nombreLessons = request.getParameter("TXTnombreLessons");
        
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));
        
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));

        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();
        ArrayList<Subsection> listasubsections = new ArrayList<Subsection>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subsection where id_subject =" + idSubjects);
            
            while (rs.next())
            {
                Subsection subsections = new Subsection();
                subsections.setId_subsection(rs.getInt("id_subsection"));
                subsections.setId_subject(rs.getInt("id_subject"));
                subsections.setNombre_sub_section(rs.getString("nombre_sub_section"));
                listasubsections.add(subsections);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsection: " + ex);
        }
        
//        --------------SELECT ANTERIORES--------------------------------------------
        
        ArrayList<Subjects> listasubjs = new ArrayList<Subjects>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subject where id_level =" + idLevel);
            
            while (rs.next())
            {
                Subjects subjs = new Subjects();
                subjs.setId(rs.getInt("id"));
                subjs.setId_level(rs.getInt("id_level"));
                subjs.setNombre(rs.getString("nombre_subject"));
                listasubjs.add(subjs);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
      

        ArrayList<Subjects> SubjectSelecionado = new ArrayList<Subjects>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM subject where id =" + idSubjects);
            
            while (rs1.next())
            {
                Subjects subjects = new Subjects();
                subjects.setId(rs1.getInt("id"));
                subjects.setNombre(rs1.getString("nombre_subject"));
                SubjectSelecionado.add(subjects);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsections: " + ex);
        }
        
        ArrayList<Asignatura> LevelSelecionado = new ArrayList<Asignatura>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM level where id =" + idLevel);
            
            while (rs1.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs1.getInt("id"));
                levels.setNombre(rs1.getString("nombre"));
                LevelSelecionado.add(levels);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        
        
        if(numeroLevelStudent == 0){
        ArrayList<Students> listastudentlevel = new ArrayList<Students>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs2 = st.executeQuery("SELECT * FROM students");
            
             while (rs2.next())
            {
                Students stud = new Students();
                stud.setId_students(rs2.getInt("id_students"));
                stud.setNombre_students(rs2.getString("nombre_students"));
                stud.setFoto(rs2.getString("foto"));
                stud.setId_students(rs2.getInt("level_id"));
                listastudentlevel.add(stud);
            }
             mv.addObject("levelStudentsSeleccionado", listastudentlevel); 
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
            
        }
        ArrayList<Students> listastudentlevel = new ArrayList<Students>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs2 = st.executeQuery("SELECT * FROM students where level_id =" + numeroLevelStudent);
            
             while (rs2.next())
            {
                Students stud = new Students();
                stud.setId_students(rs2.getInt("id_students"));
                stud.setNombre_students(rs2.getString("nombre_students"));
                stud.setFoto(rs2.getString("foto"));
                stud.setId_students(rs2.getInt("level_id"));
                listastudentlevel.add(stud);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        
        
        mv.addObject("nombrelessons", nombreLessons);
        
        mv.addObject("levelselection", LevelSelecionado);
        mv.addObject("subjectselection", SubjectSelecionado);
        
        
        mv.addObject("levelStudentsSeleccionado", listastudentlevel);   
        mv.addObject("listalevelsFor", this.getLevelsForStudents());
        
        mv.addObject("listasubsection", listasubsections);
        mv.addObject("listasubjects", listasubjs);
        mv.addObject("listalevels", this.getLevels());
        mv.addObject("listalecciones", this.getLessons());
//        mv.addObject("listamateriales", this.getMateriales());

        mv.addObject("listaAlumnos", this.getStudents());
        
        return mv;
      
}      
      
        public ModelAndView comboEquipment(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        String nombreLessons = request.getParameter("TXTnombreLessons");
        
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        int idSubsection = Integer.parseInt(request.getParameter("TXTsubsection"));
        
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        
        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();
        
        ArrayList<Equipment> listaequipment = new ArrayList<Equipment>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM activity_equipment where id_subsection =" + idSubsection);
            
            while (rs.next())
            {
                Equipment equipments = new Equipment();
                equipments.setId_activity_equipment(rs.getInt("id_activity_equipment"));
                equipments.setId_subsection(rs.getInt("id_subsection"));
                equipments.setNombre_activity_equipment(rs.getString("nombre_activity_equipment"));
                listaequipment.add(equipments);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo equipment: " + ex);
        }
        
        ArrayList<Subsection> listasubsections = new ArrayList<Subsection>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subsection where id_subject =" + idSubjects);
            
            while (rs.next())
            {
                Subsection subsections = new Subsection();
                subsections.setId_subsection(rs.getInt("id_subsection"));
                subsections.setId_subject(rs.getInt("id_subject"));
                subsections.setNombre_sub_section(rs.getString("nombre_sub_section"));
                listasubsections.add(subsections);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsection: " + ex);
        }
        
        ArrayList<Subjects> listasubjs = new ArrayList<Subjects>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subject where id_level =" + idLevel);
            
            while (rs.next())
            {
                Subjects subjs = new Subjects();
                subjs.setId(rs.getInt("id"));
                subjs.setId_level(rs.getInt("id_level"));
                subjs.setNombre(rs.getString("nombre_subject"));
                listasubjs.add(subjs);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
      
        
        
//        --------------SELECT ANTERIORES--------------------------------------------
        
        ArrayList<Subsection> SubsectionSelecionado = new ArrayList<Subsection>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM subsection where id_subsection =" + idSubsection);
            
            while (rs1.next())
            {
                Subsection subsections = new Subsection();
                subsections.setId_subsection(rs1.getInt("id_subsection"));
                subsections.setId_subject(rs1.getInt("id_subject"));
                subsections.setNombre_sub_section(rs1.getString("nombre_sub_section"));
                SubsectionSelecionado.add(subsections);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsections: " + ex);
        }
        
        ArrayList<Subjects> SubjectSelecionado = new ArrayList<Subjects>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM subject where id =" + idSubjects);
            
            while (rs1.next())
            {
                Subjects subjects = new Subjects();
                subjects.setId(rs1.getInt("id"));
                subjects.setNombre(rs1.getString("nombre_subject"));
                SubjectSelecionado.add(subjects);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsections: " + ex);
        }
        
        ArrayList<Asignatura> LevelSelecionado = new ArrayList<Asignatura>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM level where id =" + idLevel);
            
            while (rs1.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs1.getInt("id"));
                levels.setNombre(rs1.getString("nombre"));
                LevelSelecionado.add(levels);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        
        if(numeroLevelStudent == 0){
        ArrayList<Students> listastudentlevel = new ArrayList<Students>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs2 = st.executeQuery("SELECT * FROM students");
            
             while (rs2.next())
            {
                Students stud = new Students();
                stud.setId_students(rs2.getInt("id_students"));
                stud.setNombre_students(rs2.getString("nombre_students"));
                stud.setFoto(rs2.getString("foto"));
                stud.setId_students(rs2.getInt("level_id"));
                listastudentlevel.add(stud);
            }
            mv.addObject("levelStudentsSeleccionado", listastudentlevel);
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
            
        }
        ArrayList<Students> listastudentlevel = new ArrayList<Students>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs2 = st.executeQuery("SELECT * FROM students where level_id =" + numeroLevelStudent);
            
             while (rs2.next())
            {
                Students stud = new Students();
                stud.setId_students(rs2.getInt("id_students"));
                stud.setNombre_students(rs2.getString("nombre_students"));
                stud.setFoto(rs2.getString("foto"));
                stud.setId_students(rs2.getInt("level_id"));
                listastudentlevel.add(stud);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        mv.addObject("nombrelessons", nombreLessons);
        
        mv.addObject("levelselection", LevelSelecionado);
        mv.addObject("subjectselection", SubjectSelecionado);
        mv.addObject("SubsectionSelection", SubsectionSelecionado);
        
        mv.addObject("levelStudentsSeleccionado", listastudentlevel);
        mv.addObject("listalevelsFor", this.getLevelsForStudents());
        
        mv.addObject("listaequipment", listaequipment);     
        mv.addObject("listasubsection", listasubsections);
        mv.addObject("listasubjects", listasubjs);
        mv.addObject("listalevels", this.getLevels());
        mv.addObject("listalecciones", this.getLessons());
//        mv.addObject("listamateriales", this.getMateriales());

        mv.addObject("listaAlumnos", this.getStudents());
        
        return mv;
      
}    
    
    //------------------------FILTRAMOS ESTUDIANTES POR LECCION EN LA BASE DE DATOS ----------------------
    
        
         public ModelAndView levelStudentSelecccionado(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        String nombreLessons = request.getParameter("TXTnombreLessons");
         
        
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        int idSubsection = Integer.parseInt(request.getParameter("TXTsubsection"));
        int numeroLevelStudent = Integer.parseInt(request.getParameter("TXTlevelStudents"));
        String students = request.getParameter("TXTalumnosSeleccionados");
        ArrayList<Integer> idStudents = new ArrayList<Integer>();
        idStudents = StudentsParse(students);
        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();
        
        
        //if(!idStudents.isEmpty()){
            if(numeroLevelStudent == 0){
                ArrayList<Students> listastudentlevel = new ArrayList<Students>();
                try {

                    Statement st = this.cn.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT * FROM students");

                    while (rs2.next())
                    {
                        if(!idStudents.contains(rs2.getInt("id_students"))){
                            Students stud = new Students();
                            stud.setId_students(rs2.getInt("id_students"));
                            stud.setNombre_students(rs2.getString("nombre_students"));
                            stud.setFoto(rs2.getString("foto"));
                            stud.setId_students(rs2.getInt("level_id"));
                            listastudentlevel.add(stud);
                        }
                    }

                } catch (SQLException ex) {
                    System.out.println("Error leyendo subjects: " + ex);
                }

                mv.addObject("listaAlumnos", listastudentlevel); //levelStudentsSeleccionado
            }else{
                ArrayList<Students> listastudentlevel = new ArrayList<Students>();
                try {

                    Statement st = this.cn.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT * FROM students where level_id =" + numeroLevelStudent);

                    while (rs2.next())
                    {
                        if(!idStudents.contains(rs2.getInt("id_students"))){
                            Students stud = new Students();
                            stud.setId_students(rs2.getInt("id_students"));
                            stud.setNombre_students(rs2.getString("nombre_students"));
                            stud.setFoto(rs2.getString("foto"));
                            stud.setId_students(rs2.getInt("level_id"));
                            listastudentlevel.add(stud);
                        }
                    }

                } catch (SQLException ex) {
                    System.out.println("Error leyendo subjects: " + ex);
                }
                mv.addObject("listaAlumnos", listastudentlevel);
            }
        /*} else{
            if(numeroLevelStudent == 0){
                ArrayList<Students> listastudentlevel = new ArrayList<Students>();
                try {

                    Statement st = this.cn.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT * FROM students");

                    while (rs2.next())
                    {
                        Students stud = new Students();
                        stud.setId_students(rs2.getInt("id_students"));
                        stud.setNombre_students(rs2.getString("nombre_students"));
                        stud.setFoto(rs2.getString("foto"));
                        stud.setId_students(rs2.getInt("level_id"));
                        listastudentlevel.add(stud);
                    }

                } catch (SQLException ex) {
                    System.out.println("Error leyendo subjects: " + ex);
                }

                mv.addObject("listaAlumnos", listastudentlevel); //levelStudentsSeleccionado
            }else{
                ArrayList<Students> listastudentlevel = new ArrayList<Students>();
                try {

                    Statement st = this.cn.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT * FROM students where level_id =" + numeroLevelStudent);

                    while (rs2.next())
                    {
                        if(!idStudents.contains(rs2.getInt("id_students"))){
                            Students stud = new Students();
                            stud.setId_students(rs2.getInt("id_students"));
                            stud.setNombre_students(rs2.getString("nombre_students"));
                            stud.setFoto(rs2.getString("foto"));
                            stud.setId_students(rs2.getInt("level_id"));
                            listastudentlevel.add(stud);
                        }
                    }

                } catch (SQLException ex) {
                    System.out.println("Error leyendo subjects: " + ex);
                }
                mv.addObject("listaAlumnos", listastudentlevel);
            }
        }
        
        //Cojo los alumnos seleccionados
        
        ArrayList<Students> listastudentselect = new ArrayList<Students>();
        try {
            
            for(int i = 0; i<idStudents.size();i++){
                Statement st = this.cn.createStatement();
                ResultSet rs2 = st.executeQuery("SELECT * FROM students where level_id =" + idStudents.get(i));

                while (rs2.next())
                {
                    if(!idStudents.contains(rs2.getInt("id_students"))){
                        Students stud = new Students();
                        stud.setId_students(rs2.getInt("id_students"));
                        stud.setNombre_students(rs2.getString("nombre_students"));
                        stud.setFoto(rs2.getString("foto"));
                        stud.setId_students(rs2.getInt("level_id"));
                        listastudentselect.add(stud);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        
        mv.addObject("alumnosSelect", listastudentselect);
        */
        ArrayList<Asignatura> studentlevelSeleccionado = new ArrayList<Asignatura>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM level where id =" + numeroLevelStudent);
            
            while (rs1.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs1.getInt("id"));
                levels.setNombre(rs1.getString("nombre"));
                studentlevelSeleccionado.add(levels);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
      
        
        
        
        
//        --------------VALORES DE LOS SELECT DE LOS COMBOS-------------------------------------------    
                
       ArrayList<Equipment> listaequipment = new ArrayList<Equipment>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM activity_equipment where id_subsection =" + idSubsection);
            
            while (rs.next())
            {
                Equipment equipments = new Equipment();
                equipments.setId_activity_equipment(rs.getInt("id_activity_equipment"));
                equipments.setId_subsection(rs.getInt("id_subsection"));
                equipments.setNombre_activity_equipment(rs.getString("nombre_activity_equipment"));
                listaequipment.add(equipments);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo equipment: " + ex);
        }
        
        ArrayList<Subsection> listasubsections = new ArrayList<Subsection>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subsection where id_subject =" + idSubjects);
            
            while (rs.next())
            {
                Subsection subsections = new Subsection();
                subsections.setId_subsection(rs.getInt("id_subsection"));
                subsections.setId_subject(rs.getInt("id_subject"));
                subsections.setNombre_sub_section(rs.getString("nombre_sub_section"));
                listasubsections.add(subsections);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsection: " + ex);
        }
        
        ArrayList<Subjects> listasubjs = new ArrayList<Subjects>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subject where id_level =" + idLevel);
            
            while (rs.next())
            {
                Subjects subjs = new Subjects();
                subjs.setId(rs.getInt("id"));
                subjs.setId_level(rs.getInt("id_level"));
                subjs.setNombre(rs.getString("nombre_subject"));
                listasubjs.add(subjs);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
      
        
        
//        --------------SELECT ANTERIORES--------------------------------------------
        
        ArrayList<Subsection> SubsectionSelecionado = new ArrayList<Subsection>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM subsection where id_subsection =" + idSubsection);
            
            while (rs1.next())
            {
                Subsection subsections = new Subsection();
                subsections.setId_subsection(rs1.getInt("id_subsection"));
                subsections.setId_subject(rs1.getInt("id_subject"));
                subsections.setNombre_sub_section(rs1.getString("nombre_sub_section"));
                SubsectionSelecionado.add(subsections);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsections: " + ex);
        }
        
        ArrayList<Subjects> SubjectSelecionado = new ArrayList<Subjects>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM subject where id =" + idSubjects);
            
            while (rs1.next())
            {
                Subjects subjects = new Subjects();
                subjects.setId(rs1.getInt("id"));
                subjects.setNombre(rs1.getString("nombre_subject"));
                SubjectSelecionado.add(subjects);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsections: " + ex);
        }
        
        ArrayList<Asignatura> LevelSelecionado = new ArrayList<Asignatura>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM level where id =" + idLevel);
            
            while (rs1.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs1.getInt("id"));
                levels.setNombre(rs1.getString("nombre"));
                LevelSelecionado.add(levels);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        
        mv.addObject("nombrelessons", nombreLessons);
        
        mv.addObject("levelStudentsSeleccionado", studentlevelSeleccionado);
        mv.addObject("listalevelsFor", this.getLevelsForStudents());
        
        mv.addObject("listaequipment", listaequipment);     
        mv.addObject("listasubsection", listasubsections);
        mv.addObject("listasubjects", listasubjs);
        mv.addObject("listalevels", this.getLevels());
        mv.addObject("listalecciones", this.getLessons());
//        mv.addObject("listamateriales", this.getMateriales());
        
        

        return mv;
      
}
             
      //------------------------INSERTAMOS LA LECCION EN LA BASE DE DATOS ----------------------
      
      
    
        public ModelAndView crear(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        
        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();
        
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuarioregistrado");
        
        String nombreLessons = request.getParameter("TXTnombreLessons");
        String students = request.getParameter("TXTalumnosSeleccionados");
        int idLevel = Integer.parseInt(request.getParameter("TXTlevel"));
        int idSubjects = Integer.parseInt(request.getParameter("TXTsubjects"));
        int idSubsection = Integer.parseInt(request.getParameter("TXTsubsection"));
        String[] idEquipament = request.getParameterValues("TXTequipment");
        
        String fechainicio = request.getParameter("TXTfechainicio");
        String fechafin = request.getParameter("TXTfechafin");
        
        String nombreArchivo = request.getParameter("TXTnombreArchivo");
        
//        DateFormat df2 = new SimpleDateFormat("YYYY-MM-DD HH:MI:SS");
//        fechaformateada = df2.parse(METHOD_GET)
        
        int idUsuario = user.getId_usuario();
       
//        StringBuffer listaequipamiento = new StringBuffer();
//        
//        for (String idEquipament1 : idEquipament) {
//            listaequipamiento = listaequipamiento.append(idEquipament1).append(",");
//        }


// Comprobamos que el usuario ha rellenado todos los campos

//if(!checkear())
        
        int resultado = 0;
        
        ArrayList<Lessons> lista = null;
        
        try {
           
        //AHORA CREAMOS LA LECCION/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String insertLessons = "insert into lessons (nombre_lessons,id_level,id_subject,id_subsection, id_usuario, fecha_inicio, fecha_fin) values (?,?,?,?,?,?,?)";   
        
        PreparedStatement pst = this.cn.prepareStatement(insertLessons);
        

        pst.setString(1, nombreLessons);
        pst.setInt(2, idLevel);
        pst.setInt(3, idSubjects);
        pst.setInt(4, idSubsection);
        pst.setInt(5, idUsuario);
        pst.setString(6, fechainicio);
        pst.setString(7, fechafin);
       
        int numLessons = pst.executeUpdate();
        
        //AHORA AÑADIMOS LOS ESTUDIANTES
        int idLessons = getultimoLessonsCreado();
        //Primero hacemos el parse de lo que nos ha enviado la vista
        ArrayList<Integer> idStudents = new ArrayList<Integer>();
        idStudents = StudentsParse(students);
        //ArrayList<Students> listaAlumnos = new ArrayList<Students>();
        
        //Cogemos el id de la lesson
        String getLesson = "SELECT * FROM lessons_students WHERE id_lessons_students =(SELECT max(id_lessons_students) FROM lessons_students)";
        Statement st = this.cn.createStatement();
        ResultSet rs = st.executeQuery(getLesson);
        int id_student_lesson = 0;
        while (rs.next()){
            id_student_lesson = rs.getInt(1) + 1;
        }
        
        //INSERTAR EN LA TABLA DE LESSONS-STUDENTS
        
        
        
        for(int i=0; i < idStudents.size(); i++){
            String insertStudent = "INSERT INTO lessons_students (id_lessons_students, id_lessons, id_students) values (?,?,?)";
            PreparedStatement pstStu = this.cn.prepareStatement(insertStudent);
            pstStu.setInt(1, id_student_lesson + i);
            pstStu.setInt(2, idLessons);
            pstStu.setInt(3, idStudents.get(i));
            pstStu.executeUpdate();
        
         //   pstStu
        }
        
        
        
        
        
        //AHORA AÑADIMOS LOS MATERIALES/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int materialesAsignados = 0;
        if(idEquipament != null){
            for(int x=0; x < idEquipament.length; x++){
                String insertMaterialLessons = "insert into lessons_equipment (id_lessons, id_equipment) values (?,?)";
        
                PreparedStatement pst1 = this.cn.prepareStatement(insertMaterialLessons);
        
                pst1.setInt(1, idLessons);
                pst1.setInt(2, Integer.parseInt(idEquipament[x]));
            
             pst1.executeUpdate();
            }
            
            materialesAsignados = idEquipament.length;
        }
        
        //AHORA AÑADIMOS LOS ARCHIVOS ADJUNTOS/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        if(nombreArchivo != null){
            String insertArchivosLessons = "insert into archivos (id_lessons, nombre_archivo) values (?,?)";
        
            PreparedStatement pst1 = this.cn.prepareStatement(insertArchivosLessons);
        
            pst1.setInt(1, idLessons);
            pst1.setString(2, nombreArchivo);
            
            pst1.executeUpdate();
        }
        

        

        
        //mv.addObject("alumnosSeleccionados", listaAlumnos);
        mv.addObject("LessonsCreadas", numLessons);  
        mv.addObject("MaterialesAsignados", materialesAsignados);                    
        } catch (SQLException ex) {
            String error = ex.toString();
            mv.addObject("error", error);
        }
        
       
        
        
        mv.addObject("listalessons", lista);
        mv.addObject("mensaje", "Registros insertados: " + resultado);
        

        mv.addObject("listalevels", this.getLevels());
        mv.addObject("listalecciones", this.getLessons());
//        mv.addObject("listamateriales", this.getMateriales());
        mv.addObject("listaAlumnos", this.getStudents());
        
        mv.addObject("listalevelsFor", this.getLevelsForStudents());

        
        return mv;
      
}
       
        
    public ArrayList<Integer> StudentsParse(String students){
        //String students = " Esto es=un archivo data-id='12567867' que aburrido=estoy  data-id='4578578' jeje";
        String buscar = "data-id";
        int pos = students.indexOf(buscar);
        ArrayList<Integer> idStudents = new ArrayList<Integer>();
        while (pos > -1){
            students = students.substring(pos + buscar.length()+2 ,students.length());
            idStudents.add(Integer.parseInt(students.substring(0 ,students.indexOf(" ")-1)));
            
            
            pos = students.indexOf(buscar);
        }
        return idStudents;
    }
        public ModelAndView eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        
        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();
        
        int idLessons = Integer.parseInt(request.getParameter("TXTid_lessons_eliminar"));
        
        try{
        String eliminarArchivos = "delete from archivos where id_lessons = ?";
        PreparedStatement pst = this.cn.prepareStatement(eliminarArchivos);

        pst.setInt(1, idLessons);
        pst.executeUpdate();
        } catch (SQLException ex){
            String error = ex.toString();
            mv.addObject("error", error);
        }  
        
        try{
        String eliminarArchivos = "delete from lessons_students where id_lessons = ?";
        PreparedStatement pst = this.cn.prepareStatement(eliminarArchivos);

        pst.setInt(1, idLessons);
        pst.executeUpdate();
        } catch (SQLException ex){
            String error = ex.toString();
            mv.addObject("error", error);
        } 
        
        
        try {
        String eliminarLessons = "delete from lessons where id_lessons = ?";    
        PreparedStatement pst = this.cn.prepareStatement(eliminarLessons);

        pst.setInt(1, idLessons);
        
        
        int numLessons = pst.executeUpdate();
        
        
        mv.addObject("Lessons Eliminados", numLessons);  
                            
        } catch (SQLException ex) {
            String error = ex.toString();
            mv.addObject("error", error);
        }
        
        ArrayList<Lessons> lista = null;
        ArrayList<LessonsEquipment> listamateriales = null;
        try {
        String eliminarLessons = "delete from lessons_equipment where id_lessons = ?";    
        PreparedStatement pst = this.cn.prepareStatement(eliminarLessons);

        pst.setInt(1, idLessons);
        
        
        int numMateriales = pst.executeUpdate();
        
        
        mv.addObject("Materiales Eliminados", numMateriales);  
        
        
        } catch (SQLException ex) {
            String error = ex.toString();
            mv.addObject("error", error);
        }
        
        mv.addObject("listalessons", lista);
        mv.addObject("listalessons", listamateriales);
        
        mv.addObject("listalevels", this.getLevels());
        mv.addObject("listalecciones", this.getLessons());
//        mv.addObject("listamateriales", this.getMateriales());
        mv.addObject("listaAlumnos", this.getStudents());
        mv.addObject("listalevelsFor", this.getLevelsForStudents());

        
        return mv;
      
}    
       
      //------------------------RELLENAMOS LA VENTANA DE DETALLES----------------------   
        public ModelAndView detalles(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        
        
        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();
        
        int idLessons = Integer.parseInt(request.getParameter("TXTid_lessons_detalles"));
        
        //OBTENGO LOS DETALLES DE LA LECCION
        
        ArrayList<DetallesLessons> detallesLecciones = new ArrayList<DetallesLessons>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("select * from lessons, level, subject, subsection where lessons.id_lessons =" + idLessons + "and lessons.id_level = level.id AND lessons.id_subject = subject.id and lessons.id_subsection = subsection.id_subsection");
            
            while (rs.next())
            {
                DetallesLessons detalles = new DetallesLessons();
                detalles.setId_lessons(rs.getInt("id_lessons"));
                detalles.setNombre_lessons(rs.getString("nombre_lessons"));
                detalles.setId_level(rs.getInt("id_level"));
                detalles.setNombre(rs.getString("nombre"));
                detalles.setId_subject(rs.getInt("id_subject"));
                detalles.setNombre_subject(rs.getString("nombre_subject"));
                detalles.setId_subsection(rs.getInt("id_subsection"));
                detalles.setNombre_sub_section(rs.getString("nombre_sub_section"));
                detalles.setFecha_inicio(rs.getString("fecha_inicio"));
                detalles.setFecha_fin(rs.getString("fecha_fin"));
                detalles.setNombre_archivo("");
                detallesLecciones.add(detalles);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo equipment: " + ex);
        }
        
        //OBTENGO LOS ARCHIVOS SI ES QUE EXISTEN
        
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("select * from archivos where id_lessons =" + idLessons);
            int contador = 0;            
            while (rs.next()) {
                DetallesLessons detalles = detallesLecciones.get(contador);
                detalles.setNombre_archivo(rs.getString("nombre_archivo"));
                detallesLecciones.set(contador, detalles);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo equipment: " + ex);
        }
        
        //OBTENGO LOS ALUMNOS DE LA LECCION PARA LOS DETALLES
        
        ArrayList<DetallesLessons> alumnosLecciones = new ArrayList<DetallesLessons>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("select * from lessons, lessons_students, students where lessons.id_lessons =" + idLessons +  "and lessons_students.id_lessons =" + idLessons + "and lessons_students.id_students = students.id_students");
            
            while (rs.next())
            {
                DetallesLessons detallesAlumnos = new DetallesLessons();
                detallesAlumnos.setId_lessons(rs.getInt("id_lessons"));
                detallesAlumnos.setNombre_lessons(rs.getString("nombre_lessons"));
                detallesAlumnos.setId_students(rs.getInt("id_students"));
                detallesAlumnos.setNombre_students(rs.getString("nombre_students"));
                alumnosLecciones.add(detallesAlumnos);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo equipment: " + ex);
        }
        
        
        mv.addObject("detalles", detallesLecciones);
        mv.addObject("detallesAlumnos", alumnosLecciones);
        mv.addObject("listalevels", this.getLevels());
        mv.addObject("listalecciones", this.getLessons());
        //mv.addObject("listamateriales", this.getMateriales());
        mv.addObject("listalevelsFor", this.getLevelsForStudents());
        mv.addObject("listaAlumnos", this.getStudents());
        
        return mv;
      
}    
      
    private ArrayList<Lessons> getLessons()
{
        
        try {
            ArrayList<Lessons> listalessons = new ArrayList<Lessons>();
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("select * from lessons, level, subject, subsection where lessons.id_level = level.id AND lessons.id_subject = subject.id AND lessons.id_subsection = subsection.id_subsection");
            
            while (rs.next())
            {
                Lessons lesson = new Lessons();
                lesson.setId_lessons(rs.getInt("id_lessons"));
                lesson.setNombre_lessons(rs.getString("nombre_lessons"));
                lesson.setId_level(rs.getInt("id_level"));
                lesson.setNombre(rs.getString("nombre"));
                lesson.setId_subject(rs.getInt("id_subject"));
                lesson.setNombre_subject(rs.getString("nombre_subject"));
                lesson.setId_subsection(rs.getInt("id_subsection"));
                lesson.setNombre_subsection(rs.getString("nombre_sub_section"));
//              lesson.setId_equipment(rs.getString("nombre_activity_equipment"));
                listalessons.add(lesson);
            }
            return listalessons;
        } catch (SQLException ex) {
            System.out.println("Error leyendo Lessons: " + ex);
        }
        
             
        return null;
      
}

    
    
/*        public ArrayList<Lessons> getMateriales()
{
        
        try {
            ArrayList<Lessons> listalessons = new ArrayList<Lessons>();
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("select distinct lessons.*, activity_equipment.nombre_activity_equipment from lessons inner join lessons_equipment on lessons.id_lessons = lessons_equipment.id_lessons inner join activity_equipment on lessons_equipment.id_equipment = activity_equipment.id_activity_equipment order by lessons.nombre_lessons");
            
            while (rs.next())
            {
                Lessons lesson = new Lessons();
                lesson.setId_lessons(rs.getInt("id_lessons"));
                lesson.setId_equipment(rs.getString("nombre_activity_equipment"));
                listalessons.add(lesson);
            }
            return listalessons;
        } catch (SQLException ex) {
            System.out.println("Error leyendo Lessons: " + ex);
        }
        
             
        return null;
      
}

        public ArrayList<Lessons> getNombreLevel()
{
        
        try {
            ArrayList<Lessons> listalessons = new ArrayList<Lessons>();
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("select distinct lessons.*, activity_equipment.nombre_activity_equipment from lessons inner join lessons_equipment on lessons.id_lessons = lessons_equipment.id_lessons inner join activity_equipment on lessons_equipment.id_equipment = activity_equipment.id_activity_equipment order by lessons.nombre_lessons");
            
            while (rs.next())
            {
                Lessons lesson = new Lessons();
                lesson.setId_lessons(rs.getInt("id_lessons"));
                lesson.setId_equipment(rs.getString("nombre_activity_equipment"));
                listalessons.add(lesson);
            }
            return listalessons;
        } catch (SQLException ex) {
            System.out.println("Error leyendo Lessons: " + ex);
        }
        
             
        return null;
      
}*/

    private ArrayList<Asignatura> getLevels()
    {
        try {
            ArrayList<Asignatura> listaLevels = new ArrayList<Asignatura>();
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM level");
            while (rs.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs.getInt("id"));
                levels.setNombre(rs.getString("nombre"));
                listaLevels.add(levels);
            }
            return listaLevels;
        } catch (SQLException ex) {
            System.out.println("Error leyendo Levels: " + ex);
        }
        return null;
    }
    
      private ArrayList<Asignatura> getLevelsForStudents()
    {
        
        
        try {
            ArrayList<Asignatura> listaLevels = new ArrayList<Asignatura>();
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM level");
            while (rs.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs.getInt("id"));
                levels.setNombre(rs.getString("nombre"));
                listaLevels.add(levels);
            }
            return listaLevels;
        } catch (SQLException ex) {
            System.out.println("Error leyendo Levels: " + ex);
        }
        return null;
    }
    
      
    public int getultimoLessonsCreado() throws SQLException
    {
        int ultimoLessonsCreado = 0;
        
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT id_lessons FROM lessons ORDER BY id_lessons DESC LIMIT 1");
            
            if(rs1.next())
            {
                ultimoLessonsCreado = rs1.getInt(1);
                return ultimoLessonsCreado;
            }
            return 0;
    }
    
    
//OBTENER ESTUDIANTES         
       private ArrayList<Students> getStudents()
    {
        try {
            ArrayList<Students> listaAlumnos = new ArrayList<Students>();
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            while (rs.next())
            {
                Students alumnos = new Students();
                alumnos.setId_students(rs.getInt("id_students"));
                alumnos.setNombre_students(rs.getString("nombre_students"));
                alumnos.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                alumnos.setFoto(rs.getString("foto"));
                listaAlumnos.add(alumnos);
            }
            return listaAlumnos;
        } catch (SQLException ex) {
            System.out.println("Error leyendo Alumnos: " + ex);
        }
        return null;
    }
         
    /*public ModelAndView modificar(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        ModelAndView mv = new ModelAndView("Modificar");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();
        
        HttpSession sesion = request.getSession();
        
        int idLessons = Integer.parseInt(request.getParameter("TXTid_lessons_modificar"));
        
        DetallesLessons detalles = new DetallesLessons();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("select * from lessons, level, subject, subsection where lessons.id_lessons =" + idLessons + "and lessons.id_level = level.id AND lessons.id_subject = subject.id and lessons.id_subsection = subsection.id_subsection");
            
            while (rs.next())
            {
                
                detalles.setId_lessons(rs.getInt("id_lessons"));
                detalles.setNombre_lessons(rs.getString("nombre_lessons"));
                detalles.setId_level(rs.getInt("id_level"));
                detalles.setNombre(rs.getString("nombre"));
                detalles.setId_subject(rs.getInt("id_subject"));
                detalles.setNombre_subject(rs.getString("nombre_subject"));
                detalles.setId_subsection(rs.getInt("id_subsection"));
                detalles.setNombre_sub_section(rs.getString("nombre_sub_section"));
                detalles.setFecha_inicio(rs.getString("fecha_inicio"));
                detalles.setFecha_fin(rs.getString("fecha_fin"));
                detalles.setNombre_archivo("");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo equipment: " + ex);
        }
        
        ArrayList<Asignatura> LevelSelecionado = new ArrayList<Asignatura>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM level where id =" + detalles.getId_level());
            
            while (rs1.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs1.getInt("id"));
                levels.setNombre(rs1.getString("nombre"));
                LevelSelecionado.add(levels);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        
        ArrayList<Subjects> SubjectSelecionado = new ArrayList<Subjects>();
        try {
            
            Statement st = this.cn.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM subject where id =" + detalles.getId_subject());
            
            while (rs1.next())
            {
                Subjects subjects = new Subjects();
                subjects.setId(rs1.getInt("id"));
                subjects.setNombre(rs1.getString("nombre_subject"));
                SubjectSelecionado.add(subjects);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsections: " + ex);
        }
        
        mv.addObject("nombrelessons", detalles.getNombre_lessons());
        mv.addObject("levelselection", LevelSelecionado);
        mv.addObject("subjectselection", SubjectSelecionado);
        //mv.addObject("levelStudentsSeleccionado", listastudentlevel); 
        mv.addObject("listalevels", this.getLevels());
        mv.addObject("listalevelsFor", this.getLevelsForStudents());
        mv.addObject("listalecciones", this.getLessons());
        mv.addObject("listamateriales", this.getMateriales());
        mv.addObject("listaAlumnos", this.getStudents());
        
        return mv;
    }*/
    
//    
//
//    private ArrayList<Subjects> obtenerSubjects()
//    {
//        
//        try {
//            ArrayList<Subjects> listasubjs = new ArrayList<Subjects>();
//            Statement st = this.cn.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM subject where id_level = 1");
//            while (rs.next())
//            {
//                Subjects subjs = new Subjects();
//                subjs.setId(rs.getInt("id"));
//                subjs.setId_level(rs.getInt("id_level"));
//                subjs.setNombre(rs.getString("nombre_subject"));
//                listasubjs.add(subjs);
//            }
//            return listasubjs;
//        } catch (SQLException ex) {
//            System.out.println("Error leyendo subjects: " + ex);
//        }
//        return null;
//    }
       
}

