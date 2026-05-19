package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
    @NotBlank(message = "O e-mail do usuário é é obrigatório!")
    @Email(message = "O e-mail não é válido!")
    String email,

    @NotBlank(message = "A senha é obrigatória!")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres!")
    String senha
) {
}
