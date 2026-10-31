package br.com.fiap.delegaciapolicia.estoque.service;

import br.com.fiap.delegaciapolicia.compartilhado.excecao.RecursoNaoEncontradoException;
import br.com.fiap.delegaciapolicia.estoque.entity.ItemEstoque;
import br.com.fiap.delegaciapolicia.estoque.entity.MovimentacaoEstoque;
import br.com.fiap.delegaciapolicia.estoque.entity.TipoMovimentacao;
import br.com.fiap.delegaciapolicia.estoque.exception.EstoqueException;
import br.com.fiap.delegaciapolicia.estoque.repository.MovimentacaoEstoqueRepository;
import br.com.fiap.delegaciapolicia.policiais.service.PolicialService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoRepository;
    private final ItemEstoqueService itemService;
    private final PolicialService policialService;

    @Transactional
    public MovimentacaoEstoque cadastrar(MovimentacaoEstoque movimentacao) {
        policialService.buscarPorId(movimentacao.getIdPolicial());
        ItemEstoque item = itemService.buscarPorId(movimentacao.getItem().getId());
        validarItemAtivo(item);
        itemService.ajustarQuantidade(item, calcularVariacao(movimentacao.getTipo(), movimentacao.getQuantidade()));
        movimentacao.setId(null);
        movimentacao.setItem(item);
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        return movimentacaoRepository.save(movimentacao);
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoEstoque> listarTodos() {
        return movimentacaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public MovimentacaoEstoque buscarPorId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("O id da movimentação é obrigatório");
        }
        return movimentacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Movimentação não encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoEstoque> buscarPorTipo(TipoMovimentacao tipo) {
        return movimentacaoRepository.findByTipo(tipo);
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoEstoque> buscarPorItem(String idItem) {
        itemService.buscarPorId(idItem);
        return movimentacaoRepository.findByItemId(idItem);
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoEstoque> buscarPorPolicial(String idPolicial) {
        policialService.buscarPorId(idPolicial);
        return movimentacaoRepository.findByIdPolicial(idPolicial);
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoEstoque> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException("A data inicial não pode ser depois da data final");
        }
        return movimentacaoRepository.findByDataMovimentacaoBetween(inicio, fim);
    }

    @Transactional
    public MovimentacaoEstoque atualizar(String id, MovimentacaoEstoque dados) {
        MovimentacaoEstoque movimentacao = buscarPorId(id);
        policialService.buscarPorId(dados.getIdPolicial());
        itemService.ajustarQuantidade(movimentacao.getItem(),
                -calcularVariacao(movimentacao.getTipo(), movimentacao.getQuantidade()));
        ItemEstoque novoItem = itemService.buscarPorId(dados.getItem().getId());
        validarItemAtivo(novoItem);
        itemService.ajustarQuantidade(novoItem, calcularVariacao(dados.getTipo(), dados.getQuantidade()));
        movimentacao.setItem(novoItem);
        movimentacao.setIdPolicial(dados.getIdPolicial());
        movimentacao.setTipo(dados.getTipo());
        movimentacao.setQuantidade(dados.getQuantidade());
        movimentacao.setObservacao(dados.getObservacao());
        return movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public void remover(String id) {
        MovimentacaoEstoque movimentacao = buscarPorId(id);
        itemService.ajustarQuantidade(movimentacao.getItem(),
                -calcularVariacao(movimentacao.getTipo(), movimentacao.getQuantidade()));
        movimentacaoRepository.delete(movimentacao);
    }

    private void validarItemAtivo(ItemEstoque item) {
        if (!item.getAtivo()) {
            throw new EstoqueException("O item " + item.getNome() + " está inativo e não pode ser movimentado");
        }
    }

    private int calcularVariacao(TipoMovimentacao tipo, int quantidade) {
        if (tipo == TipoMovimentacao.ENTRADA) {
            return quantidade;
        }
        return -quantidade;
    }
}
