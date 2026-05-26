package dev.jardim.controle_gastos_pessoais.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "gastos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GastosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String valor;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaGastosEntity categoria;
}
