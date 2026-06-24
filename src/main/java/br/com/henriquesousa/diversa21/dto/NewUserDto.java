package br.com.henriquesousa.diversa21.dto;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // cria getters e setters
@Schema(title = "NewUser", name = "NewUser", description = "new user dto")
public class NewUserDto {
    @Schema(description = "the name of the user", example = "John Smith")
    @NotBlank(message = "name-required")
    private String name;

    @Schema(description = "the username of the user", example = "john_smith")
    @NotBlank(message = "username-required")
    private String username;

    @Schema(description = "the email of the user", example = "john_smith@email.com")
    @NotBlank(message = "email-required")
    private String email;

    @Schema(description = "the date of birth of the user", example = "1970-01-01")
    @NotNull(message = "date-of-birth-required")
    private Timestamp birthDate;

    @NotNull(message = "city-required")
    private UUID city;

    @NotNull(message = "roles-required")
    private Collection<String> roles;

    @NotBlank(message = "description-required")
    private String description;

    @NotNull(message = "institution-required")
    private UUID institution;

    // private String avatar;
    //
    // private String coverImage;

    // @NotBlank(message = "active-required")
    // private boolean active;

}
