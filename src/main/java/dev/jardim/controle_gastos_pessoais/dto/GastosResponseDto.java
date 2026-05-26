package dev.jardim.controle_gastos_pessoais.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GastosResponseDto {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String categoriaNome;
}