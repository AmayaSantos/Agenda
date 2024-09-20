package besysoft.agendaApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PersonFilterDto {
    @NotNull
    @Min(0)
    private Integer page; // [campo numérico, opcional]
    @NotNull
    @Min(1)
    private Integer size;

    private String fullName;

    private List<String> citiesNames;


}
