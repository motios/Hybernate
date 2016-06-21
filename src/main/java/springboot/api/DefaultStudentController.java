package springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.dto.ResponseDto;
import springboot.dto.StudentDto;
import springboot.model.Student;

import springboot.dao.StudentRepository;
import springboot.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DefaultStudentController {
	/*@Autowired
	private StudentRepository repository;*/
	@Autowired
	private StudentService studentService;

	/// http://localhost:8080/students
	@RequestMapping(value="/students", method = RequestMethod.GET)
	public ResponseDto allStudents(HttpServletRequest request){
		try {
			//repository.save(new Student("test5", "woman", 789789789, 95.30));
			//long k = 3;
			//dao.delete(k);
			//return repository.findAll();
			return studentService.getAllStudents();
		}
		catch (Exception e){
			return null;
		}
	}


	//get student by studentId
	@RequestMapping(value="/students/{student_id}", method = RequestMethod.GET)
	public ResponseDto getStudentByStudentId(@PathVariable("student_id") Long id,
							  HttpServletRequest request){
		return studentService.getStudentById(id);
	}

	@RequestMapping(value="/students/default", method = RequestMethod.POST)
	public ResponseDto setDefaultStudents(HttpServletRequest request){
		return studentService.setDefaultStudents();
	}

	@RequestMapping(value="/students", method = RequestMethod.POST)
	 public ResponseDto createStudent(@RequestBody StudentDto newStudent, HttpServletRequest request){
		return studentService.createStudent(newStudent);
	}

	@RequestMapping(value="/students/{student_id}/{name}/{gender}/{average}", method = RequestMethod.POST)
	public ResponseDto createStudent(@RequestParam("student_id" ) Long student_id,@RequestParam(value="name",required=false,defaultValue = "") String name, @RequestParam(value="gender",required=false,defaultValue = "") String gender, @RequestParam(value="average",required=false,defaultValue = "0.0") double average, HttpServletRequest request){
		StudentDto studentDto = new StudentDto(name,gender,student_id,average);
		return studentService.createStudent(studentDto);
	}

	@RequestMapping(value="/students/count", method = RequestMethod.GET)
	public ResponseDto getStudentsCount(HttpServletRequest request){
		return studentService.getStudentsCount();
	}
}
