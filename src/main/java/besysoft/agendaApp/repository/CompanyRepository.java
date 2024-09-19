package besysoft.agendaApp.repository;

import besysoft.agendaApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Person, Integer> {

}
