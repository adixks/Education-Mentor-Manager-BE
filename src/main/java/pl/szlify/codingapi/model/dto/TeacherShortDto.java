package pl.szlify.codingapi.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Accessors(chain = true)
public class TeacherShortDto {

    //    @NotEmptyFields
    @NotNull(message = "NULL_VALUE")
    @Size(min = 2, message = "VALUE_TOO_SMALL")
    private String firstName;

    //    @NotEmptyFields
    @NotNull(message = "NULL_VALUE")
    @Size(min = 2, message = "VALUE_TOO_SMALL")
    private String lastName;

    @NotNull(message = "NULL_VALUE")
    @Size(min = 6, message = "PASSWORD_TOO_SHORT")
    private String password;

    //    @NotEmptyFields
    @NotNull(message = "NULL_VALUE")
    @Size(min = 1, message = "LIST_TOO_SMALL")
    private Set<String> languages;
}
