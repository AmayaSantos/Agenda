package besysoft.agendaApp.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CompanyFilterDto {
    @NotNull
    @Min(0)
    private Integer page; // [campo numérico, opcional]
    @NotNull
    @Min(1)
    private Integer size;

}
