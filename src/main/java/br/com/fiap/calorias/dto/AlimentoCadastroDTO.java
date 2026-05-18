package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AlimentoCadastroDTO(
        Long alimentoId,

        @NotBlank(message = "O nome do alimento é obrigatório!")
        String nome,

        @NotBlank(message = "A porção é obrigatória!")
        String porcao,

        @NotNull(message = "A quantidade de proteína é obrigatória!")
        @PositiveOrZero(message = "A quantidade de proteína não pode ser negativa!")
        Double quantidadeProteina,

        @NotNull(message = "A quantidade de carboidrato é obrigatória!")
        @PositiveOrZero(message = "A quantidade de carboidrato não pode ser negativa!")
        Double quantidadeCarboidrato,

        @NotNull(message = "A quantidade de gorduras é obrigatória!")
        @PositiveOrZero(message = "A quantidade de gorduras não pode ser negativa!")
        Double quantidadeGorduras
) {
}