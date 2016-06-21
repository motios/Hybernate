package springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot.dto.ResponseDto;
import springboot.dto.StudentDto;
import springboot.model.Student;

import springboot.dao.StudentRepository;
import springboot.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DefaultStudentController {
	@Autowired
	private StudentRepository repository;
	@Autowired
	private StudentService studentService;

	/// http://localhost:8080/students
	@RequestMapping(value="/students", method = RequestMethod.GET)
	public ResponseDto allStudents(HttpServletRequest request){
		try {
			repository.save(new Student("test5", "woman", 147258369, 95.30));
			/*long k = 3;
			dao.delete(k);*/
			//return repository.findAll();
			return studentService.getAllStudents();
		}
		catch (Exception e){
			return null;
		}
	}


	@RequestMapping(value="/students/{student_id}", method = RequestMethod.GET)
	public ResponseDto getStudentById(@PathVariable("student_id") Long id,
							  HttpServletRequest request){
		return studentService.getStudentById(id);
	}
}
