package br.com.mesquitadev.estoque.controller;

import br.com.mesquitadev.estoque.converter.ProdutoConverter;
import br.com.mesquitadev.estoque.dto.request.ProdutoPersist;
import br.com.mesquitadev.estoque.dto.request.ProdutoUpdate;
import br.com.mesquitadev.estoque.dto.response.ProdutoResponse;
import br.com.mesquitadev.estoque.models.Produto;
import br.com.mesquitadev.estoque.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Produtos", description = "Endpoint de gestão de produtos")
public class ProdutoController {
  private final ProdutoConverter produtoConverter;
  private ProdutoService produtoService;

  @PostMapping()
  @Operation(summary = "Cadastrar produto", description = "Cadastrar um novo produto")
  public ResponseEntity<ProdutoResponse> save(@RequestBody @Valid ProdutoPersist produtoPersist) {
    Produto produto = produtoService.criarProduto(produtoPersist);
    return ResponseEntity.ok(produtoConverter.entityToResponse(produto));
  }

  @GetMapping()
  @Operation(summary = "Listar produtos", description = "Listar todos os produtos cadastrados")
  public ResponseEntity<Page<ProdutoResponse>> listarProdutos(@PageableDefault(size = 10, page = 0) Pageable pageable) {
    Page<Produto> produtos = produtoService.listarProdutos(pageable);
    return ResponseEntity.ok(produtoConverter.pageEntityToResponse(produtos));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Buscar produto por ID", description = "Buscar um produto pelo ID")
  public ResponseEntity<Optional<Produto>> buscarProdutoPorId(@PathVariable Long id) {
    Optional<Produto> produto = produtoService.buscarProdutoPorId(id);
    return ResponseEntity.ok(produto);
  }

  @PatchMapping("/{id}")
  @Operation(summary = "Atualizar produto", description = "Atualizar um produto existente")
  public ResponseEntity<ProdutoResponse> update(@PathVariable Long id, @RequestBody ProdutoUpdate produtoUpdate) {
    Produto produto = produtoService.editarProduto(id, produtoUpdate);
    return ResponseEntity.ok(produtoConverter.entityToResponse(produto));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletar produto", description = "Deletar um produto pelo seu ID")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    produtoService.deletarProduto(id);
    return ResponseEntity.noContent().build();
  }
}