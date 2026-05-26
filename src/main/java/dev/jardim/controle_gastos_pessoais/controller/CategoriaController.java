package dev.jardim.controle_gastos_pessoais.controller;

import dev.jardim.controle_gastos_pessoais.dto.CategoriaRequestDto;
import dev.jardim.controle_gastos_pessoais.dto.CategoriaResponseDto;
import dev.jardim.controle_gastos_pessoais.service.CategoriaGastosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaGastosService categoriaGastosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDto criar(@Valid @RequestBody CategoriaRequestDto dto) {
        return categoriaGastosService.criarCategoria(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaResponseDto> listar() {
        return categoriaGastosService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaResponseDto buscarId(@PathVariable Long id) {
        return categoriaGastosService.buscarPorId(id);
    }
}
