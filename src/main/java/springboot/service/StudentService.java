package springboot.service;

import springboot.dao.StudentRepository;
import org.springframework.stereotype.Service;
import springboot.dto.StudentDto;
import springboot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

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
    public List<StudentDto> getAllStudents() {
        Iterable<Student> students=  studentRepository.findAll();
        List<StudentDto> studentsDto= new ArrayList<StudentDto>();
        students.forEach(student->{ studentsDto.add(studentToDto(student)); });
        return studentsDto;
    }

    //return studentDto
    public StudentDto getStudentById(long id) {
        return studentToDto(studentRepository.findOne(id));
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
