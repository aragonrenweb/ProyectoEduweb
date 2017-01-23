package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Jesús Aragón
 */
public class Lessons {
    
            private int id_lessons;
	    private String nombre_lessons;
            private int id_level;
            private String nombre;
            private int id_subject;
            private String nombre_subject;
            private int id_subsection;
            private String nombre_subsection;
            private ArrayList<Equipment> equipment;
            private ServletContext servlet;
            private Connection cn;
            private CachedRowSet rs;
            private String fecha_inicio;
            private String fecha_fin;

    public Lessons(){
        
    }
    public Lessons(int id_lessons, String nombre_lessons, int id_level, String nombre, int id_subject, String nombre_subject, int id_subsection, String nombre_subsection, ArrayList<Equipment> equipment, String fecha_inicio, String fecha_fin) {
        this.id_lessons = id_lessons;
        this.nombre_lessons = nombre_lessons;
        this.id_level = id_level;
        this.nombre = nombre;
        this.id_subject = id_subject;
        this.nombre_subject = nombre_subject;
        this.id_subsection = id_subsection;
        this.nombre_subsection = nombre_subsection;
        this.equipment = equipment;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }
    public Lessons(ServletContext svc) {
        
        this.id_lessons = 0;
        this.nombre_lessons = "";
        this.id_level = 0;
        this.nombre = "";
        this.id_subject = 0;
        this.nombre_subject = "";
        this.id_subsection = 0;
        this.nombre_subsection = "";
        this.equipment = new ArrayList<>();
        this.servlet = svc;
        this.fecha_inicio = "";
        this.fecha_fin = "";
    }
    public Lessons(String nombre_lessons, int id_level, String nombre, int id_subject, String nombre_subject, int id_subsection, String nombre_subsection, ArrayList<Equipment> equipment, String fecha_inicio, String fecha_fin) {
        
        this.nombre_lessons = nombre_lessons;
        this.id_level = id_level;
        this.nombre = nombre;
        this.id_subject = id_subject;
        this.nombre_subject = nombre_subject;
        this.id_subsection = id_subsection;
        this.nombre_subsection = nombre_subsection;
        this.equipment = equipment;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public int getId_lessons() {
        return id_lessons;
    }

    public void setId_lessons(int id_lessons) {
        this.id_lessons = id_lessons;
    }

    public String getNombre_lessons() {
        return nombre_lessons;
    }

    public void setNombre_lessons(String nombre_lessons) {
        this.nombre_lessons = nombre_lessons;
    }

    public int getId_level() {
        return id_level;
    }

    public void setId_level(int id_level) {
        this.id_level = id_level;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getNombre_subject() {
        return nombre_subject;
    }

    public void setNombre_subject(String nombre_subject) {
        this.nombre_subject = nombre_subject;
    }

    public int getId_subsection() {
        return id_subsection;
    }

    public void setId_subsection(int id_subsection) {
        this.id_subsection = id_subsection;
    }

    public String getNombre_subsection() {
        return nombre_subsection;
    }

    public void setNombre_subsection(String nombre_subsection) {
        this.nombre_subsection = nombre_subsection;
    }

    public  ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment.clear();
        for(Equipment equip : equipment){
            this.equipment.add(equip);
        }
        this.equipment = equipment;
    }
    
    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    private Object getBean(String nombreBean){
        ApplicationContext contexto = 
        WebApplicationContextUtils.getRequiredWebApplicationContext(this.servlet);
        Object bean = contexto.getBean(nombreBean);
        return bean;
    }

    private void conectarOracle() throws SQLException{
        DriverManagerDataSource drv =
                (DriverManagerDataSource)this.getBean("dataSource");
        this.cn = drv.getConnection();
        this.rs = RowSetProvider.newFactory().createCachedRowSet();
    }

    
    public void getOneLesson(int id_lesson) throws SQLException{
        this.conectarOracle();
        try {
            String consulta = "select * from lessons,level,subject,subsection where lessons.id_lessons =" + id_lesson + " and level.id = lessons.id_level and lessons.id_subject = subject.id and lessons.id_subsection = subsection.id_subsection";
            this.rs.setCommand(consulta);
            this.rs.execute(this.cn);
            this.rs.beforeFirst();
            
            while (rs.next()){
                this.id_lessons = rs.getInt("id_lessons");
                this.nombre_lessons = rs.getString("nombre_lessons");
                this.id_level = rs.getInt("id_level");
                this.nombre = rs.getString("nombre");
                this.id_subject = rs.getInt("id_subject");
                this.nombre_subject = rs.getString("nombre_subject");
                this.id_subsection = rs.getInt("id_subsection");
                this.nombre_subsection = rs.getString("nombre_sub_section");
                this.fecha_inicio = rs.getString("fecha_inicio");
                this.fecha_fin = rs.getString("fecha_fin");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo Lessons: " + ex);
        }
        this.rs.close();
        this.cn.close();
    }
    
    public ArrayList<Lessons> getLessonFromBBDD() throws SQLException, ParseException{
        this.conectarOracle();
        ArrayList<Lessons> lessonList = new ArrayList<>();
        String Dtime = "";
        Equipment equip = new Equipment(this.servlet);
        try {
            String consulta = "select * from lessons, level, subject, subsection where lessons.id_level = level.id AND lessons.id_subject = subject.id AND lessons.id_subsection = subsection.id_subsection order by date(fecha_inicio) ASC";
            this.rs.setCommand(consulta);
            this.rs.execute(this.cn);
            this.rs.beforeFirst();

            
            while (rs.next())
            {
                
                Lessons lesson = new Lessons(this.servlet);
                lesson.setId_lessons(rs.getInt("id_lessons"));
                lesson.setNombre_lessons(rs.getString("nombre_lessons"));
                lesson.setId_level(rs.getInt("id_level"));
                lesson.setNombre(rs.getString("nombre"));
                lesson.setId_subject(rs.getInt("id_subject"));
                lesson.setNombre_subject(rs.getString("nombre_subject"));
                lesson.setId_subsection(rs.getInt("id_subsection"));
                lesson.setNombre_subsection(rs.getString("nombre_sub_section"));
                lesson.setFecha_inicio(rs.getString("fecha_inicio"));
                lesson.setEquipment(equip.getMateriales(lesson.getId_lessons()));
                lessonList.add(lesson);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo Lessons: " + ex);
        }
        this.rs.close();
        this.cn.close();
        return lessonList;
    }
    
    public void getultimoLessonsCreado() throws SQLException{
        String consulta = "SELECT id_lessons FROM lessons ORDER BY id_lessons DESC LIMIT 1";
        this.conectarOracle();
        this.rs.setCommand(consulta);
        this.rs.execute(this.cn);
        this.rs.beforeFirst();
        if(rs.next()){
            this.id_lessons = rs.getInt(1);
        }
        this.rs.close();
        this.cn.close();
    }
    
    public ArrayList<Integer> getAllIdLesson() throws SQLException{
        this.conectarOracle();
        ArrayList<Integer> ids = new ArrayList<>();
        String consulta = "SELECT id_lessons FROM lessons";
        this.rs.setCommand(consulta);
        this.rs.execute(this.cn);
        this.rs.beforeFirst();
        if(rs.next()){
            ids.add(rs.getInt("id_lessons"));
        }
        this.rs.close();
        this.cn.close();
        return ids;
    }
    
    
    public void updateLesson(String nombre_lessons, int idLesson, int id_level, int id_subject, int id_subsection, String[] id_equipment, String fecha_inicio, String fecha_fin) throws SQLException, ParseException{
        this.conectarOracle();
        //Lessons lesson = new Lessons(nombre_lessons, id_level,nombre,id_subject,nombre_subject,id_subsection, nombre_subsection, id_equipment, fecha_inicio, fecha_fin);
        //Falta equpment
        String consulta = "update lessons set nombre_lessons='" + nombre_lessons + "', id_level=" + id_level + ", id_subject=" + id_subject + ", id_subsection=" + id_subsection + ", fecha_inicio='" + fecha_inicio + "', fecha_fin='" + fecha_fin + "' where id_lessons=" + idLesson;
        this.rs.setCommand(consulta);
        this.rs.execute(this.cn);
        this.rs.beforeFirst();
        this.rs.close();
        this.cn.close();
    }
    
    public int createLesson(String nombreLessons, int idLevel, int idSubject, int idSubsection, int idUsuario, String[] id_equipment, String fechaInicio, String fechaFin) throws SQLException, ParseException {
        this.conectarOracle();
        int numLesson = -1;
        try {  
            //AHORA CREAMOS LA LECCION/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            String insertLessons = "insert into lessons (nombre_lessons,id_level,id_subject,id_subsection, id_usuario, fecha_inicio, fecha_fin) values (?,?,?,?,?,?,?)";   
            PreparedStatement pst = this.cn.prepareStatement(insertLessons);
            pst.setString(1, nombreLessons);
            pst.setInt(2, idLevel);
            pst.setInt(3, idSubject);
            pst.setInt(4, idSubsection);
            pst.setInt(5, idUsuario);
            pst.setString(6, fechaInicio);
            pst.setString(7, fechaFin);
            numLesson = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        this.rs.close();
        this.cn.close();
        return numLesson;
    }
    
    public void deleteLesson(int idLesson) throws SQLException{
        this.conectarOracle();
        String consulta = "delete from lessons where id_lessons=" + idLesson;
        this.rs.setCommand(consulta);
        this.rs.execute(this.cn);
        this.rs.beforeFirst();
        this.rs.close();
        this.cn.close();
    }
}
