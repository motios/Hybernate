package springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import springboot.dao.StudentRepository;
import springboot.model.Student;

/**
 * Created by We on 20.06.2016.
 */
public abstract class StudentService  implements StudentRepository{
    @Override
    public Page<Student> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Student findByUnikey(@Param("id") int id) {
        return null;
    }



    @Override
    public Student findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Student> findAll() {
        return null;
    }

    @Override
    public Iterable<Student> findAll(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void delete(Student entity) {

    }

    @Override
    public void delete(Iterable<? extends Student> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
