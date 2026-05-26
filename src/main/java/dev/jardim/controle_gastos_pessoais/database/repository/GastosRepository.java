package dev.jardim.controle_gastos_pessoais.database.repository;

import dev.jardim.controle_gastos_pessoais.database.model.GastosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastosRepository extends JpaRepository<GastosEntity, Long> {
}
