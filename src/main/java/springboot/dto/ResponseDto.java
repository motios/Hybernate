package springboot.dto;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by We on 21.06.2016.
 * response dto
 */
public class ResponseDto implements Serializable {

    //region fields
    private HttpStatus httpStatus;
    private List<StudentDto> students;
    private String reason;
    private long studentsCounter;
    //endregion //fields

    //region Constructor
    public ResponseDto() {

        students = new ArrayList<>();
        setStudentsCounter(0);
    }

    public ResponseDto(HttpStatus httpStatus,  String reason) {
        this();
        this.setHttpStatus(httpStatus);
        this.setReason(reason);

    }

    public ResponseDto(HttpStatus httpStatus, List<StudentDto> students, String reason) {
        this(httpStatus,reason);
        this.setStudents(students);
    }

    public ResponseDto(int studentsCounter, HttpStatus httpStatus, List<StudentDto> students, String reason) {
        this(httpStatus,students,reason);
        this.reason = reason;
    }

    //endregion //Constructor

    //region getters-seeters
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

    public void addStudent(StudentDto studentDto){
        students.add(studentDto);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getStudentsCounter() {
        return studentsCounter;
    }

    public void setStudentsCounter(long studentsCounter) {
        this.studentsCounter = studentsCounter;
    }


    //endregion getters-seeters
}
