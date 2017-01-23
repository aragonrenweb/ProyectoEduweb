/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author MacAragon
 */
public class SubirArchivosForm {
    
    private int id_archivos;
    private String nombre_archivo;
    private MultipartFile archivos;

    public SubirArchivosForm(int id_archivos, String nombre_archivo, MultipartFile archivos) {
        this.id_archivos = id_archivos;
        this.nombre_archivo = nombre_archivo;
        this.archivos = archivos;
    }

    public SubirArchivosForm() {
        this.id_archivos = 0;
        this.nombre_archivo = "C:/Users/MacAragon/Desktop/shahad.txt";
        this.archivos = archivos;
    }
    
    
    public int getId_archivos() {
        return id_archivos;
    }

    public void setId_archivos(int id_archivos) {
        this.id_archivos = id_archivos;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public MultipartFile getArchivos() {
        return archivos;
    }

    public void setArchivos(MultipartFile archivos) {
        this.archivos = archivos;
    }

}
