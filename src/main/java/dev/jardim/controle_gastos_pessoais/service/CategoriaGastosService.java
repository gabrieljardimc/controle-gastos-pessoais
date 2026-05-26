package dev.jardim.controle_gastos_pessoais.service;

import dev.jardim.controle_gastos_pessoais.database.model.CategoriaGastosEntity;
import dev.jardim.controle_gastos_pessoais.database.repository.CategoriaGastosRepository;
import dev.jardim.controle_gastos_pessoais.dto.CategoriaRequestDto;
import dev.jardim.controle_gastos_pessoais.dto.CategoriaResponseDto;
import dev.jardim.controle_gastos_pessoais.exception.BadRequestException;
import dev.jardim.controle_gastos_pessoais.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaGastosService {

    private final CategoriaGastosRepository categoriaGastosRepository;

    public CategoriaResponseDto criarCategoria(CategoriaRequestDto dto) {
        if (categoriaGastosRepository.findByNome(dto.getNome()).isPresent()) {
            throw new BadRequestException("Já existe uma categoria com esse nome");
        }

        CategoriaGastosEntity entidade = CategoriaGastosEntity.builder()
                .nome(dto.getNome())
                .build();

        return responseDto(categoriaGastosRepository.save(entidade));
    }

    public List<CategoriaResponseDto> listar() {
        return categoriaGastosRepository.findAll()
                .stream()
                .map(this::responseDto)
                .toList();
    }

    public CategoriaResponseDto buscarId(Long id) {
        CategoriaGastosEntity entity = categoriaGastosRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        return responseDto(entity);
    }

    private CategoriaResponseDto responseDto(CategoriaGastosEntity entity) {
        return CategoriaResponseDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }
}