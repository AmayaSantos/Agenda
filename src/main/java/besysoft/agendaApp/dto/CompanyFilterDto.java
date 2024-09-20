package besysoft.agendaApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CompanyFilterDto {
    @NotNull
    @Min(0)
    private Integer page; // [campo numérico, opcional]
    @NotNull
    @Min(1)
    private Integer size;

}
