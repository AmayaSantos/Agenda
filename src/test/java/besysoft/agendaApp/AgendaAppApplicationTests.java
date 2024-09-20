package besysoft.agendaApp;

import besysoft.agendaApp.service.CompanyServiceTest;
import besysoft.agendaApp.service.PersonServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
		PersonServiceTest.class,
		CompanyServiceTest.class
})
class AgendaAppApplicationTests {

}
