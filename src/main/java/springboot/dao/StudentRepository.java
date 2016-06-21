package springboot.dao;

import org.springframework.data.repository.CrudRepository;
import springboot.model.Student;


public interface StudentRepository extends CrudRepository<Student, Long> {

  /*Page<Student> findAll(Pageable pageable);
  
  @Query("SELECT c FROM Student c WHERE c.id=:id")*/

  //Student findByKey(@Param("id") int id);

  Student getStudentByStudentId(long student_id);
}
