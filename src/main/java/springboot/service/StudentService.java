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
import springboot.util.ArgumentValidationHelper;
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

    //region public

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

    /*
     * insert default students to database exclude ixisting by studentID(teudat zehut)
     * return response Dto
     * */
    public ResponseDto setDefaultStudents() {
        List<Student> students = InitJson.getStudents();
        students.forEach(student -> {
            //check if not student exist and studentId is valid
            if(studentIdIsValid(student.getStudentId()) && (studentRepository.getStudentByStudentId(student.getStudentId()) != null) ){
                if(!(studentRepository.count()< Student.maxStudents)) {
                    studentRepository.save(student);
                }
                else return;
            }
        });
        ResponseDto responseDto=new ResponseDto(HttpStatus.OK,StringHelper.RESPONSE_REASON_SET_DEFAULT_STUDENTS_OK);
        return responseDto;
    }


    //create student by unique studentID(9 digits)
    public ResponseDto createStudent(StudentDto studentDto){
        ResponseDto responseDto = new ResponseDto();
        //check max students count
        if(!(studentRepository.count()< Student.maxStudents)){
            responseDto.setHttpStatus(HttpStatus.CONFLICT);
            responseDto.setReason(String.format(StringHelper.RESPONSE_REASON_MAX_STUDENTS_COUNT,studentDto.getStudentId()));
            return responseDto;
        }
        //studentId is valid and not exist in database
        if(studentIdIsValid(studentDto.getStudentId()) && (studentRepository.getStudentByStudentId(studentDto.getStudentId()) != null)){
            Student student = studentDtoToStudent(studentDto);
            if(student!=null ){
                try {
                    studentRepository.save(student);
                }
                catch (Exception e){
                    responseDto.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    responseDto.setReason(e.getMessage());
                    return responseDto;
                }
                //create and get ok
                if(!getStudentById(student.getStudentId()).getStudents().isEmpty()){
                    responseDto.setHttpStatus(HttpStatus.CREATED);
                    responseDto.setReason(String.format(StringHelper.RESPONSE_REASON_STUDENT_CREATED_OK,student.getStudentId()));
                    return responseDto;
                }
                //create but not get without exceptions
                responseDto.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                responseDto.setReason(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
                return responseDto;
            }
            //student is exist
            responseDto.setHttpStatus(HttpStatus.CONFLICT);
            responseDto.setReason(String.format(StringHelper.RESPONSE_REASON_EXIST,studentDto.getStudentId()));
            return responseDto;
        }
        //student id is not valid
        responseDto.setHttpStatus(HttpStatus.CONFLICT);
        responseDto.setReason(String.format(StringHelper.RESPONSE_REASON_STUDENT_ID_NOT_VALID,studentDto.getStudentId()));
        return responseDto;
    }

    //count of students
    public ResponseDto getStudentsCount() {
        ResponseDto responseDto=new ResponseDto(HttpStatus.OK,HttpStatus.OK.getReasonPhrase());
        responseDto.setStudentsCounter(studentRepository.count());
        return responseDto;
    }

    //delete student
    public ResponseDto deleteStudent(long studentId) {
        ResponseDto responseDto=new ResponseDto(HttpStatus.OK,HttpStatus.OK.getReasonPhrase());
        //studentId valid and student exist in database
        if(studentIdIsValid(studentId) && (studentRepository.getStudentByStudentId(studentId)!=null)){
            try {
                Student student= studentRepository.getStudentByStudentId(studentId);
                studentRepository.delete(student);
            }
            catch (Exception e){
                responseDto.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                responseDto.setReason(e.getMessage());
                return responseDto;
            }
            if(getStudentById(studentId).getStudents().isEmpty()){//deleted
                responseDto.setHttpStatus(HttpStatus.OK);
                responseDto.setReason(String.format(StringHelper.RESPONSE_REASON_STUDENT_DELETE_OK,studentId));
                return responseDto;
            }
            //delete without exception but exist in database
            responseDto.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDto.setReason(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return responseDto;
        }
        //studentID not valid or student not exist
        responseDto.setHttpStatus(HttpStatus.CONFLICT);
        responseDto.setReason(String.format(StringHelper.RESPONSE_REASON_STUDENT_ID_NOT_VALID, studentId));
        return responseDto;

    }

    //endregion public

    //region private

   /* //if student(model and dto) not exist and studentId is valid return false
    private boolean studentExistValidate(long studentId){
        return !(ArgumentValidationHelper.studentIdValidate(studentId) && getStudentById(studentId).getStudents().isEmpty());
    }*/

    //studentId is valid return
    private boolean studentIdIsValid(long studentId){
        return ArgumentValidationHelper.studentIdValidate(studentId);
    }
    //serialize model to Dto
    private StudentDto studentToDto(Student student){
        if(student!=null){
            return new StudentDto(student.getId(),student.getName(),student.getGender(),student.getStudentId(),student.getAverage());
        }
        return null;

    }

    //deserialize Dto to model
    private Student studentDtoToStudent(StudentDto studentDto){
        if((studentDto!=null && studentDto.getGender()!= null) &&(studentDto.getGender().length()==0 ||  studentDto.getGender().length()>0 && StudentGender.contains(studentDto.getGender()))){
            return new Student(studentDto.getName(),studentDto.getGender(),studentDto.getStudentId(),studentDto.getAverage());
        }
        return null;

    }

    //endregion //private

}
