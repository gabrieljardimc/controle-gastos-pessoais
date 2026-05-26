package dev.jardim.controle_gastos_pessoais.service;

import dev.jardim.controle_gastos_pessoais.database.model.CategoriaGastosEntity;
import dev.jardim.controle_gastos_pessoais.database.model.GastosEntity;
import dev.jardim.controle_gastos_pessoais.database.repository.CategoriaGastosRepository;
import dev.jardim.controle_gastos_pessoais.database.repository.GastosRepository;
import dev.jardim.controle_gastos_pessoais.dto.GastosRequestDto;
import dev.jardim.controle_gastos_pessoais.dto.GastosResponseDto;
import dev.jardim.controle_gastos_pessoais.exception.BadRequestException;
import dev.jardim.controle_gastos_pessoais.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GastosService {

    private final GastosRepository gastosRepository;
    private final CategoriaGastosRepository categoriaGastosRepository;

    public GastosResponseDto criarGasto(GastosRequestDto dto) {

        if (dto.getValor().doubleValue() <= 0) {
            throw new BadRequestException("Valor deve ser maior que zero");
        }

        if (dto.getData().isAfter(LocalDate.now())) {
            throw new BadRequestException("Data não pode ser futura");
        }

        CategoriaGastosEntity categoria = categoriaGastosRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        GastosEntity gasto = GastosEntity.builder()
                .descricao(dto.getDescricao())
                .valor(dto.getValor())
                .data(dto.getData())
                .categoria(categoria)
                .build();

        return responseDto(gastosRepository.save(gasto));
    }

    public List<GastosResponseDto> listar() {
        return gastosRepository.findAll()
                .stream()
                .map(this::responseDto)
                .toList();
    }

    public GastosResponseDto buscarId(Long id) {
        GastosEntity gasto = gastosRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gasto não encontrado"));

        return responseDto(gasto);
    }
    public GastosResponseDto atualizar(Long id, GastosRequestDto dto) {

        if (dto.getValor().doubleValue() <= 0) {
            throw new BadRequestException("Valor deve ser maior que zero");
        }

        if (dto.getData().isAfter(LocalDate.now())) {
            throw new BadRequestException("Data não pode ser futura");
        }

        GastosEntity gasto = gastosRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gasto não encontrado"));

        CategoriaGastosEntity categoria = categoriaGastosRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        gasto.setDescricao(dto.getDescricao());
        gasto.setValor(dto.getValor());
        gasto.setData(dto.getData());
        gasto.setCategoria(categoria);

        return responseDto(gastosRepository.save(gasto));
    }

    public void deletar(Long id) {
        GastosEntity gasto = gastosRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gasto não encontrado"));

        gastosRepository.delete(gasto);
    }

    private GastosResponseDto responseDto(GastosEntity entity) {
        return GastosResponseDto.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .valor(entity.getValor())
                .data(entity.getData())
                .categoriaNome(entity.getCategoria().getNome())
                .build();
    }
}