package springboot.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import springboot.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

  Page<Student> findAll(Pageable pageable);
  
  @Query("SELECT c FROM Student c WHERE c.id=:id")
  Student findByUnikey(@Param("id") int id);
}