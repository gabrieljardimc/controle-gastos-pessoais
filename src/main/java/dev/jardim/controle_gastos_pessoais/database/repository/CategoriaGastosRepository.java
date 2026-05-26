package dev.jardim.controle_gastos_pessoais.database.repository;

import dev.jardim.controle_gastos_pessoais.database.model.CategoriaGastosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaGastosRepository extends JpaRepository<CategoriaGastosEntity, Long> {

    Optional<CategoriaGastosEntity> findByNome(String nome);
}
