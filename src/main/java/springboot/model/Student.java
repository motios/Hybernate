package springboot.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(
                name = Student.QUERY_GET_ALL,
                query = "select s from Student s"
        ),
        @NamedQuery(
                name = Student.QUERY_STUDENT_FILTER_BY_STUDENT_ID,
                query = "select s from Student s where "+Student.FIELD_STUDENT_ID + "=:" +Student.PARAM_STUDENT_ID
        )
})
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
    public static final String QUERY_GET_ALL = "student.getAll";
    public static final String QUERY_STUDENT_FILTER_BY_STUDENT_ID = "student.FilterByStudentId";
    public static final String FIELD_STUDENT_ID = "field_student_id";
    public static final String PARAM_STUDENT_ID = "param_student_id";
    public static final int maxStudents=1000;

    //region fields
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

    //endregion //fields

    //region Constructor
    public Student() {
    }

    public Student(String name, String gender, long studentId, double average) {
        this.name = name;
        this.gender = gender;
        this.studentId = studentId;
        this.average = average;
    }

    public Student(String name, String gender, long studentId, double average, long id) {
        this(name, gender, studentId, average);
        this.id=id;
    }

    //endregion //Constructor

    //region getter-setter
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

    public void setId(long id) {
        this.id = id;
    }

    //endregion //getter-setter

    //region equals
    //comparison by studentId only(teudat zehut)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return studentId == student.studentId;

    }

    @Override
    public int hashCode() {
        return (int) (studentId ^ (studentId >>> 32));
    }

    //endregion equals
}
