package springboot.dto;

import java.io.Serializable;

/**
 * Created by We on 20.06.2016.
 */
public class StudentDto implements Serializable{

    private long id;
    private String name;
    private String gender;
    private long studentId;
    private double average;

    public StudentDto(String name, String gender, long studentId, double average) {
        this.id = 0;
        this.name = name;
        this.gender = gender;
        this.studentId = studentId;
        this.average = average;
    }
    public StudentDto(long id, String name, String gender, long studentId, double average) {
        this(name,gender,studentId,average);
        this.id = id;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }


}
