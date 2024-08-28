package br.com.mesquitadev.estoque.service;


import br.com.mesquitadev.estoque.dto.request.ProdutoPersist;
import br.com.mesquitadev.estoque.dto.request.ProdutoUpdate;
import br.com.mesquitadev.estoque.models.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProdutoService {
    Page<Produto> listarProdutos(Pageable pageable);
    Optional<Produto> buscarProdutoPorId(Long id);
    Produto criarProduto(ProdutoPersist produto);
    Produto editarProduto(Long id, ProdutoUpdate produto);
    void deletarProduto(Long id);
}
