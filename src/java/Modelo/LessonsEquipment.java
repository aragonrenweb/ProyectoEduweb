/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jesús Aragón
 */
public class LessonsEquipment {
    
    private int id_lessons;
    private int id_equipment;

    public LessonsEquipment(int id_lessons, int id_equipment) {
        this.id_lessons = id_lessons;
        this.id_equipment = id_equipment;
    }
    
    public LessonsEquipment(int id_lessons) {
        this.id_lessons = id_lessons;
        this.id_equipment = 0;
    }

    public int getId_lessons() {
        return id_lessons;
    }

    public void setId_lessons(int id_lessons) {
        this.id_lessons = id_lessons;
    }

    public int getId_equipment() {
        return id_equipment;
    }

    public void setId_equipment(int id_equipment) {
        this.id_equipment = id_equipment;
    }
    
    
}
