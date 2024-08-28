package br.com.mesquitadev.estoque.service;

import br.com.mesquitadev.estoque.converter.ProdutoConverter;
import br.com.mesquitadev.estoque.dao.ProdutoDAO;
import br.com.mesquitadev.estoque.dto.request.ProdutoPersist;
import br.com.mesquitadev.estoque.dto.request.ProdutoUpdate;
import br.com.mesquitadev.estoque.exception.ProdutoNaoEncontradoException;
import br.com.mesquitadev.estoque.factory.ProdutoDAOFactory;
import br.com.mesquitadev.estoque.models.Produto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProdutoServiceImplTest {

  @Mock
  private ProdutoDAO produtoDAO;

  @Mock
  private ProdutoConverter produtoConverter;

  @Mock
  private ProdutoDAOFactory produtoDaoFactory;

  @InjectMocks
  private ProdutoServiceImpl produtoService;

  public ProdutoServiceImplTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCriarProduto() {
    ProdutoPersist persist = new ProdutoPersist();
    persist.setNome("Produto 1");
    persist.setDescricao("Descricao 1");
    persist.setPreco(100.0);
    persist.setQuantidade(10);

    Produto produto = new Produto();
    produto.setNome("Produto 1");
    produto.setDescricao("Descricao 1");
    produto.setPreco(100.0);
    produto.setQuantidade(10);

    when(produtoConverter.persistToEntity(persist)).thenReturn(produto);
    when(produtoDaoFactory.getProdutoDAO()).thenReturn(produtoDAO);
    when(produtoDAO.save(produto)).thenReturn(produto);

    Produto result = produtoService.criarProduto(persist);

    assertEquals("Produto 1", result.getNome());
    assertEquals("Descricao 1", result.getDescricao());
    assertEquals(100.0, result.getPreco());
    assertEquals(10, result.getQuantidade());
  }

  @Test
  public void testListarProdutos() {
    Produto produto = new Produto();
    produto.setNome("Produto 1");
    produto.setDescricao("Descricao 1");
    produto.setPreco(100.0);
    produto.setQuantidade(10);

    Page<Produto> page = new PageImpl<>(Collections.singletonList(produto));
    Pageable pageable = PageRequest.of(0, 10);

    when(produtoDaoFactory.getProdutoDAO()).thenReturn(produtoDAO);
    when(produtoDAO.findAll(pageable)).thenReturn(page);

    Page<Produto> result = produtoService.listarProdutos(pageable);

    assertEquals(1, result.getTotalElements());
    assertEquals("Produto 1", result.getContent().get(0).getNome());
  }

  @Test
  public void testBuscarProdutoPorId() {
    Produto produto = new Produto();
    produto.setId(1L);
    produto.setNome("Produto 1");
    produto.setDescricao("Descricao 1");
    produto.setPreco(100.0);
    produto.setQuantidade(10);

    when(produtoDaoFactory.getProdutoDAO()).thenReturn(produtoDAO);
    when(produtoDAO.findById(1L)).thenReturn(Optional.of(produto));

    Optional<Produto> result = produtoService.buscarProdutoPorId(1L);

    assertTrue(result.isPresent());
    assertEquals("Produto 1", result.get().getNome());
  }

  @Test
  public void testEditarProduto() {
    ProdutoUpdate update = new ProdutoUpdate();
    update.setNome("Produto Editado");
    update.setDescricao("Descricao Editada");
    update.setPreco(150.0);
    update.setQuantidade(20);

    Produto produto = new Produto();
    produto.setId(1L);
    produto.setNome("Produto 1");
    produto.setDescricao("Descricao 1");
    produto.setPreco(100.0);
    produto.setQuantidade(10);

    Produto produtoEditado = new Produto();
    produtoEditado.setId(1L);
    produtoEditado.setNome("Produto Editado");
    produtoEditado.setDescricao("Descricao Editada");
    produtoEditado.setPreco(150.0);
    produtoEditado.setQuantidade(20);

    when(produtoDaoFactory.getProdutoDAO()).thenReturn(produtoDAO);
    when(produtoDAO.existsById(1L)).thenReturn(true);
    when(produtoConverter.updateToEntity(update)).thenReturn(produtoEditado);
    when(produtoDAO.save(produtoEditado)).thenReturn(produtoEditado);

    Produto result = produtoService.editarProduto(1L, update);

    assertEquals("Produto Editado", result.getNome());
    assertEquals("Descricao Editada", result.getDescricao());
    assertEquals(150.0, result.getPreco());
    assertEquals(20, result.getQuantidade());
  }

  @Test
  public void testDeletarProduto() {
    when(produtoDaoFactory.getProdutoDAO()).thenReturn(produtoDAO);
    when(produtoDAO.existsById(1L)).thenReturn(true);

    produtoService.deletarProduto(1L);

    verify(produtoDAO, times(1)).deleteById(1L);
  }

  @Test
  public void testBuscarProdutoPorIdNotFound() {
    when(produtoDaoFactory.getProdutoDAO()).thenReturn(produtoDAO);
    when(produtoDAO.findById(1L)).thenReturn(Optional.empty());

    assertThrows(ProdutoNaoEncontradoException.class, () -> produtoService.buscarProdutoPorId(1L));
  }

  @Test
  public void testEditarProdutoNotFound() {
    ProdutoUpdate update = new ProdutoUpdate();
    update.setNome("Produto Editado");
    update.setDescricao("Descricao Editada");
    update.setPreco(150.0);
    update.setQuantidade(20);

    when(produtoDaoFactory.getProdutoDAO()).thenReturn(produtoDAO);
    when(produtoDAO.existsById(1L)).thenReturn(false);

    assertThrows(ProdutoNaoEncontradoException.class, () -> produtoService.editarProduto(1L, update));
  }

  @Test
  public void testDeletarProdutoNotFound() {
    when(produtoDaoFactory.getProdutoDAO()).thenReturn(produtoDAO);
    when(produtoDAO.existsById(1L)).thenReturn(false);

    assertThrows(ProdutoNaoEncontradoException.class, () -> produtoService.deletarProduto(1L));
  }
}