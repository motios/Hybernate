package springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
        @SequenceGenerator(name="pk_sequence",sequenceName="student_id_seq", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	private long id;


	@Column
	private String name;
        
        @Column
	private String gender;

        @Column 
        private long studentId;
                
        @Column  
        private double average;
        
        
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Student(String name, String gender, long studentId, double average) {
        this.name = name;
        this.gender = gender;
        this.studentId = studentId;
        this.average = average;
    }

    
   

    public Student() {
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
    
    public String getGender() {
            return gender;
        }

    public void setGender(String gender) {
        this.gender = gender;
    }
        
        
}
