package springboot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.model.Student;

import springboot.dao.StudentRepository;

@RestController
public class DefaultStudentController {
	@Autowired
	private StudentRepository repository;

	/// http://localhost:8080/allCustomers
	@RequestMapping("/allStudents")
	public Iterable<Student> allStudents(){
		try {
			repository.save(new Student("test4", "woman", 147258369, 95.30));
			/*long k = 3;
			dao.delete(k);*/
			return repository.findAll();
		}
		catch (Exception e){
			return null;
		}
	}
	
	@RequestMapping("/allCustomers/{id}")
	public Student oneCustomer(@PathVariable("id") int id){
		return repository.findByUnikey(id);
	}
}
