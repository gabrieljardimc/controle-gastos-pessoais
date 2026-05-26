package dev.jardim.controle_gastos_pessoais.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoriaRequestDto {

    @NotBlank
    private String nome;
}
