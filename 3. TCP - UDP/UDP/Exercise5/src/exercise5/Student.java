/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercise5;

import java.io.Serializable;

/**
 *
 * @author asier
 */
public class Student implements Serializable {

    /**
     * @param args the command line arguments
     */
    private int id;
    private String name;
    private int age;
    private float distanceToCollege;

    public Student(String name, int age, float distanceToCollege) {
        this.name = name;
        this.age = age;
        this.distanceToCollege = distanceToCollege;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getDistanceToCollege() {
        return distanceToCollege;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", distanceToCollege=" + distanceToCollege +
                '}';
    }
    
}
