package springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.dto.ResponseDto;
import springboot.dto.StudentDto;
import springboot.service.StudentService;
import javax.servlet.http.HttpServletRequest;


@RestController
public class DefaultStudentController {

	@Autowired
	private StudentService studentService;

	/// http://localhost:8080/students
	@RequestMapping(value="/students", method = RequestMethod.GET)
	public ResponseDto allStudents(HttpServletRequest request){
		return studentService.getAllStudents();

	}


	//get student by studentId
	@RequestMapping(value="/students/{student_id}", method = RequestMethod.GET)
	public ResponseDto getStudentByStudentId(@PathVariable("student_id") Long id,
							  HttpServletRequest request){
		return studentService.getStudentById(id);
	}

	//initialize database from json file
	@RequestMapping(value="/students/default", method = RequestMethod.POST)
	public ResponseDto setDefaultStudents(HttpServletRequest request){
		return studentService.setDefaultStudents();
	}

	//create student with Dto
	@RequestMapping(value="/students", method = RequestMethod.POST)
	 public ResponseDto createStudent(@RequestBody StudentDto newStudent, HttpServletRequest request){
		return studentService.createStudent(newStudent);
	}

	//create student with parameters
	@RequestMapping(value="/students/add", method = RequestMethod.POST)
	public ResponseDto createStudent(@RequestParam("student_id" ) Long student_id,@RequestParam(value="name",required=false,defaultValue = "") String name, @RequestParam(value="gender",required=false,defaultValue = "") String gender, @RequestParam(value="average",required=false,defaultValue = "0.0") double average, HttpServletRequest request){
		StudentDto studentDto = new StudentDto(name,gender,student_id,average);
		return studentService.createStudent(studentDto);
	}

	//get count of students
	@RequestMapping(value="/students/count", method = RequestMethod.GET)
	public ResponseDto getStudentsCount(HttpServletRequest request){
		return studentService.getStudentsCount();
	}

	//delete student by studentId
	@RequestMapping(value="/students/{student_id}", method = RequestMethod.DELETE)
	public ResponseDto deleteStudent(@PathVariable("student_id") Long student_id,HttpServletRequest request){
		return studentService.deleteStudent(student_id);
	}
}
