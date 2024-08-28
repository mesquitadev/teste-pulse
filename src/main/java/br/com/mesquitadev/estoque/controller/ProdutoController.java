package br.com.mesquitadev.estoque.controller;

import br.com.mesquitadev.estoque.converter.ProdutoConverter;
import br.com.mesquitadev.estoque.dto.request.ProdutoPersist;
import br.com.mesquitadev.estoque.dto.request.ProdutoUpdate;
import br.com.mesquitadev.estoque.dto.response.ProdutoResponse;
import br.com.mesquitadev.estoque.models.Produto;
import br.com.mesquitadev.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/produtos")
@AllArgsConstructor
public class ProdutoController {
  private final ProdutoConverter produtoConverter;
  private ProdutoService produtoService;

  @GetMapping()
  public ResponseEntity<Page<ProdutoResponse>> listarProdutos(@PageableDefault(size = 10, page = 0) Pageable pageable) {
    Page<Produto> produtos = produtoService.listarProdutos(pageable);
    return ResponseEntity.ok(produtoConverter.pageEntityToResponse(produtos));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Produto>> buscarProdutoPorId(@PathVariable Long id) {
    Optional<Produto> produto = produtoService.buscarProdutoPorId(id);
    return ResponseEntity.ok(produto);
  }

  @PostMapping()
  public ResponseEntity<ProdutoResponse> save(@RequestBody @Valid ProdutoPersist produtoPersist) {
    Produto produto = produtoService.criarProduto(produtoPersist);
    return ResponseEntity.ok(produtoConverter.entityToResponse(produto));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ProdutoResponse> update(@PathVariable Long id, @RequestBody ProdutoUpdate produtoUpdate) {
    Produto produto = produtoService.editarProduto(id, produtoUpdate);
    return ResponseEntity.ok(produtoConverter.entityToResponse(produto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    produtoService.deletarProduto(id);
    return ResponseEntity.noContent().build();
  }
}