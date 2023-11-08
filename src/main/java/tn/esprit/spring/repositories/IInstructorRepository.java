package tn.esprit.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Instructor;

@Repository
public interface IInstructorRepository extends JpaRepository<Instructor, Long> {

}
