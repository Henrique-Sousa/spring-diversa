package br.com.henriquesousa.diversa21.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // cria getters e setters
@EqualsAndHashCode(callSuper = false)
@Schema(title = "ExistingUser", name = "ExistingUser", description = "existing user dto")
public class ExistingUserDto extends NewUserDto {
    @Schema(description = "the UUID of the user", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    @NotNull(message = "uid-required")
    private UUID uid;

}
