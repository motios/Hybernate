package springboot.service;

import org.springframework.http.HttpStatus;
import springboot.dao.StudentRepository;
import org.springframework.stereotype.Service;
import springboot.dto.ResponseDto;
import springboot.dto.StudentDto;
import springboot.enums.StudentGender;
import springboot.init.InitJson;
import springboot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.util.StringHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by We on 20.06.2016.
 */
@Service
public class StudentService  {

    @Autowired
    private StudentRepository studentRepository;
    /*EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("StudentsService");
    EntityManager entityManager= entityManagerFactory.createEntityManager();
    DefaultStudentRepository defaultStudentRepository= new DefaultStudentRepository(entityManager);*/

    //return List StudentDto
    public ResponseDto getAllStudents() {
        Iterable<Student> students=  studentRepository.findAll();
        List<StudentDto> studentsDto= new ArrayList<StudentDto>();
        students.forEach(student->{ studentsDto.add(studentToDto(student)); });
        HttpStatus httpStatus = students!=null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseDto responseDto=new ResponseDto(httpStatus,studentsDto,httpStatus.getReasonPhrase());
        return responseDto;
    }

    //return studentDto
    public ResponseDto getStudentById(long studentId) {
        StudentDto studentDto= studentToDto(studentRepository.getStudentByStudentId(studentId));
        if(studentDto==null){
            return new ResponseDto(HttpStatus.NOT_FOUND, String.format(StringHelper.RESPONSE_REASON_NOT_FOUND,studentId));
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.addStudent(studentDto);
        responseDto.setHttpStatus(HttpStatus.OK);
        responseDto.setReason(HttpStatus.OK.getReasonPhrase());
        return responseDto;
    }

    //serialize model
    private StudentDto studentToDto(Student student){
        if(student!=null){
            return new StudentDto(student.getId(),student.getName(),student.getGender(),student.getStudentId(),student.getAverage());
        }
        return null;

    }

    //deserialize model
    //TODO Moti need complite check id;
    private Student studentToDto(StudentDto studentDto){
        if(studentDto!=null && studentDto.getGender()!= null && studentDto.getGender().length()>0 && StudentGender.contains(studentDto.getGender())){
            return new Student(studentDto.getName(),studentDto.getGender(),studentDto.getStudentId(),studentDto.getAverage());
        }
        return null;

    }

    //return List StudentDto
    public ResponseDto setDefaultStudents() {
        List<Student> students = InitJson.getStudents();
        students.forEach(student -> {
            if(getStudentById(student.getStudentId())==null){

            }
        });


        List<StudentDto> studentsDto= new ArrayList<StudentDto>();
        students.forEach(student->{ studentsDto.add(studentToDto(student)); });
        HttpStatus httpStatus = students!=null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseDto responseDto=new ResponseDto(httpStatus,studentsDto,httpStatus.getReasonPhrase());
        return responseDto;
    }
}
