package br.com.fiap.delegaciapolicia.patio.controller;

import br.com.fiap.delegaciapolicia.patio.entity.Veiculo;
import br.com.fiap.delegaciapolicia.patio.service.VeiculoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patio/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) {
        return veiculoService.cadastrar(veiculo);
    }

    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculoService.listarTodos();
    }

    @GetMapping(params = "placa")
    public List<Veiculo> buscarPorPlaca(
        @RequestParam
        @Pattern(regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$",
            message = "A placa deve estar no formato ABC1234 ou ABC1D23, com letras maiúsculas")
        String placa) {
        return veiculoService.buscarPorPlaca(placa);
    }

    @GetMapping("/busca/no-patio")
    public List<Veiculo> listarNoPatio() {
        return veiculoService.listarNoPatio();
    }

    @GetMapping("/{id}")
    public Veiculo buscarPorId(@PathVariable String id) {
        return veiculoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable String id, @Valid @RequestBody Veiculo veiculo) {
        return veiculoService.atualizar(id, veiculo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String id) {
        veiculoService.remover(id);
    }
}
