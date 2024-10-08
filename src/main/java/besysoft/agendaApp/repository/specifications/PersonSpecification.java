package besysoft.agendaApp.repository.specifications;

import besysoft.agendaApp.model.Person;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

import static io.micrometer.common.util.StringUtils.isBlank;

public class PersonSpecification {

    private static final String SURNAME = "surname";
    private static final String CITY = "city";
    private static final String NAME = "name";

    public static Specification<Person> fullName(String fullName) {
        return (root, query, builder) -> (isBlank(fullName)) ? builder.conjunction():
            builder.like(getUpperSaveFullName(root, builder), "%"+fullName.trim().toUpperCase()+"%");
    }

    private static Expression<String> getUpperSaveFullName(Root<Person> root, CriteriaBuilder builder) {
        return builder.upper(
                builder.concat(root.get(NAME), root.get(SURNAME)));
    }

    public static Specification<Person> inCities(List<String> cities) {
        return (root, query, builder) ->(cities.isEmpty()) ? builder.conjunction():
                builder.upper(root.get(CITY)).in( getUpperCaseCities(cities));
    }

    private static List<String> getUpperCaseCities(List<String> cities) {
        return cities.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }


}
