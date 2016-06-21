package springboot.service;

import org.springframework.http.HttpStatus;
import springboot.dao.StudentRepository;
import org.springframework.stereotype.Service;
import springboot.dto.ResponseDto;
import springboot.dto.StudentDto;
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
    public ResponseDto getStudentById(long id) {
        StudentDto studentDto= studentToDto(studentRepository.findOne(id));
        if(studentDto==null){
            return new ResponseDto(HttpStatus.NOT_FOUND, String.format(StringHelper.RESPONSE_REASON_NOT_FOUND,id));
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
        if(studentDto!=null){
            //return new Student(studentDto.getId(),studentDto.getName(),studentDto.getGender(),studentDto.getStudentId(),studentDto.getAverage());
        }
        return null;

    }
}
