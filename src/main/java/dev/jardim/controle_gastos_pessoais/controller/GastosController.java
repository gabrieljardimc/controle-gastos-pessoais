package dev.jardim.controle_gastos_pessoais.controller;

import dev.jardim.controle_gastos_pessoais.dto.GastosRequestDto;
import dev.jardim.controle_gastos_pessoais.dto.GastosResponseDto;
import dev.jardim.controle_gastos_pessoais.service.GastosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
@RequiredArgsConstructor
public class GastosController {

    private final GastosService gastosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GastosResponseDto criar(@Valid @RequestBody GastosRequestDto dto) {
        return gastosService.criarGasto(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GastosResponseDto> listar() {
        return gastosService.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GastosResponseDto buscarId(@PathVariable Long id) {
        return gastosService.buscarId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GastosResponseDto atualizar(@PathVariable Long id,
                                       @Valid @RequestBody GastosRequestDto dto) {
        return gastosService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        gastosService.deletar(id);
    }
}