package br.com.fiap.delegaciapolicia.estoque.service;

import br.com.fiap.delegaciapolicia.compartilhado.excecao.RecursoNaoEncontradoException;
import br.com.fiap.delegaciapolicia.estoque.entity.CategoriaEstoque;
import br.com.fiap.delegaciapolicia.estoque.exception.EstoqueException;
import br.com.fiap.delegaciapolicia.estoque.repository.CategoriaEstoqueRepository;
import br.com.fiap.delegaciapolicia.estoque.repository.ItemEstoqueRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaEstoqueService {

    private final CategoriaEstoqueRepository categoriaRepository;
    private final ItemEstoqueRepository itemRepository;

    @Transactional
    public CategoriaEstoque cadastrar(CategoriaEstoque categoria) {
        if (categoriaRepository.existsByNomeIgnoreCase(categoria.getNome())) {
            throw new EstoqueException("Já existe uma categoria com o nome " + categoria.getNome());
        }
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    @Transactional(readOnly = true)
    public List<CategoriaEstoque> listarTodos() {
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoriaEstoque buscarPorId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("O id da categoria é obrigatório");
        }
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada: " + id));
    }

    @Transactional
    public CategoriaEstoque atualizar(String id, CategoriaEstoque dados) {
        CategoriaEstoque categoria = buscarPorId(id);
        if (categoriaRepository.existsByNomeIgnoreCaseAndIdNot(dados.getNome(), id)) {
            throw new EstoqueException("Já existe outra categoria com o nome " + dados.getNome());
        }
        categoria.setNome(dados.getNome());
        categoria.setDescricao(dados.getDescricao());
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void remover(String id) {
        CategoriaEstoque categoria = buscarPorId(id);
        if (itemRepository.existsByCategoriaId(id)) {
            throw new EstoqueException("Não é possível remover uma categoria que possui itens");
        }
        categoriaRepository.delete(categoria);
    }
}
