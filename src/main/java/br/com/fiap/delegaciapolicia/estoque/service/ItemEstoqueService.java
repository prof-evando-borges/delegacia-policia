package br.com.fiap.delegaciapolicia.estoque.service;

import br.com.fiap.delegaciapolicia.compartilhado.excecao.RecursoNaoEncontradoException;
import br.com.fiap.delegaciapolicia.estoque.entity.ItemEstoque;
import br.com.fiap.delegaciapolicia.estoque.exception.EstoqueException;
import br.com.fiap.delegaciapolicia.estoque.repository.ItemEstoqueRepository;
import br.com.fiap.delegaciapolicia.estoque.repository.MovimentacaoEstoqueRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemEstoqueService {

    private final ItemEstoqueRepository itemRepository;
    private final MovimentacaoEstoqueRepository movimentacaoRepository;
    private final CategoriaEstoqueService categoriaService;

    @Transactional
    public ItemEstoque cadastrar(ItemEstoque item) {
        if (itemRepository.existsByCodigo(item.getCodigo())) {
            throw new EstoqueException("Já existe um item com o código " + item.getCodigo());
        }
        item.setCategoria(categoriaService.buscarPorId(item.getCategoria().getId()));
        item.setId(null);
        return itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<ItemEstoque> listarTodos() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ItemEstoque buscarPorId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("O id do item é obrigatório");
        }
        return itemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado: " + id));
    }

    @Transactional(readOnly = true)
    public List<ItemEstoque> buscarPorNome(String nome) {
        return itemRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public List<ItemEstoque> buscarPorCategoria(String idCategoria) {
        categoriaService.buscarPorId(idCategoria);
        return itemRepository.findByCategoriaId(idCategoria);
    }

    @Transactional(readOnly = true)
    public List<ItemEstoque> buscarAtivos() {
        return itemRepository.findByAtivoTrue();
    }

    @Transactional(readOnly = true)
    public List<ItemEstoque> buscarComEstoqueBaixo(int limite) {
        if (limite < 0) {
            throw new IllegalArgumentException("O limite de estoque não pode ser negativo");
        }
        return itemRepository.findByQuantidadeLessThanEqual(limite);
    }

    @Transactional
    public ItemEstoque atualizar(String id, ItemEstoque dados) {
        ItemEstoque item = buscarPorId(id);
        if (itemRepository.existsByCodigoAndIdNot(dados.getCodigo(), id)) {
            throw new EstoqueException("Já existe outro item com o código " + dados.getCodigo());
        }
        item.setCategoria(categoriaService.buscarPorId(dados.getCategoria().getId()));
        item.setCodigo(dados.getCodigo());
        item.setNome(dados.getNome());
        item.setDescricao(dados.getDescricao());
        item.setLocalizacao(dados.getLocalizacao());
        item.setQuantidade(dados.getQuantidade());
        item.setValorUnitario(dados.getValorUnitario());
        item.setAtivo(dados.getAtivo());
        return itemRepository.save(item);
    }

    @Transactional
    public void remover(String id) {
        ItemEstoque item = buscarPorId(id);
        if (movimentacaoRepository.existsByItemId(id)) {
            throw new EstoqueException("Não é possível remover um item que possui movimentações");
        }
        itemRepository.delete(item);
    }

    @Transactional
    public ItemEstoque ajustarQuantidade(ItemEstoque item, int variacao) {
        int novaQuantidade = item.getQuantidade() + variacao;
        if (novaQuantidade < 0) {
            throw new EstoqueException("Estoque insuficiente do item " + item.getNome()
                    + ": disponível " + item.getQuantidade());
        }
        item.setQuantidade(novaQuantidade);
        return itemRepository.save(item);
    }
}
