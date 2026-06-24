package br.com.henriquesousa.diversa21.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // cria getters e setters
@EqualsAndHashCode(callSuper = false)
@Schema(title = "UserRegister", name = "UserRegister", description = "dto for registering a new user")
public class UserRegisterDto extends NewUserDto {
    @Schema(description = "the password for the user", example = "12345678")
    @NotBlank(message = "password-required")
    private String password;

}
