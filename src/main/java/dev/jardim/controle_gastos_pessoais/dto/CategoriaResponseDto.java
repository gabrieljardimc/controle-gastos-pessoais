package dev.jardim.controle_gastos_pessoais.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoriaResponseDto {

    private Long id;
    private String nome;
}
